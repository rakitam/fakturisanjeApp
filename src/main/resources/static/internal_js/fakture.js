$(document).ready(function () {

    var fakturaTable = $('#fakturaTable');
    var pagination = $('#pagination');
    var page = 0;
    getFakture();

    function getFakture() {

        $.ajax({
            url: '/api/fakture?size=10&page='+page,
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
                            <td>${!faktura.datumFakture ? '' : faktura.datumFakture}</td>
                            <td>${!faktura.datumValute ? '' : faktura.datumValute}</td>
                            <td>${!faktura.datumStorniranja ? '' : faktura.datumStorniranja}</td>
                            <td>${faktura.iznosBezRabata}</td>
                            <td>${faktura.iznosZaPlacanje}</td>
                            <td>${faktura.statusFakture}</td>
                            <td>
                                <a class="btn btn-primary" href="/faktura.html?id=${faktura.id}">GET</a>
                                <button class="btn btn-secondary">Izvestaj</button>
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

});