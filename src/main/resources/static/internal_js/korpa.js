$(document).ready(function () {
    var korpaTable = $('#korpa');
    getKorpa();

    function getKorpa() {
        $.ajax({
            url: '/api/fakture/active',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (faktura) {
                $.ajax({
                    url: '/api/fakture/'+faktura.id+'/stavke',
                    headers: {'Authorization': localStorage.getItem('token')},
                    success: function (data) {
                        for (const stavka of data) {
                            korpaTable.append(
                                `<tr>
                                    <td>${stavka.id}</td>
                                    <td>${stavka.robaUsluga.nazivRobeUsluge}</td>
                                    <td>${stavka.jedinicnaCena}</td>
                                    <td>${stavka.kolicina}</td>
                                    <td>${stavka.iznosStavke}</td>
                                    <td>
                                        <button class="btn btn-danger brisanjeStavke" stavka_id="${stavka.id}">Obrisi</button>
                                    </td>
                                </tr>`)
                        }
                        if (data.length == 0) {
                            korpaTable.append(
                                `<tr>
                                <td colspan="4"><span class="float-right">Ukupna Cena:</span></td>
                                <td><b>${faktura.iznosZaPlacanje}</b></td>
                                <td></td>
                            </tr>`);
                        } else {
                            korpaTable.append(
                                `<tr>
                                    <td colspan="4"><span class="float-right">Ukupna Cena:</span></td>
                                    <td><b>${faktura.iznosZaPlacanje}</b></td>
                                    <td>
                                        <button class="btn btn-primary" id="kreiraj" faktura_id="${faktura.id}">Kreiraj fakturu</button>
                                    </td>
                                </tr>`);
                        }
                    }
                });
            }
        });
    }


    $(document).on("click",".brisanjeStavke", function (event) {
        event.preventDefault();
        var stavkaId = $(this).attr("stavka_id");
        $.ajax({
            method: 'DELETE',
            url: '/api/stavke-fakture/'+stavkaId,
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                window.location.reload();
            }
        });
    });


    $(document).on('click', '#kreiraj', function (e) {
        e.preventDefault();
        var fakturaId = $(this).attr("faktura_id");
        $.ajax({
            method: 'PUT',
            url: '/api/fakture/'+fakturaId+'/formiraj',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                window.location.reload();
            }
        });
    });

});