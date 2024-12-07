const BASE_URL = "http://localhost:8080/animais";
const params = new URLSearchParams(window.location.search);
const animalId = params.get("id");

document.addEventListener("DOMContentLoaded", async () => {
    if (!animalId) {
        alert("Animal não encontrado.");
        return;
    }

    try {
        const response = await fetch(`${BASE_URL}/${animalId}`);
        const animal = await response.json();

        document.getElementById("nome").value = animal.nome;
        document.getElementById("idade").value = animal.idade;
        document.getElementById("tipo").value = animal.tipo;
        const disponibilidade = document.getElementById("disponibilidade").value.trim();
        formData.append("disponibilidade", disponibilidade);

    } catch (error) {
        console.error("Erro ao carregar animal:", error);
        alert("Erro ao carregar os dados do animal.");
    }
});

document.getElementById("form-alterar-animal").addEventListener("submit", async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    const idade = document.getElementById("idade").value.trim();
    const tipo = document.getElementById("tipo").value.trim();
    const carteiraVacina = document.getElementById("carteiraVacina").files[0];

    let isValid = true;

    // Limpar mensagens de erro
    document.querySelectorAll(".error-message").forEach((el) => (el.textContent = ""));
    document.querySelectorAll("input, select").forEach((el) => el.classList.remove("input-error"));

    // Validações simples
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

    if (!isValid) return;

    const formData = new FormData();
    formData.append("nome", nome);
    formData.append("idade", idade);
    formData.append("tipo", tipo);
    if (carteiraVacina) {
        formData.append("carteiraVacina", carteiraVacina);
    }

    try {
        const response = await fetch(`${BASE_URL}/${animalId}`, {
            method: "PUT",
            body: formData,
        });

        if (response.ok) {
            alert("Animal atualizado com sucesso!");
            window.location.href = "AnimalTela.html";
        } else {
            alert("Erro ao atualizar animal.");
        }
    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao conectar com o servidor.");
    }
});
