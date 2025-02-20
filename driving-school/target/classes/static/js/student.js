// src/main/resources/static/js/student.js
function loadAppointments() {
    fetch('/api/student/appointments', {
        headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
    })
        .then(response => response.json())
        .then(data => {
            const appointmentsList = document.getElementById('appointments');
            appointmentsList.innerHTML = data.appointments
                .map(appointment => `
                <div class="appointment-card">
                    <h3>${appointment.title}</h3>
                    <p><strong>时间：</strong> ${appointment.startTime}</p>
                    <p><strong>教练：</strong> ${appointment.coachName}</p>
                </div>
            `)
                .join('');
        });
}

// 初始化加载预约数据
loadAppointments();