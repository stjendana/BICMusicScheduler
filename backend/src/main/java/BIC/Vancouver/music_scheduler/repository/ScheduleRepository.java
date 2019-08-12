package BIC.Vancouver.music_scheduler.repository;

import BIC.Vancouver.music_scheduler.model.schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<schedule, Integer> {
}
