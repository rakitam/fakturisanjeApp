$(document).ready(function(){
    var cenovniciTable = $('#cenovniciTable');
    getCenovnici();





    function getCenovnici() {
        $.ajax({
            url: '/api/cenovnici',
            success: function (data) {
                console.log(data);
                for (const cenovnik of data) {
                    console.log(cenovnik);
                    cenovniciTable.append(
                        `
                        <tr>
                            <td></td>
                            <td>${cenovnik.preduzece.naziv}</td>
                            <td>${cenovnik.datumVazenja}</td>
                            <td><a href='cenovnik.html?id=${cenovnik.id}' class='btn btn-outline-primary'>GET</a></td>
                        </tr>
                        `
                    )
                }
            }
        });
    }

});