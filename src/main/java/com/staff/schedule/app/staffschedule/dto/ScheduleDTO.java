package com.staff.schedule.app.staffschedule.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
