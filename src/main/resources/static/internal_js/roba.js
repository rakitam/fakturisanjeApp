$(document).ready(function(){
    var robaTable = $('#robaTable');
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
                            <td>
                                <button roba_id="${roba.id}" type="button" class="btn btn-primary addToKorpa">Dodaj</button>
                            </td>
                        </tr>
                        `
                    )
                }
            }
        });
    }

    $(document).on('click', '.addToKorpa',function (e) {
        console.log($(this).attr('roba_id'));
    })

});