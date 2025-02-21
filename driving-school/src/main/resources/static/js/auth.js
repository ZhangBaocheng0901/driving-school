// src/main/resources/static/js/auth.js
document.getElementById('login-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const userType = document.getElementById('user-type').value;

    fetch('/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token') // 如果已登录
        },
        body: JSON.stringify({ username, password, userType })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem('token', data.token);
                window.location.href = '/' + userType + '-dashboard.html';
            } else {
                alert('登录失败，请检查用户名和密码');
            }
        });
});