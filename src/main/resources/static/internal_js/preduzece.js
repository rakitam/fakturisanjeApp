$(document).ready(function () {
    $('#nav').load('nav.html');
    getPreduzece();

     var preduzece = {}

    // $("#update_preduzece").load("dialog/update_preduzece.html", function () {
    //     $.ajax({
    //         url: '/api/preduzeca/1',
    //         headers: {'Authorization': localStorage.getItem('token')},
    //         success: function (data) {
    //             var preduzeceNaziv = $('#naziv');
    //             preduzece = data;
    //             for (var i=0; i<data.length; i++) {
    //                 preduzeceNaziv.append(`<option value="${i}">${data[i].nazivPDV}</option>`);
    //             }
    //         }
    //     });
    // });

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



    $('#izmeniPodatke').on("click", function(){

        preduzece.naziv =  $("#naziv").val();
        preduzece.adresaPreduzeca =  $("#adresaPreduzeca").val();
        //preduzece.mesto.id =  $("#mesto").val();
        preduzece.telefonPreduzeca =  $("#telefonPreduzeca").val();
        preduzece.tekuciRacun =  $("#tekuciRacun").val();
        preduzece.telefon =  $("#telefon").val();
        preduzece.email =  $("#email").val();
        preduzece.PIB =  $("#PIB").val();

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
    });
});