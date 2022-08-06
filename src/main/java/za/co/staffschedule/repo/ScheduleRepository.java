package za.co.staffschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import za.co.staffschedule.model.Schedule;

@Service
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
