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
    } catch (error) {
        alert(error.message);
    }
});

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
}

// Atualizar status da entrega
async function atualizarStatus(id, novoStatus) {
    try {
        const response = await fetch(`${BASE_URL}/${id}/status?status=${novoStatus}`, {
            method: "PUT",
        });

        if (!response.ok) throw new Error("Erro ao atualizar status.");

        alert("Status atualizado com sucesso!");
        carregarEntregasPendentes(); // Recarrega todas as entregas pendentes após atualização
    } catch (error) {
        alert(error.message);
    }
}

// Carrega todas as entregas pendentes ao iniciar
carregarEntregasPendentes();
