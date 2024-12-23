// Quando a página carregar, chama a função para carregar os produtos e o histórico de saídas
document.addEventListener("DOMContentLoaded", function () {
    carregarProdutos();   // Carrega a lista de produtos no estoque
    carregarHistoricoSaidas(); // Carrega o histórico de saídas de estoque
});

// Função para carregar os produtos disponíveis no estoque
function carregarProdutos() {
    fetch("http://localhost:8080/api/produto/get-all-produto", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(data => {
            const produtoSelect = document.getElementById("produto");

            // Verifica se a resposta é um array
            if (Array.isArray(data)) {
                produtoSelect.innerHTML = ""
                data.forEach(produto => {
                    const option = document.createElement("option");
                    option.value = produto.idProduto;
                    option.textContent = `${produto.nomeProduto} (${produto.quantidadeEstoque} disponíveis)`;
                    produtoSelect.appendChild(option);
                });
            } else {
                console.error("Erro: Dados não são um array", data);
            }
        })
        .catch(error => {
            console.error("Erro ao carregar produtos:", error);
        });
}

// Função para registrar a saída de estoque
const buttonSubmit = document.querySelector('#saidaForm .btn');
document.getElementById("saidaForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const produtoId = document.getElementById("produto").value;
    const quantidade = document.getElementById("quantidade").value;
    const motivo = document.getElementById("motivo").value;

    // Validação simples
    if (!produtoId || !quantidade || quantidade <= 0 || !motivo) {
        alert("Por favor, preencha todos os campos corretamente.");
        return;
    }

    const url = `http://localhost:8080/api/saida-estoque?idProduto=${produtoId}&quantidade=${quantidade}&motivo=${motivo}`;

    buttonSubmit.innerHTML = 'Carregando...';
    buttonSubmit.setAttribute('disabled', 'disabled');

    fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(result => {
            alert("Saída de estoque registrada com sucesso!");
            document.getElementById("saidaForm").reset();
            carregarHistoricoSaidas();  // Atualiza o histórico de saídas
            carregarProdutos();
        })
        .catch(error => {
            alert("Erro ao registrar saída de estoque: " + error.message);
        })
        .finally(() => {
            buttonSubmit.innerHTML = 'Registrar Saída de Estoque';
            buttonSubmit.removeAttribute('disabled');
        })
    carregarHistoricoSaidas();
});

// Função para carregar o histórico de saídas de estoque
function carregarHistoricoSaidas() {
    fetch("http://localhost:8080/api/saida-estoque", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const tabela = document.getElementById("historicoTabela").getElementsByTagName('tbody')[0];

            // Limpa a tabela antes de inserir novos dados
            tabela.innerHTML = '';

            // Insere as novas linhas na tabela com o histórico de saídas
            data.forEach(saida => {
                const row = tabela.insertRow();

                row.insertCell(0).textContent = saida.produto.idProduto;
                row.insertCell(1).textContent = saida.produto.nomeProduto;
                row.insertCell(2).textContent = saida.qtde;
                row.insertCell(3).textContent = saida.motivo || "Sem motivo";
                row.insertCell(4).textContent = new Date(saida.dataSaida).toLocaleDateString({ language: 'pt-br' });

                const actionsCell = row.insertCell(5); // Coluna de ações
                const alterarBtn = document.createElement("button");
                alterarBtn.classList.add("btn", "btn-warning", "btn-sm", "me-2");
                alterarBtn.textContent = "Alterar";
                alterarBtn.onclick = () => alterarSaida(saida.idRegistroSaidaItens);

                const removerBtn = document.createElement("button");
                removerBtn.classList.add("btn", "btn-danger", "btn-sm");
                removerBtn.textContent = "Remover";
                removerBtn.onclick = () => removerSaida(saida.idRegistroSaidaItens);

                // Adiciona os botões à célula
                actionsCell.appendChild(alterarBtn);
                actionsCell.appendChild(removerBtn);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar histórico de saídas:", error);
        });
}
function alterarSaida(idRegistro) {
    // Exemplo de como poderia ser feita a alteração.
    // Na prática, você pode abrir um modal ou um formulário com os dados para edição.
    const novaQuantidade = prompt("Digite a nova quantidade:");
    const novoMotivo = prompt("Digite o novo motivo:");

    if (novaQuantidade && novoMotivo) {
        const url = `http://localhost:8080/api/saida-estoque/${idRegistro}?novaQuantidade=${novaQuantidade}&novoMotivo=${novoMotivo}`;

        fetch(url, {
            method: "PUT",
            headers: { "Content-Type": "application/json" }
        })
            .then(response => response.json())
            .then(data => {
                alert("Saída de estoque atualizada com sucesso!");
                carregarHistoricoSaidas();  // Atualiza o histórico após a alteração
            })
            .catch(error => {
                alert("Erro ao atualizar saída de estoque: " + error.message);
            });
    }
}

function removerSaida(id) {
    const url = `http://localhost:8080/api/saida-estoque/${id}`;

    fetch(url, {
        method: 'DELETE',
        headers: { "Content-Type": "application/json" }
    })
        .then(response => {
            if (response.ok) {
                alert("Saída de estoque excluída com sucesso!");
                carregarHistoricoSaidas();  // Atualiza o histórico
            } else {
                alert("Erro ao excluir saída de estoque.");
            }
        })
        .catch(error => {
            alert("Erro ao conectar com o servidor.");
            console.error(error);
        });
}
