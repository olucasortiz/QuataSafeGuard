const BASE_URL = "http://localhost:8080/animais";

document.getElementById("form-cadastrar-animal").addEventListener("submit", async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    const idade = document.getElementById("idade").value.trim();
    const tipo = document.getElementById("tipo").value.trim();
    const carteiraVacina = document.getElementById("carteiraVacina").files[0];

    let isValid = true;


    document.querySelectorAll(".error-message").forEach((el) => (el.textContent = ""));
    document.querySelectorAll("input, select").forEach((el) => el.classList.remove("input-error"));

    if (!nome) {
        document.getElementById("nome-error").textContent = "O nome é obrigatório.";
        document.getElementById("nome").classList.add("input-error");
        isValid = false;
    }

    if (!idade || isNaN(idade) || idade <= 0) {
        document.getElementById("idade-error").textContent = "A idade deve ser um número positivo.";
        document.getElementById("idade").classList.add("input-error");
        isValid = false;
    }

    if (!tipo) {
        document.getElementById("tipo-error").textContent = "O tipo é obrigatório.";
        document.getElementById("tipo").classList.add("input-error");
        isValid = false;
    }

    if (!carteiraVacina) {
        document.getElementById("carteiraVacina-error").textContent = "A carteira de vacinação é obrigatória.";
        document.getElementById("carteiraVacina").classList.add("input-error");
        isValid = false;
    }

    if (!isValid) return;

    const formData = new FormData();
    formData.append("nome", nome);
    formData.append("idade", idade);
    formData.append("tipo", tipo);
    formData.append("carteiraVacina", carteiraVacina);

    try {
        const response = await fetch(BASE_URL, {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            alert("Animal cadastrado com sucesso!");
            document.getElementById("form-cadastrar-animal").reset();
        } else {
            alert("Erro ao cadastrar animal.");
        }
    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao conectar com o servidor.");
    }
});
