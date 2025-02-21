
package com.driving.service;

import com.driving.dto.request.VehicleRequest;
import com.driving.entity.Vehicle;
import com.driving.entity.Appointment;
import com.driving.repository.VehicleRepository;
import com.driving.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // 获取所有可用车辆
    @Transactional(readOnly = true)
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAvailableVehicles();
    }

    // 创建车辆
    @Transactional
    public Vehicle createVehicle(VehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setStatus(Vehicle.VehicleStatus.AVAILABLE);
        vehicle.setMaintenanceNotes(request.getMaintenanceNotes());

        return vehicleRepository.save(vehicle);
    }

    // 更新车辆状态
    @Transactional
    public void updateVehicleStatus(Long vehicleId, String status) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        vehicle.setStatus(status);
        vehicleRepository.save(vehicle);
    }

    // 获取车辆详情
    @Transactional(readOnly = true)
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }
}