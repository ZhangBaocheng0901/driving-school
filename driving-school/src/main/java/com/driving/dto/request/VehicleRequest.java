
package com.driving.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VehicleRequest {

    @NotNull(message = "车牌号不能为空")
    @Size(min = 4, max = 20, message = "车牌号长度必须在4-20字符之间")
    private String licensePlate;

    @NotNull(message = "状态不能为空")
    private String status; // AVAILABLE/UNDER_MAINTENANCE/IN_USE

    @Size(max = 255, message = "最后维护日期描述不超过255字符")
    private String maintenanceNotes;

    // Getters & Setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    // ...其他字段的getter/setter
}