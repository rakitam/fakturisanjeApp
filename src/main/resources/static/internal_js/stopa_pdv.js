$(document).ready(function(){
    var stopaPDVTabela = $('#stopaPDVTabela');
    var grupe = [];
    var urlSearchParams = getParameters();

    $("#modal_stopa").load("dialog/dodavanje_stopa_pdv.html", function () {
        $.ajax({
            url: '/api/pdv',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                var selectPdv = $('#pdv');
                grupe = data;
                for (var i=0; i<data.length; i++) {
                    selectPdv.append(`<option value="${i}">${data[i].nazivPDV}</option>`);
                }
            }
        });
    });

    getStopaPDV();

    function getStopaPDV() {
        $.ajax({
            url: '/api/pdv/' + urlSearchParams['id'] + '/stopa',
            headers: {'Authorization': localStorage.getItem('token')},
            success: function (data) {
                stopaPDVTabela.empty();
                for (const stopa of data) {
                    stopaPDVTabela.append(
                        `<tr>
                            <td>${stopa.id}</td>
                            <td>${new Date(stopa.datumVazenja).toLocaleString()}</td>
                            <td>${stopa.procenat}</td>
                            <td>${stopa.pdv.nazivPDV}</td>
                            <td></td>
                        </tr>`
                    )
                }
            }
        });
    }

    $('#dodaj_stopu').click(function (e) {
        e.preventDefault();
        $('#dodavanje_stope').modal('show');
        $('#datumVazenja').val(new Date().toISOString().split('T')[0]);
        $('#procenat').val(1);
    });


    $(document).on('click', '#potvrda_dodavanja_stope', function () {
        $('.alert').alert('close')
        var procenat = $('#procenat').val();
        if (procenat > 100 || procenat < 1) {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Procenat mora biti od 1 do 100</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var datumVazenja = $('#datumVazenja').val();
        if (datumVazenja == '') {
            $('#messages').append(
                `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Datum nije selektovan</div>`);
            setTimeout(function () {$('.alert').alert('close')}, 3000);
            return;
        }
        var pdv = $('#pdv').val();

        var novaStopa = {
            datumVazenja: datumVazenja,
            procenat: procenat,
            pdv: grupe[pdv]
        }

        $.ajax({
            url: '/api/stope-pdv',
            headers: {'Authorization': localStorage.getItem('token')},
            type: 'POST',
            data: JSON.stringify(novaStopa),
            contentType:"application/json",
            success: function(data) {
                alert("Stopa uspesno dodata.")
                console.log('uspesno dodata stopa');
                $('#dodavanje_stope').modal('hide');
                getStopaPDV();
            },
            error: function () {
                $('#messages').append(
                    `<div class=" alert alert-danger alert-dismissible fade show" role="alert">Datum ne moze biti u proslosti</div>`);
                setTimeout(function () {$('.alert').alert('close')}, 3000);
            }
        });
    });

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

});