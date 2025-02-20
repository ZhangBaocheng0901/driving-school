// src/main/java/com/driving/service/CoachService.java
package com.driving.service;

import com.driving.entity.Coach;
import com.driving.entity.Appointment;
import com.driving.exception.ResourceNotFoundException;
import com.driving.repository.CoachRepository;
import com.driving.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 查询所有教练
    public Page<Coach> findAllCoaches(Pageable pageable) {
        return coachRepository.findAll(pageable);
    }

    // 根据ID查询教练
    public Coach findCoachById(Long id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach not found with id: " + id));
    }

    // 创建/更新教练
    @Transactional
    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    // 删除教练
    @Transactional
    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }

    // 查询教练的所有预约
    public List<Appointment> getAppointmentsByCoach(Long coachId) {
        return appointmentRepository.findByCoachId(coachId);
    }

    // 创建预约（教练视角）
    @Transactional
    public Appointment createAppointment(Appointment appointment, Long coachId) {
        Coach coach = findCoachById(coachId);
        appointment.setCoach(coach);
        return appointmentRepository.save(appointment);
    }

    // 更新教练状态（启用/禁用）
    @Transactional
    public Coach updateCoachStatus(Long id, boolean active) {
        Coach coach = findCoachById(id);
        coach.setActive(active);
        return coachRepository.save(coach);
    }
}