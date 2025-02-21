
package com.driving.controller;

import com.driving.dto.request.VehicleRequest;
import com.driving.entity.Vehicle;
import com.driving.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@PreAuthorize("hasRole('ADMIN')")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // 获取所有可用车辆
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllAvailableVehicles() {
        return ResponseEntity.ok(vehicleService.getAvailableVehicles());
    }

    // 创建车辆
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleRequest request) {
        Vehicle vehicle = vehicleService.createVehicle(request);
        return ResponseEntity.ok(vehicle);
    }

    // 更新车辆状态
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateVehicleStatus(@PathVariable Long id, @RequestParam String status) {
        vehicleService.updateVehicleStatus(id, status);
        return ResponseEntity.ok().build();
    }

    // 获取车辆详情
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }
}