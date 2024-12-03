function filterTable() {
    const vacinaFilter = document.getElementById('vacina-filter').value.toLowerCase();
    const loteFilter = document.getElementById('lote-filter').value.toLowerCase();
    const dataFilter = document.getElementById('data-filter').value;
    const table = document.getElementById('tabela-vacina');
    const tr = table.getElementsByTagName('tr');

    for (let i = 1; i < tr.length; i++) {
        const tdVacina = tr[i].getElementsByTagName('td')[0];
        const tdLote = tr[i].getElementsByTagName('td')[1];
        const tdData = tr[i].getElementsByTagName('td')[2];
        if (tdVacina && tdLote && tdData) {
            const vacinaValue = tdVacina.textContent || tdVacina.innerText;
            const loteValue = tdLote.textContent || tdLote.innerText;
            const dataValue = tdData.textContent || tdData.innerText;

            if (
                (vacinaValue.toLowerCase().indexOf(vacinaFilter) > -1 || !vacinaFilter) &&
                (loteValue.toLowerCase().indexOf(loteFilter) > -1 || !loteFilter) &&
                (dataValue.indexOf(dataFilter) > -1 || !dataFilter)
            ) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

async function excluirVacina(id) {
    if (!confirm('Deseja realmente excluir esta vacina?')) {
        return;
    }   

    try {
        const response = await fetch(`http://localhost:8080/vacinas_carteirinha/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error('Erro ao excluir vacina.');
        }
        alert('Vacina excluída com sucesso!');
        location.reload();
    } catch (error) {
        alert(error.message);
    }
}

document.addEventListener('DOMContentLoaded', async function() {
    try {
        const animalResponse = await fetch('http://localhost:8080/carteira_vacina', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!animalResponse.ok) {
            throw new Error(`HTTP error! status: ${animalResponse.status}`);
        }
        const data = await animalResponse.json();
        console.log(data);
    } catch (error) {
        alert(error.message);
    }
    try {
        const vacinasResponse = await fetch('http://localhost:8080/vacinas', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!vacinasResponse.ok) {
            throw new Error(`HTTP error! status: ${vacinasResponse.status}`);
        }
        const vacinas = await vacinasResponse.json();
        if (!Array.isArray(vacinas)) {
            throw new Error('Response is not an array');
        }

        const selectVacina = document.getElementById('select-vacina');
        vacinas.forEach(vacina => {
            const option = document.createElement('option');
            option.value = vacina.idVacina;
            option.innerText = vacina.nome;
            selectVacina.appendChild(option);
        });
    } catch (error) {
        alert(error.message);
    }

    const form = document.getElementById('vacinaForm');
    form.addEventListener('submit',  function(event) {
        event.preventDefault(); 

        const data = document.getElementById('data').value;
        const idCarteiraVacina = document.getElementById('carteira-id').value;
        const idVacina = document.getElementById('select-vacina').value;

        console.log(data, idCarteiraVacina, idVacina);

        if (!data || !idCarteiraVacina || !idVacina) {
            alert('Por favor, preencha todos os campos.');
            return;
        }

        fetch('http://localhost:8080/vacinas_carteirinha', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                data,
                idCarteiraVacina,
                idVacina
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao salvar vacina.');
            }
            return response.json();
        })
        .then(data => {
            alert('Vacina salva com sucesso!');
            location.reload();
        })
        .catch(error => {
            alert(error.message);
        });
    });
});

async function listarVacina() {
    const urlParams = new URLSearchParams(window.location.search);
    const animalId = urlParams.get('animalId');

    try {
        const carteiraResponse = await fetch(`http://localhost:8080/carteira_vacina/animal/${animalId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!carteiraResponse.ok) {
            throw new Error(`HTTP error! status: ${carteiraResponse.status}`);
        }
        const carteira = await carteiraResponse.json();
        
        const carteirinhaDetails = document.getElementById('carteirinhaDetails');
        carteirinhaDetails.innerHTML = `
            <tr> 
                <th>Vacina</th>
                <th>Lote</th>
                <th>Data da Aplicação</th>
                <th>Excluir</th>
            </tr>

            <input type="hidden" id="carteira-id" value="${carteira.id}">            
        `;
        
        try {
            const vacinasResponse = await fetch(`http://localhost:8080/vacinas_carteirinha/${carteira.id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            if (!vacinasResponse.ok) {
                throw new Error(`HTTP error! status: ${vacinasResponse.status}`);
            }
            const vacinas = await vacinasResponse.json();
            if(!Array.isArray(vacinas)) {
                throw new Error('Response is not an array');
            }

            if (vacinas.length === 0) {
                const vacinasDetails = document.createElement('tr');
                vacinasDetails.innerHTML = `
                    <td colspan="5">Não há vacinas tomadas</td>
                `;
                carteirinhaDetails.appendChild(vacinasDetails);
            } else {
                vacinas.forEach(vacinaCarteirinha => {
                    const vacinasDetails = document.createElement('tr');
                    vacinasDetails.setAttribute('data-id', vacinaCarteirinha.id);

                    vacinasDetails.innerHTML = `
                        <td>${vacinaCarteirinha.vacina.nome}</td>
                        <td>${vacinaCarteirinha.vacina.lote}</td>
                        <td>${vacinaCarteirinha.data}</td>
                        <td><button onclick="excluirVacina(${vacinaCarteirinha.id})">Excluir</button></td>
                    `;
                    carteirinhaDetails.appendChild(vacinasDetails);
                });
            }
        } catch (error) {
            console.error(error.message);
        }

    } catch (error) {
        console.error(error.message);
    }
}

// Chamar a função listarVacina quando a página carregar
document.addEventListener('DOMContentLoaded', listarVacina);