$(document).ready(function () {

    var fakturaTable = $('#fakturaTable');
    var pagination = $('#pagination');
    var statusFaktureSelect = $('#status-fakture');
    var poslovneGodine = [];
    var page = 0;

    $("#modal_izvestaj_poslovna_godina").load("dialog/faktura_izvestaj_poslovna_godina.html", function () {
        $.ajax({
            url: 'api/poslovne-godine',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                var poslovneGodineSelect = $('#poslovneGodine');
                poslovneGodine = data;
                for (var i=0; i<data.length; i++) {
                    poslovneGodineSelect.append(`<option value="${i}">${data[i].godina}</option>`);
                }
            }
        });
    });

    getFakture();

    function getFakture() {
        var statusFakture = statusFaktureSelect.val();
        $.ajax({
            url: '/api/fakture?size=10&page='+page+'&status='+statusFakture,
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data, status, headers) {
                var total = headers.getResponseHeader('total');
                pagination.empty();
                fakturaTable.empty();
                for (let i = 0; i < total; i++) {
                    if (i == page) {
                        pagination.append(`<li class="page-item active"><span class="page-link" >${i + 1}</span></li>`)
                    } else {
                        pagination.append(`<li class="page-item" page="${i}"><a class="page-link" href="#">${i + 1}</a></li>`)
                    }
                }
                for (const faktura of data) {
                    fakturaTable.append(
                        `<tr>
                            <td>${faktura.id}</td>                   
                            <td>${faktura.brojFakture}/${faktura.poslovnaGodina.godina}</td>
                            <td>${faktura.korisnik}</td>
                            <td>${!faktura.datumFakture ? '' : new Date(faktura.datumFakture).toLocaleString()}</td>
                            <td>${!faktura.datumValute ? '' : new Date(faktura.datumValute).toLocaleString()}</td>
                            <td>${faktura.rabat}</td>
                            <td>${faktura.osnovica}</td>
                            <td>${faktura.ukupanPdv}</td>
                            <td>${faktura.iznosZaPlacanje}</td>
                            <td>${faktura.statusFakture}</td>
                            <td>
                                <a class="btn btn-primary" href="/faktura.html?id=${faktura.id}">Pogledaj</a>
                            </td>
                        </tr>`
                    )
                }
            }
        });
    }

    $('#izvestaj_za_godinu').click(function (e) {
        e.preventDefault();
        $('#faktura_izvestaj_poslovna_godina').modal('show');
        $('#poslovneGodine>option:eq(0)').prop('selected', true);
    });

    $(document).on('click', '#potvrda_poslovne_godine', function () {
        $('.alert').alert('close')
        var pg = $('#poslovneGodine option:selected').text();
        var pg_int = parseInt(pg);
        console.log(pg_int);
        $.ajax({
            method: 'GET',
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/fakture/izvestaj?godina=' + pg_int,
            xhrFields: {
                responseType: 'blob'
            },
            success: function (blob) {
                var url = URL.createObjectURL(blob);
                window.open(url)
            }
        });

        $('#faktura_izvestaj_poslovna_godina').modal('hide');
    });


    pagination.on("click","li.page-item", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        getFakture();
    });

    statusFaktureSelect.change(function (e) {
        e.preventDefault();
        getFakture();
    });

});