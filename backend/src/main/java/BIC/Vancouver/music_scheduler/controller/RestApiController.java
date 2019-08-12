package BIC.Vancouver.music_scheduler.controller;

import BIC.Vancouver.music_scheduler.model.ministry;
import BIC.Vancouver.music_scheduler.model.schedule;
import BIC.Vancouver.music_scheduler.model.user;
import BIC.Vancouver.music_scheduler.model.userMinistry;
import BIC.Vancouver.music_scheduler.service.MinistryService;
import BIC.Vancouver.music_scheduler.service.ScheduleService;
import BIC.Vancouver.music_scheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class RestApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private MinistryService ministryService;
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/")
    public String index() {
        return "REST Back end server is initiated!";
    }

    @RequestMapping("/users")
    public @ResponseBody
    Iterable<user> GetUsers() {
        return this.userService.GetUsers();
    }

    @RequestMapping("/ministries")
    public @ResponseBody
    Iterable<ministry> GetMinistries() {
        return this.ministryService.GetMinistries();
    }

    @RequestMapping("/userMinistries")
    public @ResponseBody
    Iterable<userMinistry> GetUserMinistries() {
        return this.ministryService.GetUserMinistries();
    }

    @RequestMapping("/schedules")
    public @ResponseBody
    Iterable<schedule> GetSchedules() {
        return this.scheduleService.GetSchedules();
    }
}
