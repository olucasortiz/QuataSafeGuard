document.addEventListener("DOMContentLoaded", function() {
    carregarProdutos();
});

function carregarProdutos() {
    fetch("http://localhost:8080/api/produto/get-all-produto", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
    .then(response => response.json())
    .then(data => {
        const produtoSelect = document.getElementById("produto");

        // Verifique se a resposta é um array
        if (Array.isArray(data)) {
            data.forEach(produto => {
                const option = document.createElement("option");
                option.value = produto.idProduto;  // Usa idProduto como valor
                option.textContent = `${produto.nomeProduto} (${produto.quantidadeEstoque} disponíveis)`;  // Exibe o nome e quantidade
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

document.getElementById("doacaoForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const doador = document.getElementById("doador").value || "Anônimo";  // Define 'Anônimo' caso o nome não seja informado
    const produtoId = document.getElementById("produto").value;
    const quantidade = document.getElementById("quantidade").value;
    const funcionarioId = 1; // O ID do funcionário deve ser capturado dinamicamente, no caso está fixo como 1 para exemplo
    const caixaId = 1; // O ID do caixa deve ser capturado dinamicamente, no caso está fixo como 1 para exemplo

    // Validação simples
    if (!produtoId || !quantidade || quantidade <= 0) {
        alert("Por favor, preencha todos os campos corretamente.");
        return;
    }

    const doacao = {
        data: new Date().toISOString().split("T")[0],  // Data atual
        valor: 100.0,  // Valor da doação, pode ser ajustado conforme necessário
        quantidadeItens: 1,  // Número de itens na doação
        funcionario: {
            idFuncionario: funcionarioId
        },
        caixa: {
            id: caixaId
        },
        doador: {
            cpf: doador // Pode ser CPF ou outro identificador, dependendo da implementação
        },
        itensDoacao: [
            {
                produto: {
                    idProduto: parseInt(produtoId)
                },
                quantidade: parseInt(quantidade)
            }
        ]
    };

    fetch("http://localhost:8080/api/doacoes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(doacao)
    })
    .then(response => response.json())
    .then(result => {
        alert("Doação registrada com sucesso!");
        document.getElementById("doacaoForm").reset();  // Limpa o formulário
    })
    .catch(error => {
        alert("Erro ao registrar doação: " + error.message);
    });
});
