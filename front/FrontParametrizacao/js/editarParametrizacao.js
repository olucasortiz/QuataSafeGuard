
function formatarCnpj(cnpj) {
    return cnpj.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, "$1.$2.$3/$4-$5");
}

function formatarData(data) {
    const dataObj = new Date(data);
    const dia = String(dataObj.getDate()).padStart(2, "0");
    const mes = String(dataObj.getMonth() + 1).padStart(2, "0");
    const ano = dataObj.getFullYear();
    return `${dia}/${mes}/${ano}`;
}

document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM carregado, iniciando fetch...");
    fetch("http://localhost:8080/api/empresa/detalhes", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then((response) => {
            console.log("Resposta bruta do backend:", response);
            return response.json();
        })
        .then((result) => {
            console.log("Dados recebidos do backend:", result);
            if (result) {
                document.getElementById("nomeFantasia").value = result.nomeFantasia || "";
                document.getElementById("razaoSocial").value = result.razaoSocial || "";
                document.getElementById("cnpj").value = result.cnpj || "";
                document.getElementById("endereco").value = result.endereco || "";
                document.getElementById("bairro").value = result.bairro || "";
                document.getElementById("cidade").value = result.cidade || "";
                document.getElementById("uf").value = result.uf || "";
                document.getElementById("telefone").value = result.telefone || "";
                document.getElementById("site").value = result.site || "";
                document.getElementById("email").value = result.email || "";
                document.getElementById("cep").value = result.cep || "";
                document.getElementById("dataCriacao").value = result.dataCriacao
                    ? new Date(result.dataCriacao).toISOString().split("T")[0]
                    : "";
                document.getElementById("logoPequeno").value = result.logoPequeno || "";
                document.getElementById("logoGrande").value = result.logoGrande || "";
            } else {
                console.error("Nenhum dado encontrado.");
            }
        })
        .catch((error) => console.error("Erro ao carregar os dados:", error));
});


function editarParametrizacao(id) {
    fetch(`http://localhost:8080/api/empresa/get-empresa/${id}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.json())
        .then((result) => {
            const empresaProfileSection = document.getElementById("empresaProfile");
            const dataCriacao = result.dataCriacao
                ? new Date(result.dataCriacao).toISOString().split("T")[0]
                : "";
            empresaProfileSection.innerHTML = `
                <form onsubmit="atualizarParametrizacao(event)">
                    <input type="text" id="nomeFantasia" value="${result.nomeFantasia}" required>
                    <input type="text" id="razaoSocial" value="${result.razaoSocial}" required>
                    <input type="text" id="cnpj" value="${result.cnpj}" required>
                    <input type="text" id="endereco" value="${result.endereco}">
                    <input type="text" id="bairro" value="${result.bairro}">
                    <input type="text" id="cidade" value="${result.cidade}">
                    <input type="text" id="uf" value="${result.uf}" maxlength="2">
                    <input type="text" id="telefone" value="${result.telefone}">
                    <input type="text" id="site" value="${result.site}">
                    <input type="date" id="dataCriacao" value="${result.dataCriacao}">
                    <input type="text" id="logoPequeno" value="${result.logoPequeno}">
                    <input type="text" id="logoGrande" value="${result.logoGrande}">
                    <button type="submit">Salvar</button>
                </form>`;
        })
        .catch((error) => console.error("Erro ao editar parametrização:", error));
}

function atualizarParametrizacao(event) {
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
        dataCriacao: document.getElementById("dataCriacao").value,
        logoPequeno: document.getElementById("logoPequeno").value,
        logoGrande: document.getElementById("logoGrande").value,
    });

    fetch("http://localhost:8080/api/empresa/update-empresa", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: empresa,
    })
        .then(() => {
            alert("Parametrização atualizada com sucesso!");
            window.location.href = "perfil.html";
        })
        .catch((error) => console.error("Erro ao atualizar parametrização:", error));
}
