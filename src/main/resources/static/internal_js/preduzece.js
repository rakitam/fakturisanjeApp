$(document).ready(function () {
    $('#nav').load('nav.html');
    getPreduzece();

    function getPreduzece(){
        $.ajax({
            url: '/api/preduzeca/1',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function(data) {
                $("#naziv").val(data.naziv);
                $("#adresaPreduzeca").val(data.adresaPreduzeca);
                $("#mesto").val(data.mesto.naziv);
                $("#telefonPreduzeca").val(data.telefon);
                $("#tekuciRacun").val(data.tekuciRacun);
                $("#telefon").val(data.telefon);
                $("#email").val(data.email);
                $("#PIB").val(data.pib);
            }
        });
    }
});