document.getElementById("doacaoForm").addEventListener("submit", registrarDoacao);

function registrarDoacao(event) {
    event.preventDefault();
    const doador = document.getElementById("doador").value;
    const item = document.getElementById("item").value;
    const quantidade = document.getElementById("quantidade").value;

    const doacao = JSON.stringify({
        doador: doador || "Anônimo",
        itensDoacao: [
            { nome: item, quantidade: parseInt(quantidade, 10) }
        ]
    });

    fetch("http://localhost:8080/api/doacoes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: doacao
    })
        .then(response => response.json())
        .then(result => {
            alert("Doação registrada com sucesso!");
            document.getElementById("doacaoForm").reset();
        })
        .catch(error => alert("Erro ao registrar doação: " + error.message));
}
