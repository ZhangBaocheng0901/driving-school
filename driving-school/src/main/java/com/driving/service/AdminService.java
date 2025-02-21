
package com.driving.service;

import com.driving.dto.response.StatsResponse;
import com.driving.entity.Appointment;
import com.driving.repository.AppointmentRepository;
import com.driving.repository.StudentRepository;
import com.driving.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CoachRepository coachRepository;

    // 获取学员每日练习时长统计
    @Transactional(readOnly = true)
    public StatsResponse getStudentDailyPractice() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        List<Appointment> studentAppointments = appointmentRepository.findByStudentIdAndTimeRange(null, startOfDay,
                endOfDay);

        Map<Long, Long> studentStats = studentAppointments.stream().collect(Collectors
                .groupingBy(Appointment::getStudentId, Collectors.summingLong(Appointment::calculateDuration)));

        return new StatsResponse(studentStats, getCoachDailyTeaching());
    }

    // 获取教练每日教学时长统计
    @Transactional(readOnly = true)
    public Map<Long, Long> getCoachDailyTeaching() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        List<Appointment> coachAppointments = appointmentRepository.findByCoachIdAndTimeRange(null, startOfDay,
                endOfDay);

        return coachAppointments.stream().collect(
                Collectors.groupingBy(Appointment::getCoachId, Collectors.summingLong(Appointment::calculateDuration)));
    }
}