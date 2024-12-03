document.addEventListener("DOMContentLoaded", function () {
    carregarProdutos();
});

// Função para carregar os produtos do backend
function carregarProdutos() {
    console.log("Carregando lista de produtos...");

    fetch("http://localhost:8080/api/produto/get-all-produto", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(data => {
            console.log("Produtos carregados:", data);

            const produtoSelect = document.getElementById("produto");

            if (Array.isArray(data)) {
                data.forEach(produto => {
                    const option = document.createElement("option");
                    option.value = produto.idProduto;  // Usando idProduto como valor
                    option.textContent = `${produto.nomeProduto} (${produto.quantidadeEstoque} disponíveis)`;  // Exibe nome e quantidade
                    produtoSelect.appendChild(option);
                });
            } else {
                console.error("Erro: Dados de produtos não são um array:", data);
            }
        })
        .catch(error => {
            console.error("Erro ao carregar produtos:", error);
        });
}

// Função para buscar o doador pelo CPF
function buscarDoadorPorCpf(cpf) {
    return fetch(`http://localhost:8080/api/doadores/${cpf}`, {  // Supondo que você tenha uma API para isso
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Doador não encontrado");
            }
            return response.json();
        })
        .catch(error => {
            console.error("Erro ao buscar doador:", error);
            throw new Error("Doador não encontrado");
        });
}

// Função que será chamada ao submeter o formulário
document.getElementById("doacaoForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const cpf = document.getElementById("cpf").value.trim();  // Captura o CPF do doador
    const produtoId = document.getElementById("produto").value;
    const quantidade = document.getElementById("quantidade").value;
    const funcionarioId = 1; // ID do funcionário (exemplo fixo)
    const caixaId = 3; // ID do caixa (exemplo fixo)

    // Validação simples
    if (!cpf || !produtoId || !quantidade || quantidade <= 0) {
        alert("Por favor, preencha todos os campos corretamente.");
        return;
    }

    // Buscar doador pelo CPF
    buscarDoadorPorCpf(cpf)
        .then(doador => {
            const doacao = {
                "data": new Date().toISOString().split("T")[0],  // Data atual (formatada)
                "valor": 100.50,  // Valor da doação (ajustar conforme necessário)
                "quantidadeItens": 1,  // Número de itens na doação
                "funcionario": {
                    "idFuncionario": funcionarioId
                },
                "caixa": {
                    "idCaixa": caixaId
                },
                "itensDoacao": [
                    {
                        "qtde": parseInt(quantidade),
                        "produto": {
                            "idProduto": parseInt(produtoId)
                        }
                    }
                ],
                "doador": {
                    "idDoador": doador.idDoador // ID do doador retornado
                }
            };

            // Exibe os dados da doação que serão enviados
            console.log("Dados da doação que serão enviados:", JSON.stringify(doacao, null, 2));

            // Enviar a doação para o backend
            fetch("http://localhost:8080/api/doacoes", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(doacao)
            })
                .then(response => {
                    console.log("Resposta do servidor:", response);
                    if (!response.ok) {
                        console.error("Erro na resposta do servidor:", response.status, response.statusText);
                    }
                    return response.json();
                })
                .then(result => {
                    console.log("Resultado da requisição:", result);
                    if (result) {
                        alert("Doação registrada com sucesso!");
                        document.getElementById("doacaoForm").reset();  // Limpa o formulário
                    } else {
                        console.error("Erro: A resposta não contém dados válidos.", result);
                    }
                })
                .catch(error => {
                    console.error("Erro ao registrar doação:", error);
                    alert("Erro ao registrar doação: " + error.message);
                });
        })
        .catch(error => {
            alert("Erro: " + error.message);
        });
});