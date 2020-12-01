$(document).ready(function () {
    $('#footer').load('footer.html'),
    $('#nav').load('nav.html', function () {
        if (!localStorage.getItem('token')) {
            localStorage.clear();
            window.location.href = '/login.html'
        }

        if(localStorage.getItem('role')=="ROLE_KORISNIK") {
            $('.admin-only').remove();
        }
    });

    $(document).on('click', '#logout', function () {
        localStorage.clear();
        window.location.href = '/login.html';
    });
});