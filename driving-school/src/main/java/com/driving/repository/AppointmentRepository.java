// src/main/java/com/driving/repository/AppointmentRepository.java
package com.driving.repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * 查找学员的预约记录（按时间倒序）
     */
    List<Appointment> findByStudentIdOrderByStartTimeDesc(Long studentId);

    /**
     * 查找教练的待确认预约
     */
    @Query("SELECT a FROM Appointment a WHERE a.coach.id = :coachId AND a.status = 'PENDING'")
    List<Appointment> findPendingByCoachId(@Param("coachId") Long coachId);

    /**
     * 检查时间冲突
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Appointment a " +
            "WHERE a.coach.id = :coachId " +
            "AND a.status NOT IN ('CANCELLED', 'REJECTED') " +
            "AND ((a.startTime BETWEEN :start AND :end) OR (a.endTime BETWEEN :start AND :end))")
    boolean existsTimeConflict(
            @Param("coachId") Long coachId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}