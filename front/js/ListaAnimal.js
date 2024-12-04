function filterTable() {
    const nameFilter = document.getElementById('nameFilter').value.toLowerCase();
    const statusFilter = document.getElementById('statusFilter').value;
    const table = document.getElementById('animalTable');
    const tr = table.getElementsByTagName('tr');

    for (let i = 1; i < tr.length; i++) {
        const tdName = tr[i].getElementsByTagName('td')[1];
        const tdStatus = tr[i].getElementsByTagName('td')[3];
        if (tdName && tdStatus) {
            const nameValue = tdName.textContent || tdName.innerText;
            const statusValue = tdStatus.textContent || tdStatus.innerText;

            if (nameValue.toLowerCase().indexOf(nameFilter) > -1 && (statusFilter === "" || statusValue === statusFilter)) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

async function cancelarEdicao(id) {
    try {
        const response = await fetch(`http://localhost:8080/animais/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao carregar animal.');
        }
        const animal = await response.json();

        const row = document.querySelector(`tr[data-id='${id}']`);
        if (!row) {
            throw new Error('Linha do animal não encontrada.');
        }

        row.innerHTML = `
            <td>${id}</td>
            <td>${animal.nome}</td>
            <td>${animal.sexo}</td>
            <td>${animal.idade}</td>
            <td>${animal.tipo}</td>
            <td>${animal.disponibilidade ? 'Disponível para doação' : 'Indisponível para doação'}</td>
            <td><button class="btn-form" onclick="">Ver Carteirinha</button></td>
            <td><button class="btn-form" onclick="editarAnimal(${id})">Editar</button></td>
            <td><button class="btn-form" onclick="excluirAnimal(${id})">Excluir</button></td>
        `;
    } catch (error) {
        alert(error.message);
    }
}

async function editarAnimal(id) {
    try {
        const response = await fetch(`http://localhost:8080/animais/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao carregar animal.');
        }
        const animal = await response.json();

        // Encontrar a linha correspondente ao animal
        const row = document.querySelector(`tr[data-id='${id}']`);
        if (!row) {
            throw new Error('Linha do animal não encontrada.');
        }

        // Transformar a linha em um formulário
        row.innerHTML = `
            <td>${id}</td>
            <td><input type="text" id="nome-${id}" value="${animal.nome}"></td>
            <td>
                <select id="sexo-${id}" name="sexoAnimal" required>
                    <option value="M" ${animal.sexo === 'M' ? 'selected' : ''}>Macho</option>
                    <option value="F" ${animal.sexo === 'F' ? 'selected' : ''}>Fêmea</option>
                </select>
            </td>
            <td><input type="number" id="idade-${id}" value="${animal.idade}" required></td>
            <td>
                <select id="tipo-${id}" name="tipoAnimal" required>
                    <option value="Cachorro" ${animal.tipo === 'Cachorro' ? 'selected' : ''}>Cachorro</option>
                    <option value="Gato" ${animal.tipo === 'Gato' ? 'selected' : ''}>Gato</option>
                </select>
            </td>            
            <td>
                <select id="disponibilidade-${id}">
                    <option value="true" ${animal.disponibilidade ? 'selected' : ''}>Disponível para doação</option>
                    <option value="false" ${!animal.disponibilidade ? 'selected' : ''}>Indisponível para doação</option>
                </select>
            </td>
            <td><button class="btn-form" onclick="">Ver Carteirinha</button></td>
            <td><button class="btn-form" onclick="salvarEdicao(${id})">Salvar</button></td>
            <td><button class="btn-form" onclick="cancelarEdicao(${id})">Cancelar</button></td>
        `;
    } catch (error) {
        alert(error.message);
    }
}

async function salvarEdicao(id) {
    try {
        // Buscar os dados atuais do animal
        const responseGet = await fetch(`http://localhost:8080/animais/${id}`);
        if (!responseGet.ok) {
            throw new Error('Erro ao carregar animal.');
        }
        const animal = await responseGet.json();

        // Obter os valores atualizados do formulário
        const nome = document.getElementById(`nome-${id}`).value;
        const tipo = document.getElementById(`tipo-${id}`).value;
        const disponibilidade = document.getElementById(`disponibilidade-${id}`).value === 'true';

        // Manter os valores de idade e carteirinha
        const idade = animal.idade;
        const carteirinha = animal.carteira_vacina_id;

        // Enviar a atualização com todos os campos
        const responsePut = await fetch(`http://localhost:8080/animais/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nome, tipo, disponibilidade, idade, carteirinha })
        });

        if (!responsePut.ok) {
            throw new Error('Erro ao salvar animal.');
        }

        alert('Animal atualizado com sucesso!');
        listarAnimais();
    } catch (error) {
        alert(error.message);
    }
}

async function excluirAnimal(id) {
    // Alerta de confirmação
    if (!confirm('Deseja realmente excluir o animal?')) {
        return;
    }
    try {
        const response = await fetch(`http://localhost:8080/animais/${id}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Erro ao excluir animal.');
        }
        alert('Animal excluído com sucesso!');
        listarAnimais();
    } catch (error) {
        alert(error.message);
    }
}

function verCarteirinha(animalId) {
    window.open(`Carteirinha.html?animalId=${animalId}`);
}

// Lista Todos os Animais
async function listarAnimais() {
    try {
        const response = await fetch('http://localhost:8080/animais');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        if (!Array.isArray(data)) {
            throw new Error('Response is not an array');
        }
        const table = document.getElementById('animalTable');
        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Sexo</th>
                <th>Idade</th>
                <th>Tipo</th>
                <th>Status</th>
                <th>Carteirinha</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
        `;
        data.forEach(animal => {
            if(animal.disponibilidade === true){
                animal.disponibilidade = "Disponível para Adoção";
            }
            else{
                animal.disponibilidade = "Indisponível para Adoção";
            }

            if(animal.sexo == 'M') {
                animal.sexo = "Macho";
            }
            else{
                animal.sexo = "Fêmea";
            }
            
            const tr = document.createElement('tr');
            tr.setAttribute('data-id', animal.id);

            tr.innerHTML = `
                <td>${animal.id}</td>
                <td>${animal.nome}</td>
                <td>${animal.sexo}</td>
                <td>${animal.idade}</td>
                <td>${animal.tipo}</td>
                <td>${animal.disponibilidade}</td>
                <td><button class="btn-form" onclick="verCarteirinha(${animal.id})">Ver Carteirinha</button></td>
                <td><button class="btn-form" onclick="editarAnimal(${animal.id})">Editar</button></td>
                <td><button class="btn-form" onclick="excluirAnimal(${animal.id})">Excluir</button></td>
            `;
            table.appendChild(tr);
        });
    } catch (error) {
        console.error('Error fetching animais:', error);
    }
}