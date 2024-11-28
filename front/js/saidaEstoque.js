// Quando a página carregar, chama a função para carregar os produtos e o histórico de saídas
document.addEventListener("DOMContentLoaded", function() {
    carregarProdutos();
    carregarHistoricoSaidas();
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

        if (Array.isArray(data)) {
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
document.getElementById("saidaForm").addEventListener("submit", function(event) {
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

    fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    })
    .then(response => response.json())
    .then(result => {
        alert("Saída de estoque registrada com sucesso!");
        document.getElementById("saidaForm").reset();
        carregarHistoricoSaidas();  // Atualiza o histórico de saídas
    })
    .catch(error => {
        alert("Erro ao registrar saída de estoque: " + error.message);
    });
});

// Função para carregar o histórico de saídas de estoque
function carregarHistoricoSaidas() {
    fetch("http://localhost:8080/api/saida-estoque", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
    .then(response => response.json())
    .then(data => {
        const tabela = document.getElementById("historicoTabela").getElementsByTagName('tbody')[0];

        // Limpa a tabela antes de inserir novos dados
        tabela.innerHTML = '';

        data.forEach(saida => {
            const row = tabela.insertRow();

            row.insertCell(0).textContent = saida.produto.idProduto;
            row.insertCell(1).textContent = saida.produto.nomeProduto;
            row.insertCell(2).textContent = saida.quantidade;
            row.insertCell(3).textContent = saida.motivo || "Sem motivo";
            row.insertCell(4).textContent = saida.dataSaida;
        });
    })
    .catch(error => {
        console.error("Erro ao carregar histórico de saídas:", error);
    });
}
