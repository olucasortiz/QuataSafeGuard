<<<<<<< HEAD
const BASE_URL = "http://localhost:8080/entregas"; // URL do backend
const tabela = document.getElementById("tabela-entregas").querySelector("tbody");
const formFiltro = document.getElementById("form-filtro");

// Carregar todas as entregas pendentes ao carregar a página
async function carregarEntregasPendentes() {
    try {
        const response = await fetch(`${BASE_URL}/pendentes`);
        if (!response.ok) throw new Error("Erro ao carregar entregas pendentes.");

        const entregas = await response.json();
        renderTabelaEntregas(entregas); // Renderiza todas as entregas pendentes
    } catch (error) {
        alert(error.message);
    }
}

// Filtrar entregas por data ou ID do animal
formFiltro.addEventListener("submit", async (event) => {
    event.preventDefault();

    const dataEntrega = document.getElementById("filtro-data").value;
    const animalId = document.getElementById("filtro-animal-id").value;

    let url = BASE_URL;
    if (dataEntrega) {
        url += `/data?data=${dataEntrega}`;
    } else if (animalId) {
        url += `/animal/${animalId}`;
    } else {
        alert("Preencha pelo menos um filtro.");
        return;
    }

    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error("Erro ao filtrar entregas.");

        const entregas = await response.json();
        renderTabelaEntregas(entregas); // Renderiza apenas os resultados do filtro
=======
const BASE_URL = "http://localhost:8080/api/entregas"; // URL do backend
const tabela = document.getElementById("tabela-entregas").querySelector("tbody");
const form = document.getElementById("form-entrega");

// Registrar uma nova entrega
form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const animalId = document.getElementById("animal-id").value;
    const funcionarioId = document.getElementById("funcionario-id").value;
    const agendaId = document.getElementById("agenda-id").value;

    const novaEntrega = {
        animal: { id: parseInt(animalId) },
        funcionario: { id: parseInt(funcionarioId) },
        agenda: { idAgenda: parseInt(agendaId) },
        statusEntrega: "Pendente",
    };

    try {
        const response = await fetch(BASE_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(novaEntrega),
        });

        if (!response.ok) throw new Error("Erro ao registrar entrega.");

        alert("Entrega registrada com sucesso!");
        carregarEntregasPendentes();
        form.reset();
>>>>>>> branch-ortiz
    } catch (error) {
        alert(error.message);
    }
});

<<<<<<< HEAD
// Renderizar a tabela com entregas
function renderTabelaEntregas(entregas) {
    tabela.innerHTML = ""; // Limpa a tabela
    entregas.forEach((entrega) => {
        tabela.innerHTML += `
            <tr>
                <td>${entrega.id}</td>
                <td>${entrega.animal.id}</td>
                <td>${new Date(entrega.dataEntrega).toLocaleDateString()}</td>
                <td class="${entrega.status === "Pendente" ? "status-pendente" : "status-concluido"}">${entrega.status}</td>
                <td>
                    <button onclick="atualizarStatus(${entrega.id}, 'Concluído')">Concluir</button>
                </td>
            </tr>
        `;
    });
=======
// Carregar entregas pendentes
async function carregarEntregasPendentes() {
    try {
        const response = await fetch(`${BASE_URL}/pendentes?status=Pendente`);
        if (!response.ok) throw new Error("Erro ao carregar entregas pendentes.");

        const entregas = await response.json();
        tabela.innerHTML = "";
        entregas.forEach((entrega) => {
            tabela.innerHTML += `
                <tr>
                    <td>${entrega.idEntregaAnimal}</td>
                    <td>${entrega.animal.id}</td>
                    <td>${new Date(entrega.dataEntrega).toLocaleDateString()}</td>
                    <td class="${entrega.statusEntrega === "Pendente" ? "status-pendente" : "status-concluido"}">${entrega.statusEntrega}</td>
                    <td>
                        <button onclick="atualizarStatus(${entrega.idEntregaAnimal}, 'Concluído')">Concluir</button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        alert(error.message);
    }
>>>>>>> branch-ortiz
}

// Atualizar status da entrega
async function atualizarStatus(id, novoStatus) {
    try {
        const response = await fetch(`${BASE_URL}/${id}/status?status=${novoStatus}`, {
<<<<<<< HEAD
            method: "PUT",
=======
            method: "PATCH",
>>>>>>> branch-ortiz
        });

        if (!response.ok) throw new Error("Erro ao atualizar status.");

        alert("Status atualizado com sucesso!");
<<<<<<< HEAD
        carregarEntregasPendentes(); // Recarrega todas as entregas pendentes após atualização
=======
        carregarEntregasPendentes();
>>>>>>> branch-ortiz
    } catch (error) {
        alert(error.message);
    }
}

<<<<<<< HEAD
// Carrega todas as entregas pendentes ao iniciar
=======
>>>>>>> branch-ortiz
carregarEntregasPendentes();
