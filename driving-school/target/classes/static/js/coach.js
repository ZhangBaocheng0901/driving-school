// src/main/resources/static/js/coach.js
function loadAvailableSlots() {
    fetch('/api/coach/availability', {
        headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
    })
        .then(response => response.json())
        .then(data => {
            const slotsList = document.getElementById('available-slots');
            slotsList.innerHTML = data.slots
                .map(slot => `
                <div class="time-slot">
                    <p><strong>开始时间：</strong> ${slot.startTime}</p>
                    <p><strong>结束时间：</strong> ${slot.endTime}</p>
                </div>
            `)
                .join('');
        });
}

// 初始化加载可用时间段
loadAvailableSlots();