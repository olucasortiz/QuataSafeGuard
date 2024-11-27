document.addEventListener("DOMContentLoaded", () => {
    carregarProdutosDisponiveis();
    
    const doacaoForm = document.getElementById("doacaoForm");

    doacaoForm.addEventListener("submit", (event) => {
        event.preventDefault(); // Evita o envio do formulário

        const produtoId = document.getElementById("produto").value;
        const quantidade = document.getElementById("quantidade").value;

        if (!quantidade || quantidade <= 0) {
            alert("Por favor, insira uma quantidade válida.");
            return;
        }

        const doacaoData = {
            produtoId: produtoId,
            quantidade: parseInt(quantidade, 10)
        };

        fetch("http://localhost:8080/api/doacoes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(doacaoData)
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById("mensagemConfirmacao").style.display = "block";
            doacaoForm.reset();
        })
        .catch(error => {
            alert("Erro: " + error.message);
        });
    });
});

function carregarProdutosDisponiveis() {
    fetch("http://localhost:8080/api/produtos")  // Endpoint que retorna os produtos disponíveis
        .then(response => response.json())
        .then(data => {
            const produtoSelect = document.getElementById("produto");
            data.forEach(produto => {
                const option = document.createElement("option");
                option.value = produto.idProduto;
                option.textContent = `${produto.nomeProduto} (Disponível: ${produto.quantidadeEstoque})`;
                produtoSelect.appendChild(option);
            });
        })
        .catch(error => console.error("Erro ao carregar produtos:", error));
}
