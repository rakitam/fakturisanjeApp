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
                        for (const stavke of data) {
                            korpaTable.append(
                                `<tr>
                                    <td>${stavke.id}</td>
                                    <td>${stavke.robaUsluga.nazivRobeUsluge}</td>
                                    <td>${stavke.jedinicnaCena}</td>
                                    <td>${stavke.kolicina}</td>
                                    <td>${stavke.iznosStavke}</td>
                                    <td>${stavke.iznosPdva}</td>
                                    <td>
                                        <button class="btn btn-danger brisanjeStavke" stavke_id="${stavke.id}">Obrisi</button>
                                    </td>
                                </tr>`)
                        }
                        if (data.length == 0) {
                            korpaTable.append(
                                `<tr style="border-top: 2px solid black">
                                <td style="padding-top: 30px" colspan="4"><span class="float-right">Ukupna Cena:</span></td>
                                <td style="padding-top: 30px"><b>${faktura.iznosZaPlacanje}</b></td>
                                <td style="padding-top: 30px"></td>
                            </tr>`);
                        } else {
                            korpaTable.append(
                                `<tr style="border-top: 2px solid black">
                                    <td style="padding-top: 30px" colspan="4"><span class="float-right">Ukupna cena:</span></td>
                                    <td style="padding-top: 30px"><b>${faktura.iznosZaPlacanje}</b></td>
                                    <td style="padding-top: 30px">
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
        var stavkaId = $(this).attr("stavke_id");
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