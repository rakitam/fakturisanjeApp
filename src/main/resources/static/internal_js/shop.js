$(document).ready(function () {

    $("#add-to-korpa").load("dialog/stavka.html", function () {
        if(localStorage.getItem('role')=="ROLE_ADMIN") {
            $.ajax({
                url: '/api/korisnici',
                headers: {'Authorization': localStorage.getItem('token')},
                success: function (data) {
                    var korisnikSelect = $('#korisnik');
                    for (var i=0; i<data.length; i++) {
                        korisnikSelect.append(`<option value="${data[i].email}">${data[i].email}</option>`);
                    }
                }
            });
        } else {
            $('.admin-only').remove();
        }
    });

    var robaTable = $('#robaTable');
    var pagination = $('#pagination');
    var page = 0;
    var selectedRobaId;
    getRoba();

    function getRoba() {
        $.ajax({
            url: '/api/stavke-cenovnika?size=10&page=' + page,
            headers: {'Authorization': localStorage.getItem('token')},
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
                            <td>${roba.cenaSaPdv}</td>
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
        $('#kolicina').val(1);
        $('#korisnik>option:eq(0)').prop('selected', true);
        $('#rabat').val(0);
        $('.alert').alert('close')

    });

    $(document).on('click', '#potvrda', function () {
        var kolicina = $('#kolicina').val();
        var rabat = $('#rabat').val();
        var korisnik = $('#korisnik').val();
        if (!rabat) {
            rabat = 0;
        }
        if (kolicina < 1) {
            $('#messages').append(
                `<div class="alert alert-danger fade show" role="alert">Kolicina ne sme biti manja od 1</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        if (rabat < 0) {
            $('#messages').append(
                `<div class="alert alert-danger fade show" role="alert">Rabat ne sme biti manji od 0</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        if(localStorage.getItem('role') === 'ROLE_ADMIN') {
            dodavanjeUKorpuAdmin(kolicina, rabat,korisnik)
        } else {
            dodavanjeUKorpu(kolicina, rabat);
        }
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
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                console.log('uspesno dodato u korpu');
            }
        });
    }
    function dodavanjeUKorpuAdmin(kolicina, rabat, korisnik) {
        if (korisnik == localStorage.getItem('email')) {
            dodavanjeUKorpu(kolicina, rabat);
            return;
        }
        $.ajax({
            method: 'POST',
            url: '/api/stavke-cenovnika/' + selectedRobaId + '/add-to-korpa/'+korisnik+'?kolicina=' + kolicina + '&rabat=' + rabat,
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                console.log('uspesno dodato u korpu');
            }
        });
    }

});