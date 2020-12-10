$(document).ready(function(){

    var grupaRobeTable = $('#grupeRobeTable');
    var pagination = $('#pagination');
    var page = 0;
    var grupe = [];
    getGrupaRobe();

    $("#add-grupa-robe").load("dialog/dodavanje_grupe_robe.html", function () {
        $.ajax({
            url: '/api/pdv',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                var selectPDV = $('#pdv');
                grupe = data;
                for (var i=0; i<data.length; i++) {
                    selectPDV.append(`<option value="${i}">${data[i].nazivPDV}</option>`);
                }
            }
        });
    });


    function getGrupaRobe() {

        $.ajax({
            url: '/api/grupe-robe?size=10&page='+page,
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data, status, headers) {
                var total = headers.getResponseHeader('total');
                pagination.empty();
                grupaRobeTable.empty();
                for (let i = 0; i < total; i++) {
                    if (i==page) {
                        pagination.append(`<li class="page-item active"><span class="page-link" >${i+1}</span></li>`)
                    } else {
                        pagination.append(`<li class="page-item" page="${i}"><a class="page-link" href="#">${i+1}</a></li>`)
                    }
                }
                for (const grupa of data) {
                    grupaRobeTable.append(
                        `<tr>
                            <td>${grupa.id}</td>
                            <td>${grupa.nazivGrupe}</td>
                            <td>${grupa.pdv.nazivPDV}</td>
                            <td>
                                <a class="btn btn-outline-primary pregledRobe" href="robe-usluge.html?grupa=${grupa.id}">Vidi robu</a>
                            </td>
                        </tr>`
                    );
                }
            }
        });
    }


    pagination.on("click","li.page-item", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        getGrupaRobe();
    });


    $('#modalDodavanje').click(function (e) {
        e.preventDefault();
        $('#dodavanje_grupe_robe').modal('show');

        $('#naziv').val('');
        $('#pdv>option:eq(0)').prop('selected', true);
    });


    $(document).on('click', '#potvrda_dodavanja_grupe_robe', function () {
        $('.alert').alert('close')
        var naziv = $('#naziv').val();
        if (naziv.length < 3) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Naziv mora biti duzi od 3 slova</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var pdv = $('#pdv').val();
        if (pdv == null) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Grupa robe nije selektovana</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }

        var novaGrupa = {
            nazivGrupe: naziv,
            pdv: grupe[pdv]
        };

        $.ajax({
            url: '/api/grupe-robe',
            headers: {'Authorization': localStorage.getItem('token')},
            type: 'POST',
            data: JSON.stringify(novaGrupa),
            contentType:"application/json",
            success: function(data) {
                alert("Grupa uspesno dodata.")
                getGrupaRobe();
                console.log('uspesno dodata grupa');
            }
        });
        $('#dodavanje_grupe_robe').modal('hide');
    });

});