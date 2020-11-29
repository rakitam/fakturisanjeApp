$(document).ready(function(){
    var stavkeTable = $('#stavkeTable');
    var pagination = $('#pagination');
    var nazivInput = $('#naziv');
    var page = 0;
    var urlSearchParams = getParameters();
    getStavke();

    function getStavke() {
    	var naziv = nazivInput.val();

        $.ajax({
            url: '/api/cenovnici/'+ urlSearchParams['id'] + '/stavke-cenovnika?size=10&page='+page,
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