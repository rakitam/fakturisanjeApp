$(document).ready(function () {

    $("#add-to-korpa").load("dialog/stavka.html");

    var robaTable = $('#robaTable');
    var pagination = $('#pagination');
    var page = 0;
    var selectedRobaId;
    getRoba();

    function getRoba() {
        $.ajax({
            url: '/api/stavke-cenovnika?size=10&page=' + page,
            success: function (data, status, headers) {
                var total = headers.getResponseHeader('total');
                pagination.empty();
                robaTable.empty();
                for (let i = 0; i < total; i++) {
                    if (i == page) {
                        pagination.append(`<li class="page-item active"><span class="page-link" >${i + 1}</span></li>`)
                    } else {
                        pagination.append(`<li class="page-item" page="${i}"><a class="page-link" href="#">${i + 1}</a></li>`)
                    }
                }
                for (const roba of data) {
                    robaTable.append(
                        `
                        <tr>
                            <td>${roba.id}</td>
                            <td>${roba.robaUsluga.nazivRobeUsluge}</td>
                            <td>${roba.cena}</td>
                            <td>${roba.robaUsluga.grupaRobe.nazivGrupe}</td>
                            <td>
                                <button roba_id="${roba.id}" type="button" class="btn btn-primary addToKorpa">Dodaj</button>
                            </td>
                        </tr>
                        `
                    )
                }
            }
        });
    }

    $(document).on('click', '.addToKorpa', function (e) {
        selectedRobaId = $(this).attr('roba_id')
        $('#stavka').modal('show')
        $('#rabat').val(0);
        $('#kolicina').val(1);
        $('.alert').alert('close')

    });

    $(document).on('click', '#potvrda', function () {
        var rabat = $('#rabat').val();
        if (rabat < 0) {
            $('#messages').append(
                `<div class=" alert alert-warning alert-dismissible fade show" role="alert">Rabat ne sme biti manji od 1</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var kolicina = $('#kolicina').val();
        if (kolicina < 1) {
            $('#messages').append(
                `<div class="alert alert-danger fade show" role="alert">Kolicina ne sme biti manja od 1</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        dodavanjeUKorpu(kolicina, rabat);
        $('#stavka').modal('hide');
    });

    pagination.on("click", "li.page-item", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        getRoba();
    });


    function dodavanjeUKorpu(kolicina, rabat) {
        $.ajax({
            method: 'POST',
            url: '/api/stavke-cenovnika/' + selectedRobaId + '/add-to-korpa?kolicina=' + kolicina + '&rabat=' + rabat,
            success: function (data) {
                console.log('uspesno dodato u korpu');
            }
        });
    }

});