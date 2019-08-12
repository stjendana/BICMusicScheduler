package BIC.Vancouver.music_scheduler.service;

import BIC.Vancouver.music_scheduler.model.ministry;
import BIC.Vancouver.music_scheduler.model.user;
import BIC.Vancouver.music_scheduler.model.userMinistry;
import BIC.Vancouver.music_scheduler.repository.MinistryRepository;
import BIC.Vancouver.music_scheduler.repository.UserMinistryRepository;
import BIC.Vancouver.music_scheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public @ResponseBody Iterable<user>
    GetUsers() {
        return userRepository.findAll();
    }

}
