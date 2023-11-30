function validarDocumento() {
    var input = document.getElementById("numDoc")
    input.addEventListener('input', function (event) {
        var mensaje = document.getElementById('docError');
        if (input.value.length < 8) {
            mensaje.textContent = 'El numero de documento debe tener al menos 8 caracteres';
        } else {
            mensaje.textContent = '';
        }
    });
}


