$(document).ready(function(){
    var robeUslugeTable = $('#robeUslugeTable');
    var pagination = $('#pagination');
    var grupe = [];

    $("#add_roba").load("dialog/dodavanje_robe.html", function () {
        $.ajax({
            url: '/api/grupe-robe?size=9999999&page=0',
            success: function (data) {
                var selectGrupe = $('#grupe');
                grupe = data;
                for (var i=0; i<data.length; i++) {
                    selectGrupe.append(`<option value="${i}">${data[i].nazivGrupe}</option>`);
                }
            }
        });
    });

    var page = 0;
    var urlSearchParams = getParameters();
    var nazivInput = $('#naziv');
    getRoba();

    function getRoba() {
    	var naziv = nazivInput.val();
        $.ajax({
            url: '/api/robe-usluge?size=10&page='+page+'&grupa='+ urlSearchParams['grupa']+'&naziv='+naziv,
            success: function (data, status, headers) {
                var total = headers.getResponseHeader('total');
                pagination.empty();
                robeUslugeTable.empty();
                for (let i = 0; i < total; i++) {
                    if (i==page) {
                        pagination.append(`<li class="page-item active"><span class="page-link" >${i+1}</span></li>`)
                    } else {
                        pagination.append(`<li class="page-item" page="${i}"><a class="page-link" href="#">${i+1}</a></li>`)
                    }
                }
                for (const roba of data) {
                    robeUslugeTable.append(
                        `<tr>
                            <td>${roba.id}</td>
                            <td>${roba.nazivRobeUsluge}</td>
                            <td>${roba.jedinicaMere}</td>
                            <td>${roba.grupaRobe.nazivGrupe}</td>
                        </tr>`
                    )
                }
            }
        });
    }

    nazivInput.keyup(function (e) {
        e.preventDefault();
        getRoba();
    });

     
    
    pagination.on("click","li.page-item", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        getRoba();
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


    $('#modalDodavanje').click(function (e) {
        e.preventDefault();
        $('#dodavanje_robe').modal('show');

        $('#naziv').val('');
        $('#jedinicaMere').val('');
        $('#grupe>option:eq(0)').prop('selected', true);
    });


    $(document).on('click', '#potvrda_dodavanja_robe', function () {
        $('.alert').alert('close')
        var naziv = $('#naziv').val();
        if (naziv.length < 3) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Naziv mora biti duzi od 3 slova</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var jedinicaMere = $('#jedinicaMere').val();
        if (jedinicaMere.length < 1) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Jedinica mora biti duzi od 1 slova</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var grupa = $('#grupe').val();
        if (grupa == null) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Grupa robe nije selektovana</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }

        var novaRoba = {
            nazivRobeUsluge: naziv,
            jedinicaMere: jedinicaMere,
            grupaRobe: grupe[grupa]
        };

        $.ajax({
            url: '/api/robe-usluge',
            type: 'POST',
            data: JSON.stringify(novaRoba),
            contentType:"application/json",
            success: function(data) {
                getRoba();
                console.log('uspesno dodata roba');
            }
        });
        $('#dodavanje_robe').modal('hide');
    });

});