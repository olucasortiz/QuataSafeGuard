function filterTable() {
    const nameFilter = document.getElementById('nameFilter').value.toLowerCase();
    const table = document.getElementById('animal-table');
    const tr = table.getElementsByTagName('tr');

    for (let i = 1; i < tr.length; i++) {
        const tdName = tr[i].getElementsByTagName('td')[1];
        if (tdName) {
            const nameValue = tdName.textContent || tdName.innerText;

            if (nameValue.toLowerCase().indexOf(nameFilter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

async function cancelarEdicao(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/produto/get-produto/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao carregar produto.');
        }
        const produto = await response.json();

        const row = document.querySelector(`tr[data-id='${id}']`);
        if (!row) {
            throw new Error('Linha do produto não encontrada.');
        }

        row.innerHTML = `
            <td>${id}</td>
            <td>${produto.nome}</td>
            <td>${produto.descricao}</td>
            <td>${produto.quantidade}</td>
            <td><button class="btn-form" onclick="editarProduto(${id})">Editar</button></td>
            <td><button class="btn-form" onclick="excluirProduto(${id})">Excluir</button></td>
        `;
    } catch (error) {
        alert(error.message);
    }
}

async function editarProduto(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/produto/get-produto/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao carregar produto.');
        }
        const produto = await response.json();

        // Encontrar a linha correspondente ao produto
        const row = document.querySelector(`tr[data-id='${id}']`);
        if (!row) {
            throw new Error('Linha do produto não encontrada.');
        }

        // Transformar a linha em um formulário
        row.innerHTML = `
            <td>${id}</td>
            <td><input type="text" id="nome-${id}" value="${produto.nomeProduto}"></td>
            <td><textarea id="descricao-${id}" required>${produto.descricaoProduto}</textarea></td>
            <td><input type="number" id="quantidade-${id}" value="${produto.quantidadeEstoque}" required></td>
            <td><button class="btn-form" onclick="salvarEdicao(${id})">Salvar</button></td>
            <td><button class="btn-form" onclick="cancelarEdicao(${id})">Cancelar</button></td>
        `;
    } catch (error) {
        alert(error.message);
    }
}

async function salvarEdicao(id) {
    try {
        // Obter os valores atualizados do formulário
        const nomeProduto = document.getElementById(`nome-${id}`).value;
        const descricaoProduto = document.getElementById(`descricao-${id}`).value;
        const quantidadeEstoque = document.getElementById(`quantidade-${id}`).value;

        // Enviar a atualização com todos os campos
        const responsePut = await fetch(`http://localhost:8080/api/produto/update-produto/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nomeProduto, descricaoProduto, quantidadeEstoque })
        });

        if (!responsePut.ok) {
            throw new Error('Erro ao salvar produto.');
        }

        alert('Produto atualizado com sucesso!');
        listarProdutos();
    } catch (error) {
        alert(error.message);
    }
}

async function excluirProduto(id) {
    // Alerta de confirmação
    if (!confirm('Deseja realmente excluir o produto?')) {
        return;
    }
    try {
        const response = await fetch(`http://localhost:8080/api/produto/delete-produto/${id}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Erro ao excluir produto.');
        }
        alert('Produto excluído com sucesso!');
        listarProdutos();
    } catch (error) {
        alert(error.message);
    }
}

document.addEventListener('DOMContentLoaded', async function() {
    const form = document.getElementById('form-produto');
    
    if (!form) {
        console.error('Formulário não encontrado!');
        return;
    }

    form.addEventListener('submit', async function(event) {
        event.preventDefault();
        const Data = {
            nomeProduto: document.getElementById('nome-produto').value,
            descricaoProduto: document.getElementById('descricao-produto').value,
            quantidadeEstoque: document.getElementById('quantidade-produto').value,
        };

        try {
            const response = await fetch('http://localhost:8080/api/produto/create-produto', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(Data)
            });

            if (!response.ok) {
                throw new Error('Erro ao cadastrar produto.');
            }
            alert('Produto adicionado com sucesso!');
            window.location.reload();
            form.reset();
        } catch (error) {
            alert(error.message);
        }
    });
});
// Lista Todos os Produtos
async function listarProdutos() {
    try {
        const response = await fetch('http://localhost:8080/api/produto/get-all-produto');
        if (!response.ok) {
            throw new Error('Erro ao carregar produtos.');
        }
        const produtos = await response.json();

        const table = document.getElementById('animal-table');
        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Quantidade</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
        `;
        for (const produto of produtos) {
            table.innerHTML += `
                <tr data-id="${produto.idProduto}">
                    <td>${produto.idProduto}</td>
                    <td>${produto.nomeProduto}</td>
                    <td>${produto.descricaoProduto}</td>
                    <td>${produto.quantidadeEstoque}</td>
                    <td><button class="btn-form" onclick="editarProduto(${produto.idProduto})">Editar</button></td>
                    <td><button class="btn-form" onclick="excluirProduto(${produto.idProduto})">Excluir</button></td>
                </tr>
            `;
        }
    }
    catch (error) {
        console.log(error.message);
    }
}