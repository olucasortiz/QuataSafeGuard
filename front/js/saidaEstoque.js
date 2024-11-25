document.addEventListener("DOMContentLoaded", () => {
    carregarProdutos();
});

function carregarProdutos() {
    fetch("http://localhost:8080/api/estoque", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(data => {
            const estoqueContainer = document.getElementById("estoqueContainer");
            estoqueContainer.innerHTML = data.map(produto => `
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">${produto.nome}</h5>
                        <p class="card-text">Quantidade disponível: ${produto.quantidadeEstoque}</p>
                        <input type="number" id="quantidade-${produto.id}" class="form-control mb-2" placeholder="Quantidade a retirar">
                        <button class="btn btn-danger" onclick="registrarSaida(${produto.id})">Registrar Saída</button>
                    </div>
                </div>
            `).join("");
        })
        .catch(error => console.error("Erro ao carregar estoque:", error));
}

function registrarSaida(id) {
    const quantidade = document.getElementById(`quantidade-${id}`).value;

    if (!quantidade || quantidade <= 0) {
        alert("Por favor, insira uma quantidade válida.");
        return;
    }

    fetch("http://localhost:8080/api/saida-estoque/saida", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            produtoId: id,
            quantidade: parseInt(quantidade, 10)
        })
    })
        .then(() => {
            alert("Saída registrada com sucesso!");
            carregarProdutos();
        })
        .catch(error => alert("Erro ao registrar saída: " + error.message));
}
