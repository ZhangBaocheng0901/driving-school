
package com.driving.service;

import com.driving.dto.request.TimeSlotRequest;
import com.driving.entity.Appointment;
import com.driving.entity.Coach;
import com.driving.entity.TimeSlot;
import com.driving.repository.CoachRepository;
import com.driving.repository.TimeSlotRepository;
import com.driving.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    // 获取教练信息
    @Transactional(readOnly = true)
    public Coach getCoachById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coach not found"));
    }

    // 创建时间段
    @Transactional
    public void createTimeSlot(Long coachId, TimeSlotRequest request) {
        Coach coach = getCoachById(coachId);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setCoach(coach);
        timeSlot.setStartTime(request.getStartTime());
        timeSlot.setEndTime(request.getEndTime());
        timeSlot.setDescription(request.getDescription());

        timeSlotRepository.save(timeSlot);
    }

    // 查询空闲时间段
    @Transactional(readOnly = true)
    public List<Appointment> getFreeTimeslots(Long coachId) {
        Coach coach = getCoachById(coachId);
        return coach.getAppointments().stream().filter(appointment -> appointment.getStatus().equals("SCHEDULED"))
                .collect(Collectors.toList());
    }
}