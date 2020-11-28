$(document).ready(function(){

    var grupaRobeTable = $('#grupeRobeTable');
    var pagination = $('#pagination');
    var page = 0;
    getGrupaRobe();

    function getGrupaRobe() {

        $.ajax({
            url: '/api/grupe-robe?size=10&page='+page,
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
                                <a class="btn btn-outline-primary pregledRobe" href="robe-usluge.html?grupa=${grupa.id}">GET</a>
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

});