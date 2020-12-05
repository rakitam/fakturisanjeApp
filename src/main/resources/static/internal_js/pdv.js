$(document).ready(function(){
    var pdvTabela = $('#pdvTabela');
    $("#modal_pdv").load("dialog/dodavanje_pdv.html");
    getPDV();

    function getPDV() {
        $.ajax({
            url: '/api/pdv',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                pdvTabela.empty();
                for (const pdv of data) {
                    pdvTabela.append(
                        `<tr>
                            <td>${pdv.id}</td>
                            <td>${pdv.nazivPDV}</td>
                            <td><a href='stope-pdv.html?id=${pdv.id}' class='btn btn-outline-primary'>Pogledaj aktivnu stopu</a></td>
                        </tr>
                        `
                    )
                }
            }
        });
    }

    $('#dodaj_pdv').click(function (e) {
        e.preventDefault();
        $('#dodavanje_pdv').modal('show');
        $('#naziv').val('');
    });


    $(document).on('click', '#potvrda_dodavanja_pdv', function () {
        $('.alert').alert('close')
        var naziv = $('#naziv').val();
        if (naziv.length < 3) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Naziv mora biti duzi od 3 slova</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var noviPDV = {
            nazivPDV: naziv
        };

        $.ajax({
            url: '/api/pdv',
            headers: {'Authorization': localStorage.getItem('token')},
            type: 'POST',
            data: JSON.stringify(noviPDV),
            contentType:"application/json",
            success: function(data) {
                getPDV();
                console.log('uspesno dodat pdv');
            }
        });
        $('#dodavanje_pdv').modal('hide');
    });

});