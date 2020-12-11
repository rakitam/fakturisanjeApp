$(document).ready(function () {
    $('#nav').load('nav.html');
    getPreduzece();

     var preduzece = {}

    $("#modal_preduzeca").load("dialog/update_preduzece.html", function () {

        $.ajax({
            url: '/api/preduzeca/1',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                $("#nazivIzmena").val(data.naziv);
                $("#adresaIzmena").val(data.adresaPreduzeca);
                $("#telefonIzmena").val(data.telefon);
                $("#tekuciRacunIzmena").val(data.tekuciRacun);
                $("#telefonIzmena").val(data.telefon);
                $("#emailIzmena").val(data.email);
                $("#pibIzmena").val(data.pib);
            }

        });
    });
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

                preduzece = data;


            }

        });

    }
    if(localStorage.getItem('role')=="ROLE_KORISNIK") {
        $('#modalIzmena').hide();
    }

    $('#modalIzmena').click(function (e) {
        e.preventDefault();
        $('#update_preduzece').modal('show');
    });
    $(document).on('click', '#potvrda_izmene_preduzeca', function () {
        $('.alert').alert('close')

        preduzece.naziv =  $("#nazivIzmena").val();
        preduzece.adresaPreduzeca =  $("#adresaIzmena").val();
        preduzece.telefonPreduzeca =  $("#telefonIzmena").val();
        preduzece.tekuciRacun =  $("#tekuciRacun").val();
        preduzece.telefon =  $("#telefonIzmena").val();
        preduzece.email =  $("#emailIzmena").val();
        preduzece.PIB =  $("#pibIzmena").val();

        $.ajax({
            method: 'PUT',
            data: JSON.stringify(preduzece),
            contentType: "application/json",
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/preduzeca/1'
        }).done(function(){
            console.log(preduzece)
            alert("Podaci uspešno izmenjeni.")
            getPreduzece()
        });

        $('#update_preduzece').modal('hide');
    });
});