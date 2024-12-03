document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('form-recebimento');
    
    form.addEventListener('submit', function(event) {

        event.preventDefault();
        const animalData = {
            nome: document.getElementById('nome-animal').value,
            tipo: document.getElementById('tipo-animal').value,
            idade: document.getElementById('idade-animal').value,
            sexo: document.getElementById('sexo-animal').value,
            data_nascimento: document.getElementById('nascimento-animal').value,
        };

        fetch('http://localhost:8080/receber', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(animalData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao receber animal.');
            }
            else {
                alert('Animal recebido com sucesso!');
                form.reset();
            }
        })
        .catch(error => {
            alert(error.message);
        });
    }
    );
});