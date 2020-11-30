$(document).ready(function () {


    $('#login').click(function () {
        var email = $('#email').val();
        var password = $('#password').val();

        var login = {
            username: email,
            password: password
        }
        $.ajax({
            method: 'POST',
            url: '/api/login',
            data: JSON.stringify(login),
            contentType:"application/json",
            success: function (data) {
                localStorage.setItem('token', data.token);
                localStorage.setItem('email', data.username);
                localStorage.setItem('role', data.role);
                window.location.href = '/';
            }
        });
    });
});