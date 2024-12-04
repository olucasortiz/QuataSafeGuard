// URL base da API
const API_BASE_URL = "http://localhost:8080/api";

// Função genérica para realizar chamadas à API
async function fetchAPI(url, method, body = null) {
    const options = {
        method,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    try {
        const response = await fetch(url, options);
        if (response.ok) {
            return await response.json();
        } else {
            throw new Error(`Erro: ${response.status} - ${response.statusText}`);
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert(error.message);
        throw error;
    }
}

// ------------------------ Funções para Caixa ------------------------

// Abrir caixa
document.getElementById("form-abrir-caixa")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const saldoInicial = parseFloat(document.getElementById("saldoInicial").value);

    try {
        const result = await fetchAPI(`${API_BASE_URL}/caixas/abrir`, "POST", { saldoInicial });
        alert(`Caixa aberto com sucesso! ID: ${result.id}`);
    } catch (error) {
        console.error("Erro ao abrir caixa:", error);
    }
});

// Atualizar caixa
document.getElementById("form-atualizar-caixa")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("caixa-id").value;
    const valorAtualizado = parseFloat(document.getElementById("valor-atualizado").value);

    try {
        const result = await fetchAPI(`${API_BASE_URL}/caixas/atualizar/${id}?valorAtualizado=${valorAtualizado}`, "PUT");
        alert(`Caixa atualizado com sucesso! Saldo Atual: ${result.saldoAtual}`);
    } catch (error) {
        console.error("Erro ao atualizar caixa:", error);
    }
});

// Fechar caixa
document.getElementById("form-fechar-caixa")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("caixa-id-fechar").value;
    const valorFechamento = parseFloat(document.getElementById("valor-fechamento").value);

    try {
        const result = await fetchAPI(`${API_BASE_URL}/caixas/fechar/${id}?valorFechamento=${valorFechamento}`, "PUT");
        alert(`Caixa fechado com sucesso! Saldo Final: ${result.saldoFinal}`);
    } catch (error) {
        console.error("Erro ao fechar caixa:", error);
    }
});

// -------------------- Funções para Agendamento --------------------

// Agendar visita
document.getElementById("form-agendar-visita")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const dataHora = document.getElementById("dataHora").value;
    const motivo = document.getElementById("motivo").value;
    const funcionarioId = parseInt(document.getElementById("funcionarioId").value);
    const animalId = document.getElementById("animalId").value ? parseInt(document.getElementById("animalId").value) : null;

    const agendamento = { dataHora, motivo, funcionario: { idFuncionario: funcionarioId }, animal: animalId ? { id: animalId } : null };

    try {
        const result = await fetchAPI(`${API_BASE_URL}/agendamentos/agendar`, "POST", agendamento);
        alert(`Visita agendada com sucesso! ID: ${result.idAgendaVisita}`);
    } catch (error) {
        console.error("Erro ao agendar visita:", error);
    }
});

// Buscar agendamento
document.getElementById("form-buscar-visita")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = parseInt(document.getElementById("visita-id").value);

    try {
        const result = await fetchAPI(`${API_BASE_URL}/agendamentos/${id}`, "GET");
        document.getElementById("resultado-visita").innerText = `Agendamento ID: ${result.idAgendaVisita}\nData/Hora: ${result.dataHora}\nMotivo: ${result.motivo}`;
    } catch (error) {
        console.error("Erro ao buscar agendamento:", error);
    }
});

// -------------------- Funções para Doadores --------------------

// Cadastrar doador
document.getElementById("form-cadastrar-doador")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const cpf = document.getElementById("cpf").value;
    const nome = document.getElementById("nome").value;

    try {
        const result = await fetchAPI(`${API_BASE_URL}/doadores/criar`, "POST", { cpf, nome });
        alert(`Doador cadastrado com sucesso! Nome: ${result.nome}`);
    } catch (error) {
        console.error("Erro ao cadastrar doador:", error);
    }
});

// Buscar doador
document.getElementById("form-buscar-doador")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const cpf = document.getElementById("cpf-buscar").value;

    try {
        const result = await fetchAPI(`${API_BASE_URL}/doadores/${cpf}`, "GET");
        document.getElementById("resultado-busca").innerText = `Doador: ${result.nome}\nCPF: ${result.cpf}`;
    } catch (error) {
        console.error("Erro ao buscar doador:", error);
    }
});

// Atualizar doador
document.getElementById("form-atualizar-doador")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const cpf = document.getElementById("cpf-atualizar").value;
    const nome = document.getElementById("nome-atualizar").value;

    try {
        const result = await fetchAPI(`${API_BASE_URL}/doadores/atualizar/${cpf}`, "PUT", { nome });
        alert(`Doador atualizado com sucesso! Nome: ${result.nome}`);
    } catch (error) {
        console.error("Erro ao atualizar doador:", error);
    }
});

// Deletar doador
document.getElementById("form-deletar-doador")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    const cpf = document.getElementById("cpf-deletar").value;

    try {
        await fetchAPI(`${API_BASE_URL}/doadores/deletar/${cpf}`, "DELETE");
        alert(`Doador com CPF ${cpf} deletado com sucesso!`);
    } catch (error) {
        console.error("Erro ao deletar doador:", error);
    }
});
