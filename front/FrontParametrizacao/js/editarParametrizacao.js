// editarparametrizacao.js

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
    fetch("http://localhost:8080/api/empresa/get/profile", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.json())
        .then((result) => {
            const empresaProfileSection = document.getElementById("empresaProfile");
            empresaProfileSection.innerHTML = `
                <div class="profile-container">
                    <img src="${result.logoPequeno}" alt="Logo da Empresa" class="logo">
                    <h2>${result.nomeFantasia}</h2>
                    <p><strong>Razão Social:</strong> ${result.razaoSocial}</p>
                    <p><strong>CNPJ:</strong> ${formatarCnpj(result.cnpj)}</p>
                    <p><strong>Endereço:</strong> ${result.endereco}</p>
                    <p><strong>Bairro:</strong> ${result.bairro}</p>
                    <p><strong>Cidade:</strong> ${result.cidade}</p>
                    <p><strong>UF:</strong> ${result.uf}</p>
                    <p><strong>Telefone:</strong> ${result.telefone}</p>
                    <p><strong>Site:</strong> ${result.site}</p>
                    <p><strong>Data de Criação:</strong> ${formatarData(result.dataCriacao)}</p>
                    <button onclick="editarParametrizacao(${result.id})">Editar Parametrização</button>
                </div>`;
        })
        .catch((error) => console.error("Erro ao carregar perfil:", error));
});

function editarParametrizacao(id) {
    fetch(`http://localhost:8080/api/empresa/get/${id}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.json())
        .then((result) => {
            const empresaProfileSection = document.getElementById("empresaProfile");
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
        logoPequeno: document.getElementById("logoPequeno").value,
        logoGrande: document.getElementById("logoGrande").value,
    });

    fetch("http://localhost:8080/api/empresa/update", {
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
