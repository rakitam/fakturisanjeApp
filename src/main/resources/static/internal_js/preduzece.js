$(document).ready(function () {
    $('#nav').load('nav.html');
    getPreduzece();


    function getPreduzece(){
        $.ajax({
            url: '/api/preduzeca/1',
            success: function(data) {
                $("#naziv").val(data.naziv);
                $("#adresaPreduzeca").val(data.adresaPreduzeca);
                $("#mesto").val(data.mesto.naziv);
                $("#telefonPreduzeca").val(data.telefon);
                $("#tekuciRacun").val(data.tekuciRacun.slice(0, 3) + "-" + data.tekuciRacun.slice(3, 15) + "-" + data.tekuciRacun.slice(15));
                $("#telefon").val(data.telefon);
                $("#email").val(data.email);
                $("#PIB").val(data.pib);
            }
        });
    }
});