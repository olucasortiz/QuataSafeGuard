// Função para carregar todos os animais
fetch('http://localhost:8080/animais') // Endpoint correto para listar todos os animais
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro ao buscar animais: ${response.status}`); // Verifica se a resposta é válida
        }
        return response.json();
    })
    .then(data => {
        console.log('Animais carregados:', data); // Log para verificar os dados
        const tabela = document.getElementById('animais-tabela');
        tabela.innerHTML = ''; // Limpa a tabela antes de preenchê-la

        data.forEach(animal => {
            const linha = document.createElement('tr');
            linha.innerHTML = `
                <td>${animal.id}</td>
                <td>${animal.nome}</td>
                <td>${animal.idade}</td>
                <td>${animal.tipo}</td>
                <td>${animal.disponibilidade ? 'Sim' : 'Não'}</td>
                <td>
                    <button class="btn-entrega" onclick="window.location.href='AlterarAnimal.html?id=${animal.id}'">Alterar</button>
                    <button class="btn-entrega" onclick="deletarAnimal(${animal.id})">Deletar</button>
                </td>
            `;
            tabela.appendChild(linha);
        });
    })
    .catch(error => console.error('Erro ao carregar animais:', error)); // Captura e exibe erros

// Função para deletar um animal
function deletarAnimal(id) {
    if (confirm('Tem certeza que deseja deletar este animal?')) {
        fetch(`http://localhost:8080/animais/${id}`, { method: 'DELETE' }) // Endpoint para deletar animal por ID
            .then(response => {
                if (response.ok) {
                    alert('Animal deletado com sucesso!');
                    window.location.reload(); // Recarrega a página após deletar
                } else {
                    alert(`Erro ao deletar animal: ${response.status}`);
                }
            })
            .catch(error => console.error('Erro ao deletar animal:', error));
    }
}
