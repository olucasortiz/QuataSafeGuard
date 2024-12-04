const form = document.getElementById("form-agendamento");
<<<<<<< HEAD
const BASE_URL = "http://localhost:8080/api/agendamentos";


=======
const BASE_URL = "http://localhost:8080/api/agendamentos"; // Atualize conforme necessário
>>>>>>> branch-ortiz

form.addEventListener("submit", async (event) => {
    event.preventDefault();

<<<<<<< HEAD
    const cpfDoador = document.getElementById("cpf-doador").value.trim();
    const nomeAnimal = document.getElementById("nome-animal").value.trim();
    const idadeAnimal = parseInt(document.getElementById("idade-animal").value.trim());
    const tipoAnimal = document.getElementById("tipo-animal").value.trim();
    const dataHora = new Date(document.getElementById("data-hora").value).toISOString();
    const informacoes = document.getElementById("informacoes").value.trim();
    const carteiraVacina = document.getElementById("carteira-vacina").files[0];

    // Validar se todos os campos obrigatórios estão preenchidos
    if (!cpfDoador || !nomeAnimal || !idadeAnimal || !tipoAnimal || !dataHora || !carteiraVacina) {
        alert("Por favor, preencha todos os campos obrigatórios e envie a carteira de vacinação.");
        return;
    }

    // Garantir que o tipo do animal seja válido
    if (!["Cachorro", "Gato"].includes(tipoAnimal)) {
        alert("Tipo de animal inválido. Selecione 'Cachorro' ou 'Gato'.");
=======
    const cpfDoador = document.getElementById("cpf-doador").value;
    const tipoAnimal = document.getElementById("tipo-animal").value;
    const dataHora = document.getElementById("data-hora").value;
    const informacoes = document.getElementById("informacoes").value;
    const carteiraVacina = document.getElementById("carteira-vacina").files[0]; 
    if (!carteiraVacina) {
        alert("Por favor, envie uma imagem da carteira de vacinação.");
>>>>>>> branch-ortiz
        return;
    }

    const formData = new FormData();
    formData.append("cpfDoador", cpfDoador);
<<<<<<< HEAD
    formData.append("nomeAnimal", nomeAnimal);
    formData.append("idadeAnimal", idadeAnimal);
=======
>>>>>>> branch-ortiz
    formData.append("tipoAnimal", tipoAnimal);
    formData.append("dataHora", dataHora);
    formData.append("informacoes", informacoes);
    formData.append("carteiraVacina", carteiraVacina);
<<<<<<< HEAD
    
=======
>>>>>>> branch-ortiz

    try {
        const response = await fetch(BASE_URL, {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
<<<<<<< HEAD
            alert("Entrega e agendamento registrados com sucesso!");
            form.reset();
        } else {
            const errorResponse = await response.json();
            alert(`Erro ao registrar entrega: ${errorResponse.message}`);
        }
    } catch (error) {
        console.error("Erro ao conectar com o servidor:", error);
        alert("Erro ao conectar com o servidor. Tente novamente mais tarde.");
=======
            alert("Entrega agendada com sucesso!");
            form.reset();
        } else {
            alert("Erro ao agendar entrega. Verifique os dados e tente novamente.");
        }
    } catch (error) {
        console.error("Erro ao conectar com o servidor:", error);
        alert("Erro ao agendar entrega. Tente novamente mais tarde.");
>>>>>>> branch-ortiz
    }
});
