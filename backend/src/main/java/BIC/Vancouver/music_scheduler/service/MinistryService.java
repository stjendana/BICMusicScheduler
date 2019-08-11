package BIC.Vancouver.music_scheduler.service;

import BIC.Vancouver.music_scheduler.model.ministry;
import BIC.Vancouver.music_scheduler.repository.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class MinistryService {
    @Autowired
    private MinistryRepository ministryRepository;

    public @ResponseBody
    Iterable<ministry> GetMinistries() {
        return ministryRepository.findAll();
    }
}
