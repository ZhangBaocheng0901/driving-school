// src/main/resources/static/js/admin.js
function loadStudents() {
    fetch('/api/admin/students', {
        headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') },
        params: { page: 1, size: 10 }
    })
        .then(response => response.json())
        .then(data => {
            const studentsTable = document.getElementById('students-table');
            studentsTable.innerHTML = data.students
                .map(student => `
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.phone}</td>
                    <td><a href="/admin/student/${student.id}">查看详情</a></td>
                </tr>
            `)
                .join('');
        });
}

// 初始化加载学员列表
loadStudents();