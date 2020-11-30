$(document).ready(function(){
    var stavkeTable = $('#stavkeTable');
    var pagination = $('#pagination');
    var nazivInput = $('#naziv');
    var page = 0;
    var urlSearchParams = getParameters();
    var robeUsluge = [];
    var cenovnici = [];
    $("#modal_stavka").load("dialog/dodavanje_stavke_cenovnika.html", function () {
        $.ajax({
            url: '/api/robe-usluge?size=999999&page=0',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                var robaSelect = $('#roba');
                robeUsluge = data;
                for (var i=0; i<data.length; i++) {
                    robaSelect.append(`<option value="${i}">${data[i].nazivRobeUsluge}</option>`);
                }
            }
        });
        $.ajax({
            url: '/api/cenovnici?size=999999&page=0',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                var cenovniciSelect = $('#cenovnik');
                cenovnici = data;
                for (var i=0; i<data.length; i++) {
                    cenovniciSelect.append(`<option value="${i}">${data[i].datumVazenja}</option>`);
                }
            }
        });
    });

    getStavke();

    function getStavke() {
        var naziv = nazivInput.val();

        $.ajax({
            url: '/api/cenovnici/'+ urlSearchParams['id'] + '/stavke-cenovnika?size=10&page='+page+'&naziv='+naziv,
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data, status, headers) {
                var total = headers.getResponseHeader('total');
                pagination.empty();
                stavkeTable.empty();
                for (let i = 0; i < total; i++) {
                    if (i==page) {
                        pagination.append(`<li class="page-item active"><span class="page-link" >${i+1}</span></li>`)
                    } else {
                        pagination.append(`<li class="page-item" page="${i}"><a class="page-link" href="#">${i+1}</a></li>`)
                    }
                }
                for (const stavke of data) {
                    stavkeTable.append(
                        `<tr>
                            <td>${stavke.id}</td>
                            <td>${stavke.robaUsluga.nazivRobeUsluge}</td>
                            <td>${stavke.cena}</td>
                        </tr>`
                    )
                }
            }
        });
    }


    $('#dodaj_stavku').click(function (e) {
        e.preventDefault();
        $('#dodavanje_stavke_cenovnika').modal('show');
        $('#cena').val(1);
        $('#roba>option:eq(0)').prop('selected', true);
        $('#cenovnik>option:eq(0)').prop('selected', true);
    });


    $(document).on('click', '#potvrda_dodavanja_stavke_cenovnika', function () {
        $('.alert').alert('close')
        var cena = $('#cena').val();
        if (cena < 0) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Cena mora biti veca od 0</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var cenovnik = $('#cenovnik').val();
        var roba = $('#roba').val();

        var novaStavkaCenovnika = {
            cena: cena,
            cenovnik: cenovnici[cenovnik],
            robaUsluga: robeUsluge[roba]
        }

        $.ajax({
            url: '/api/stavke-cenovnika',
            headers: {'Authorization': localStorage.getItem('token')},
            type: 'POST',
            data: JSON.stringify(novaStavkaCenovnika),
            contentType:"application/json",
            success: function(data) {
                console.log('uspesno dodata stavka cenovnika');
                getStavke();
            }
        });

        $('#dodavanje_stavke_cenovnika').modal('hide');
    });


    nazivInput.keyup(function (e) {
        e.preventDefault();
        getStavke();
    });

    pagination.on("click","li.page-item", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        getStavke();
    });


    function getParameters(){
        var param = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            param.push(hash[0]);
            param[hash[0]] = hash[1];
        }
        return param;
    }

});