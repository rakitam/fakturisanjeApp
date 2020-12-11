$(document).ready(function(){
    var stavkeTable = $('#stavkeTable');
    var urlSearchParams = getParameters();
    getFaktura();

    function getFaktura() {
        $.ajax({
            url: '/api/fakture/'+ urlSearchParams['id'],
            headers: {"Authorization": localStorage.getItem('token')},
            success: function (data) {
                $('.brojFakture').text(data.brojFakture + '/' + data.poslovnaGodina.godina);
                $('#datumFakture').text(!data.datumFakture? '': new Date(data.datumFakture).toLocaleString());
                $('#datumValute').text(!data.datumValute? '':  new Date(data.datumValute).toLocaleString());
                $('#datumStorniranja').text(!data.datumStorniranja?'':  new Date(data.datumStorniranja).toLocaleString());
                $('#datumPlacanja').text(!data.datumPlacanja?'':  new Date(data.datumPlacanja).toLocaleString());
                $('#iznosBezRabata').val(data.iznosBezRabata);
                $('#ukupno').text(data.iznosZaPlacanje);
                $('#poreskaOsnovica').text(data.osnovica);
                $('#ukupanRabat').text(data.rabat);
                $('#ukupanPDV').text(data.ukupanPdv);
                $('#statusFakture').text(data.statusFakture);
                $('#imePrezime').text(data.korisnik.imePrezime);
                $('#brojTelefona').text(data.korisnik.brojTelefona);
                $('#email').text(data.korisnik.email);
                $('#brojTekucegRacuna').text(data.korisnik.tekuciRacun);
                $('#adresaKorisnika').text(data.korisnik.adresaKorisnika);
                if(localStorage.getItem('role')=="ROLE_KORISNIK") {
                    $('#storniraj').hide();
                }
                if(data.statusFakture == 'PLACENA') {
                    $('#plati').hide();
                }
                if(data.statusFakture == 'STORNIRANA') {
                    $('#storniraj').hide();
                    $('#plati').hide();
                }

                $.ajax({
                    url: '/api/fakture/'+ urlSearchParams['id']+'/stavke',
                    headers: {"Authorization": localStorage.getItem('token')},
                    success: function (data) {
                        for (const stavka of data) {
                            stavkeTable.append(
                        `<tr>
                            <td>${stavka.robaUsluga.nazivRobeUsluge}</td>
                            <td>${stavka.robaUsluga.jedinicaMere}</td>
                            <td>${stavka.jedinicnaCena}</td>
                            <td>${stavka.kolicina}</td>
                            <td>${stavka.rabat}</td>
                            <td>${stavka.osnovicaZaPdv}</td>
                            <td>${stavka.procenatPdva}</td>
                            <td>${stavka.iznosPdva}</td>
                            <td>${stavka.osnovicaZaPdv + stavka.iznosPdva}</td>
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

    //https://stackoverflow.com/questions/34586671/download-pdf-file-using-jquery-ajax/34587987?fbclid=IwAR3jIB53FgLggv7sLx6n2s_wEZm90oZn_LTpXjMFm0uTulHblhj6bwkm6NM
    $('#napravi_izvestaj').click(function () {
        $.ajax({
            method: 'GET',
            headers: {"Authorization": localStorage.getItem('token')},
            url: '/api/fakture/' + urlSearchParams['id'] + '/napravi-izvestaj',
            xhrFields: {
                responseType: 'blob'
            },
            success: function (blob) {
                var url = URL.createObjectURL(blob);
                window.open(url)
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