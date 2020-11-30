$(document).ready(function () {
    $('#nav').load('nav.html', function () {
        if (!localStorage.getItem('token')) {
            localStorage.clear();
            window.location.href = '/login.html'
        }
    });

    $(document).on('click', '#logout', function () {
        localStorage.clear();
        window.location.href = '/login.html';
    });
});