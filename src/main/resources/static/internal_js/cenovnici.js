$(document).ready(function(){
    var cenovniciTable = $('#cenovniciTable');
    var preduzeca = [];
    $("#modal_cenovnik").load("dialog/dodavanje_cenovnika.html", function () {
        $.ajax({
            url: '/api/preduzeca',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                var preduzecaSelect = $('#preduzeca');
                preduzeca = data;
                for (var i=0; i<data.length; i++) {
                    preduzecaSelect.append(`<option value="${i}">${data[i].naziv}</option>`);
                }
            }
        });
    });
    getCenovnici();

    function getCenovnici() {
        $.ajax({
            url: '/api/cenovnici',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                cenovniciTable.empty();
                for (const cenovnik of data) {
                    cenovniciTable.append(
                        `
                        <tr>
                            <td>${cenovnik.id}</td>
                            <td>${cenovnik.preduzece.naziv}</td>
                            <td>${cenovnik.datumVazenja}</td>
                            <td>${cenovnik.aktivan}</td>
                            <td><a href='cenovnik.html?id=${cenovnik.id}' class='btn btn-outline-primary'>Pogledaj</a></td>
                        </tr>
                        `
                    )
                }
            }
        });
    }

    $('#dodaj_cenovnik').click(function (e) {
        e.preventDefault();
        $('#dodavanje_cenovnika').modal('show');
        $('#datumVazenja').val(new Date().toISOString().split('T')[0]);
        $('#preduzeca>option:eq(0)').prop('selected', true);
    });

    $(document).on('click', '#potvrda_dodavanja_cenovnika', function () {
        $('.alert').alert('close')
        var datumVazenja = $('#datumVazenja').val();
        if (datumVazenja == '') {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Datum nije selektovan</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;

        }
        var preduzece = $('#preduzeca').val();

        var noviCenovnik = {
            datumVazenja: datumVazenja,
            preduzece: preduzeca[preduzece]
        }

        $.ajax({
            url: '/api/cenovnici',
            headers: {'Authorization': localStorage.getItem('token')},
            type: 'POST',
            data: JSON.stringify(noviCenovnik),
            contentType:"application/json",
            success: function(data) {
                console.log('uspesno dodat cenovnik');
                getCenovnici();
            }
        });

        $('#dodavanje_cenovnika').modal('hide');
    });
});