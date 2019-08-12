package BIC.Vancouver.music_scheduler.service;

import BIC.Vancouver.music_scheduler.model.schedule;
import BIC.Vancouver.music_scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public @ResponseBody Iterable<schedule> GetSchedules(){ return scheduleRepository.findAll(); }
}
