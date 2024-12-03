function filterTable() {
    const vacinaFilter = document.getElementById('vacina-filter').value.toLowerCase();
    const loteFilter = document.getElementById('lote-filter').value.toLowerCase();
    const table = document.getElementById('tabela-vacina');
    const tr = table.getElementsByTagName('tr');

    for (let i = 1; i < tr.length; i++) {
        const tdVacina = tr[i].getElementsByTagName('td')[1];
        const tdLote = tr[i].getElementsByTagName('td')[2];
        if (tdVacina && tdLote) {
            const vacinaValue = tdVacina.textContent || tdVacina.innerText;
            const loteValue = tdLote.textContent || tdLote.innerText;

            if (vacinaValue.toLowerCase().indexOf(vacinaFilter) > -1 && loteValue.toLowerCase().indexOf(loteFilter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

async function cancelarEdicao(id) {
    try {
        const response = await fetch(`http://localhost:8080/vacinas/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao carregar vacina.');
        }
        const vacina = await response.json();

        const row = document.querySelector(`tr[data-id='${id}']`);
        if (!row) {
            throw new Error('Linha da vacina não encontrada.');
        }

        row.innerHTML = `
            <td>${id}</td>
            <td>${vacina.nome}</td>
            <td>${vacina.lote}</td>
            <td>${vacina.validade}</td>
            <td><button class="btn-form" onclick="editarVacina(${id})">Editar</button></td>
            <td><button class="btn-form" onclick="excluirVacina(${id})">Excluir</button></td>
        `;
    } catch (error) {
        alert(error.message);
    }
}

async function editarVacina(id) {
    try {
        const response = await fetch(`http://localhost:8080/vacinas/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao carregar vacina.');
        }
        const vacina = await response.json();

        // Encontrar a linha correspondente à vacina
        const row = document.querySelector(`tr[data-id='${id}']`);
        if (!row) {
            throw new Error('Linha da vacina não encontrada.');
        }

        // Transformar a linha em um formulário
        row.innerHTML = `
            <td>${id}</td>
            <td><input type="text" id="nome-${id}" value="${vacina.nome}"></td>
            <td><input type="text" id="lote-${id}" value="${vacina.lote}"></td>
            <td><input type="date" id="validade-${id}" value="${vacina.validade}" required></td>
            <td><button class="btn-form" onclick="salvarEdicao(${id})">Salvar</button></td>
            <td><button class="btn-form" onclick="cancelarEdicao(${id})">Cancelar</button></td>
        `;
    } catch (error) {
        alert(error.message);
    }
}

async function salvarEdicao(id) {
    try {
        // Buscar os dados atuais da vacina
        const responseGet = await fetch(`http://localhost:8080/vacinas/${id}`);
        if (!responseGet.ok) {
            throw new Error('Erro ao carregar vacina.');
        }
        const vacina = await responseGet.json();

        // Obter os valores atualizados do formulário
        const nome = document.getElementById(`nome-${id}`).value;
        const lote = document.getElementById(`lote-${id}`).value;
        const validade = document.getElementById(`validade-${id}`).value;

        // Enviar a atualização com todos os campos
        const responsePut = await fetch(`http://localhost:8080/vacinas/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nome, lote, validade })
        });

        if (!responsePut.ok) {
            throw new Error('Erro ao salvar vacina.');
        }

        alert('Vacina atualizada com sucesso!');
        listarVacina();
    } catch (error) {
        alert(error.message);
    }
}

async function excluirVacina(id) {
    // Alerta de confirmação
    if (!confirm('Deseja realmente excluir a vacina?')) {
        return;
    }
    try {
        const response = await fetch(`http://localhost:8080/vacinas/${id}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Erro ao excluir vacina.');
        }
        alert('Vacina excluída com sucesso!');
        listarVacina();
    } catch (error) {
        alert(error.message);
    }
}

async function listarVacina() {
    try {
        const vacinas = await fetch('http://localhost:8080/vacinas', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!vacinas.ok) {
            throw new Error(`HTTP error! status: ${vacinas.status}`);
        }
        const vacina = await vacinas.json();
        if (!Array.isArray(vacina)) {
            throw new Error('Response is not an array');
        }
        // Print tabela vacinas
        const vacinaDetails = document.getElementById('tabela-vacina');
        vacinaDetails.innerHTML = `
            <tr> 
                <th>ID</th>
                <th>Vacina</th>
                <th>Lote</th>
                <th>Data de Validade</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
        `;
        vacina.forEach(vacina => {
            const tr = document.createElement('tr');
            tr.setAttribute('data-id', vacina.idVacina);

            tr.innerHTML += `
                <td>${vacina.idVacina}</td>
                <td>${vacina.nome}</td>
                <td>${vacina.lote}</td>
                <td>${vacina.validade}</td>
                <td><button class="btn-form" onclick="editarVacina(${vacina.idVacina})">Editar</button></td>
                <td><button class="btn-form" onclick="excluirVacina(${vacina.idVacina})">Excluir</button></td>
            `;
            vacinaDetails.appendChild(tr);
        });
    } catch (error) {
        alert(error.message);
    }    
}


document.addEventListener('DOMContentLoaded', async function() {
    const form = document.getElementById('form-vacina');
    
    if (!form) {
        console.error('Formulário não encontrado!');
        return;
    }

    form.addEventListener('submit', async function(event) {
        event.preventDefault();
        const vacinaData = {
            nome: document.getElementById('vacina').value,
            lote: document.getElementById('lote').value,
            validade: document.getElementById('validade').value,
        };

        try {
            const response = await fetch('http://localhost:8080/vacinas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vacinaData)
            });

            if (!response.ok) {
                throw new Error('Erro ao vacinar animal.');
            }
            alert('Vacina aplicada com sucesso!');
            window.location.reload();
            form.reset();
        } catch (error) {
            alert(error.message);
        }
    });
});