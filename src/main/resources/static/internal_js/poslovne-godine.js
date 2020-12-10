$(document).ready(function () {
    $('#nav').load('nav.html');
    var godineTable = $('#godineTable');
    getPoslovneGodine();


    function getPoslovneGodine(){
        $.ajax({
            url: '/api/poslovne-godine',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function(data) {
                for (const godina of data) {
                    godineTable.append(
                        `<tr>
                            <td>${godina.id}</td>
                            <td>${godina.godina}</td>
                            <td>${godina.zakljucana}</td>
                            <td>
                                <td><button godina_id="${godina.id}" type=button id='zakljucaj' class='btn btn-outline-primary'>Zakljucaj</button></td>
                            </td>
                            <td>
                                <td><button godina_kif="${godina.id}" type=button id='kif' class='btn btn-primary'>Fakture</button></td>
                            </td>
                        </tr>`
                    )
                }
            }
        });
    }

    $(document).on('click', '#zakljucaj', function (event) {
        event.preventDefault();
        var godinaId = $(this).attr("godina_id");
        $.ajax({
            method: 'PUT',
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/poslovne-godine/'+ godinaId +'/zakljucaj',
            success: function (data) {
                alert("Poslovna godina uspesno zakljucana")
                window.location.reload();
            },
            error: function () {
                alert("Poslovna godina je vec zakljucana.")
            }
        });
    });

    //TODO: Dovrsiti dobavljanje faktura za poslovnu godinu
    $(document).on('click', '#kif', function (event) {
        event.preventDefault();
        var godinaKifId = $(this).attr("godina_kif");
        $.ajax({
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/fakture/godina?id='+ godinaKifId,
            success: function (data) {
                window.location.replace();
            },
        });
    });
});