$(document).ready(function(){
    var robaTable = $('#robaTable');
    var buttonAddToKorpa = $("#addToKorpa");
    getRoba();

    function getRoba() {
        $.ajax({
            url: '/api/stavke-cenovnika',
            success: function (data) {
                console.log(data);
                for (const roba of data) {
                    console.log(roba);
                    robaTable.append(
                        `
                        <tr>
                            <td>${roba.id}</td>
                            <td>${roba.robaUsluga.nazivRobeUsluge}</td>
                            <td>${roba.cena}</td>
                            <td>${roba.robaUsluga.grupaRobe.nazivGrupe}</td>
                            <td>$("#addToKorpa").on("click", function (event) {
                                
                            }</td>
                        </tr>
                        `
                    )
                }
            }
        });
    }

});