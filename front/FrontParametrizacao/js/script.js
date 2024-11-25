function validateLogin(event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (email === "admin" && password === "admin") {
        fetch('http://localhost:8080/api/empresa/existe-param', { method: 'GET' })
            .then((response) => response.json())
            .then((result) => {
                const { existe } = result;
                if (existe) {
                    window.location.href = "perfil.html";
                } else {
                    window.location.href = "formularioParametrizacao.html";
                }
            })
            .catch((error) => console.error("Erro ao verificar parametrização:", error));
    } else {
        alert("Login inválido!");
    }
}

function parametrizar(event) {
    event.preventDefault();

    const empresa = JSON.stringify({
        nomeFantasia: document.getElementById("nomeFantasia").value,
        razaoSocial: document.getElementById("razaoSocial").value,
        cnpj: document.getElementById("cnpj").value,
        endereco: document.getElementById("endereco").value,
        bairro: document.getElementById("bairro").value,
        cidade: document.getElementById("cidade").value,
        uf: document.getElementById("uf").value,
        telefone: document.getElementById("telefone").value,
        site: document.getElementById("site").value,
        logoPequeno: document.getElementById("logoPequeno").value,
        logoGrande: document.getElementById("logoGrande").value,
    });

    fetch("http://localhost:8080/api/empresa/parametrizar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: empresa,
    })
        .then((response) => {
            if (response.ok) {
                alert("Parametrização salva com sucesso!");
                window.location.href = "perfil.html";
            } else {
                throw new Error("Erro ao salvar parametrização");
            }
        })
        .catch((error) => alert("Erro ao salvar dados: " + error.message));
}
