package za.co.staffschedule.service;

import za.co.staffschedule.exception.ScheduleException;
import za.co.staffschedule.request.ScheduleRequest;
import za.co.staffschedule.request.ScheduleResponse;
import za.co.staffschedule.response.UserResponse;

public interface ScheduleService {


    ScheduleResponse createSchedule(ScheduleRequest request) throws ScheduleException;

    ScheduleResponse deleteSchedule(Long scheduleId) throws ScheduleException;

    UserResponse getSchedules() throws ScheduleException;

    ScheduleResponse getScheduleById(Long scheduleId) throws ScheduleException;
}
