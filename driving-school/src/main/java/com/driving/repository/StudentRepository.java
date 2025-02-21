
package com.driving.repository;

import com.driving.entity.Appointment;
import com.driving.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 按学生ID查询预约
    List<Appointment> findByStudentId(Long studentId);
}