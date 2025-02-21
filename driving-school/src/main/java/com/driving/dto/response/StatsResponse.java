
package com.driving.dto.response;

import java.util.Map;

public class StatsResponse {

    private Map<Long, Long> studentPractice; // 学员ID -> 练习总时长（分钟）
    private Map<Long, Long> coachTeaching; // 教练ID -> 教学总时长（分钟）

    public StatsResponse(Map<Long, Long> studentPractice, Map<Long, Long> coachTeaching) {
        this.studentPractice = studentPractice;
        this.coachTeaching = coachTeaching;
    }

    // Getters
    public Map<Long, Long> getStudentPractice() {
        return studentPractice;
    }

    // ...其他字段的getter
}