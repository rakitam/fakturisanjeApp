$(document).ready(function(){
    var stavkeTable = $('#stavkeTable');
    getStavke();

    function getStavke() {

        var url = (window.location).href;
        var id = url.substring(url.lastIndexOf('=') + 1);

        $.ajax({
            url: '/api/cenovnici/'+ id +'/stavke-cenovnika',
            success: function (data) {
                console.log(data);
                for (const stavke of data) {
                    console.log(stavke);
                    stavkeTable.append(
                        `
                        <tr>
                            <td></td>
                            <td>${stavke.robaUsluga.nazivRobeUsluge}</td>
                            <td>${stavke.cena}</td>
                        </tr>
                        `
                    )
                }
            }
        });
    }

});