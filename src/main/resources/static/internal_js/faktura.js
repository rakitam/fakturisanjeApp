$(document).ready(function(){
    var stavkeTable = $('#stavkeTable');
    var urlSearchParams = getParameters();
    getFaktura();

    function getFaktura() {
        $.ajax({
            url: '/api/fakture/'+ urlSearchParams['id'],
            headers: {"Authorization": localStorage.getItem('token')},
            success: function (data) {
                //$('#brojFaktureId').val(data.brojFakture + '/' + data.poslovnaGodina.godina);
                $('.brojFakture').text(data.brojFakture + '/' + data.poslovnaGodina.godina);
                $('#datumFakture').val(!data.datumFakture? '': new Date(data.datumFakture).toLocaleString());
                $('#datumValute').val(!data.datumValute? '':  new Date(data.datumValute).toLocaleString());
                $('#datumStorniranja').val(!data.datumStorniranja?'':  new Date(data.datumStorniranja).toLocaleString());
                $('#iznosBezRabata').val(data.iznosBezRabata);
                $('#ukupno').text(data.iznosZaPlacanje);
                $('#poreskaOsnovica').text(data.osnovica);
                $('#ukupanRabat').text(data.rabat);
                $('#ukupanPDV').text(data.ukupanPdv);
                $('#statusFakture').val(data.statusFakture);
                if (data.statusFakture != 'FORMIRANA') {
                    $('#plati').hide();
                    $('#storniraj').hide();
                }
                if(localStorage.getItem('role')=="ROLE_KORISNIK") {
                    $('#storniraj').hide();
                }
                if(data.statusFakture == 'PLACENA') {
                    $('#plati').hide();
                }
                if(data.statusFakture == 'STORNIRANA') {
                    $('#storniraj').hide();
                }
                $.ajax({
                    url: '/api/fakture/'+ urlSearchParams['id']+'/stavke',
                    headers: {"Authorization": localStorage.getItem('token')},
                    success: function (data) {
                        for (const stavka of data) {
                            stavkeTable.append(
                                `<tr>
                            <td>${stavka.id}</td>
                            <td>${stavka.robaUsluga.nazivRobeUsluge}</td>
                            <td>${stavka.robaUsluga.jedinicaMere}</td>
                            <td>${stavka.jedinicnaCena}</td>
                            <td>${stavka.kolicina}</td>
                            <td>${stavka.rabat}</td>
                            <td>${stavka.osnovicaZaPdv}</td>
                            <td>${stavka.procenatPdva}</td>
                            <td>${stavka.iznosPdva}</td>
                            <td>${stavka.iznosStavke + stavka.iznosPdva}</td>
                        </tr>`
                            )
                        };
                    }
                });
            }
        });
    }

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

    //TODO: NE RADI GOVNO UBICU SE
    $('#napravi_izvestaj').click(function () {
        console.log(urlSearchParams['id']);
        $.ajax({
            method: 'GET',
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/fakture/' + urlSearchParams['id'] + '/napravi-izvestaj',
            success: function (data) {
                $("#prikazi_izvestaj").attr('src', '/api/fakture/' + urlSearchParams['id'] + '/napravi-izvestaj');
                $("#prikazi_pdf").modal('show');
            }
        });
    });


    $('#plati').click(function () {
        $.ajax({
            method: 'PUT',
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/fakture/'+ urlSearchParams['id']+'/plati',
            success: function (data) {
                window.location.reload();
            }
        });
    });

    $('#storniraj').click(function () {
        $.ajax({
            method: 'PUT',
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/fakture/'+ urlSearchParams['id']+'/storniraj',
            success: function (data) {
                window.location.reload();
            }
        });
    });

});