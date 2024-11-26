document.getElementById("doacaoForm").addEventListener("submit", registrarDoacao);

function registrarDoacao(event) {
    event.preventDefault();

    const doador = document.getElementById("doador").value || "Anônimo";  // Define 'Anônimo' caso o nome não seja informado
    const item = document.getElementById("item").value;
    const quantidade = document.getElementById("quantidade").value;

    // Validação simples
    if (!item || !quantidade || quantidade <= 0) {
        alert("Por favor, preencha todos os campos corretamente.");
        return;
    }

    const doacao = JSON.stringify({
        doador: doador,
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
            document.getElementById("doacaoForm").reset();  // Limpa o formulário
        })
        .catch(error => {
            alert("Erro ao registrar doação: " + error.message);
        });
}
