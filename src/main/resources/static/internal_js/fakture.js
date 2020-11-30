$(document).ready(function () {

    var fakturaTable = $('#fakturaTable');
    var pagination = $('#pagination');
    var statusFaktureSelect = $('#status-fakture');
    var page = 0;
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
                            <td>${!faktura.datumStorniranja ? '' : new Date(faktura.datumStorniranja).toLocaleString()}</td>
                            <td>${faktura.iznosBezRabata}</td>
                            <td>${faktura.iznosZaPlacanje}</td>
                            <td>${faktura.statusFakture}</td>
                            <td>
                                <a class="btn btn-primary" href="/faktura.html?id=${faktura.id}">GET</a>
                            </td>
                        </tr>`
                    )
                }
            }
        });
    }


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