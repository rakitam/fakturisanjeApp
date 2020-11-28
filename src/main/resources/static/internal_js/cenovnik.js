$(document).ready(function(){
    var stavkeTable = $('#stavkeTable');
    var pagination = $('#pagination');
    var page = 0;
    getStavke();

    function getStavke() {

        var url = (window.location).href;
        var id = url.substring(url.lastIndexOf('=') + 1);

        $.ajax({
            url: '/api/cenovnici/'+ id + '/stavke-cenovnika?size=10&page='+page,
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


    pagination.on("click","li.page-item", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        getStavke();
    });


});