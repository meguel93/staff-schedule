package za.co.staffschedule.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleRequest {
    private Long userId;
    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endDate;
}
