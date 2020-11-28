$(document).ready(function(){
    var urlSearchParams = getParameters();
    getFaktura();

    function getFaktura() {
        $.ajax({
            url: '/api/fakture/'+ urlSearchParams['id'],
            success: function (data) {
                $('#brojFakture').val(data.brojFakture + '/' + data.poslovnaGodina.godina);
                $('#datumFakture').val(data.datumFakture);
                $('#datumValute').val(data.datumValute);
                $('#iznosBezRabata').val(data.iznosBezRabata);
                $('#iznosZaPlacanje').val(data.iznosZaPlacanje);
                $('#osnovica').val(data.osnovica);
                $('#rabat').val(data.rabat);
                $('#statusFakture').val(data.statusFakture);
                if (data.statusFakture != 'FORMIRANA') {
                    $('#plati').hide();
                    $('#storniraj').hide();
                }
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

    $('#plati').click(function () {
        $.ajax({
            method: 'PUT',
            url: '/api/fakture/'+ urlSearchParams['id']+'/plati',
            success: function (data) {
                window.location.reload();
            }
        });
    });

    $('#storniraj').click(function () {
        $.ajax({
            method: 'PUT',
            url: '/api/fakture/'+ urlSearchParams['id']+'/storniraj',
            success: function (data) {
                window.location.reload();
            }
        });

    });

});