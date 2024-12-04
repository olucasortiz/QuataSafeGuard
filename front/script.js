const apiBaseUrl = "http://localhost:8080/api/doadores";

// Função para carregar todos os doadores
async function carregarDoadores() {
    try {
        const response = await fetch(apiBaseUrl);
        const doadores = await response.json();
        const tabela = document.getElementById("tabelaDoadores");
        tabela.innerHTML = "";

        doadores.forEach(doador => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${doador.cpf}</td>
                <td>${doador.nome}</td>
                <td>${doador.email}</td>
                <td>${doador.telefone}</td>
                <td>${doador.idade}</td>
                <td>
                    <button onclick="editarDoador('${doador.cpf}')">Editar</button>
                    <button onclick="deletarDoador('${doador.cpf}')">Excluir</button>
                </td>
            `;
            tabela.appendChild(row);
        });
    } catch (error) {
        console.error("Erro ao carregar doadores:", error);
    }
}

// Função para salvar um doador
async function salvarDoador(event) {
    event.preventDefault();

    const doador = {
        cpf: document.getElementById("cpf").value,
        nome: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        telefone: document.getElementById("telefone").value,
        idade: parseInt(document.getElementById("idade").value),
    };

    const metodo = doador.cpf ? "PUT" : "POST";
    const url = metodo === "POST" ? `${apiBaseUrl}/criar` : `${apiBaseUrl}/atualizar/${doador.cpf}`;

    try {
        await fetch(url, {
            method: metodo,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(doador),
        });
        carregarDoadores();
        document.getElementById("doadorForm").reset();
    } catch (error) {
        console.error("Erro ao salvar doador:", error);
    }
}

// Função para excluir um doador
async function deletarDoador(cpf) {
    try {
        await fetch(`${apiBaseUrl}/deletar/${cpf}`, { method: "DELETE" });
        carregarDoadores();
    } catch (error) {
        console.error("Erro ao excluir doador:", error);
    }
}

// Função para carregar dados para edição
function editarDoador(cpf) {
    fetch(`${apiBaseUrl}/${cpf}`)
        .then(response => response.json())
        .then(doador => {
            document.getElementById("cpf").value = doador.cpf;
            document.getElementById("nome").value = doador.nome;
            document.getElementById("email").value = doador.email;
            document.getElementById("telefone").value = doador.telefone;
            document.getElementById("idade").value = doador.idade;
        })
        .catch(error => console.error("Erro ao carregar doador:", error));
}

// Event listeners
document.getElementById("doadorForm").addEventListener("submit", salvarDoador);

// Inicialização
carregarDoadores();

const apiBaseUrlCaixa = "http://localhost:8080/api/caixa";

// Função para abrir caixa
async function abrirCaixa(event) {
    event.preventDefault();

    const caixa = {
        valorInicial: parseFloat(document.getElementById("valorInicial").value),
        funcionario: { id: parseInt(document.getElementById("funcionarioId").value) }
    };

    try {
        const response = await fetch(`${apiBaseUrlCaixa}/abrir`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(caixa),
        });
        if (response.ok) {
            alert("Caixa aberto com sucesso!");
        } else {
            alert("Erro ao abrir o caixa.");
        }
    } catch (error) {
        console.error("Erro ao abrir caixa:", error);
    }
}

// Função para atualizar caixa
async function atualizarCaixa(event) {
    event.preventDefault();

    const id = document.getElementById("caixaIdAtualizar").value;
    const valorAtualizado = parseFloat(document.getElementById("valorAtualizado").value);

    try {
        const response = await fetch(`${apiBaseUrlCaixa}/atualizar/${id}?valorAtualizado=${valorAtualizado}`, {
            method: "PUT",
        });
        if (response.ok) {
            alert("Caixa atualizado com sucesso!");
        } else {
            alert("Erro ao atualizar o caixa.");
        }
    } catch (error) {
        console.error("Erro ao atualizar caixa:", error);
    }
}

// Função para fechar caixa
async function fecharCaixa(event) {
    event.preventDefault();

    const id = document.getElementById("caixaIdFechar").value;
    const valorFechamento = parseFloat(document.getElementById("valorFechamento").value);

    try {
        const response = await fetch(`${apiBaseUrlCaixa}/fechar/${id}?valorFechamento=${valorFechamento}`, {
            method: "PUT",
        });
        if (response.ok) {
            alert("Caixa fechado com sucesso!");
        } else {
            alert("Erro ao fechar o caixa.");
        }
    } catch (error) {
        console.error("Erro ao fechar caixa:", error);
    }
}

// Event listeners
document.getElementById("abrirCaixaForm").addEventListener("submit", abrirCaixa);
document.getElementById("atualizarCaixaForm").addEventListener("submit", atualizarCaixa);
document.getElementById("fecharCaixaForm").addEventListener("submit", fecharCaixa);
