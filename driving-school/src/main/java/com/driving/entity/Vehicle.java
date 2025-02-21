
package com.driving.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String licensePlate; // 车牌号

    @Enumerated(EnumType.STRING)
    private VehicleStatus status; // 状态枚举

    @Column(nullable = true)
    private LocalDateTime lastMaintenanceDate; // 最近保养日期

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments; // 关联的预约

    // 枚举类型定义
    public enum VehicleStatus {
        AVAILABLE, // 可用
        UNDER_MAINTENANCE, // 维修中
        IN_USE // 已使用（被预约中）
    }
}