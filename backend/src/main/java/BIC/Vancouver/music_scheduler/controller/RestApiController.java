package BIC.Vancouver.music_scheduler.controller;

import BIC.Vancouver.music_scheduler.model.*;
import BIC.Vancouver.music_scheduler.service.MailService;
import BIC.Vancouver.music_scheduler.service.MinistryService;
import BIC.Vancouver.music_scheduler.service.ScheduleService;
import BIC.Vancouver.music_scheduler.service.UserService;
import BIC.Vancouver.music_scheduler.util.PdfGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@CrossOrigin
public class RestApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private MinistryService ministryService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MailService mailService;
    @Autowired
    private PdfGeneratorUtil pdfGeneratorUtil;

    @RequestMapping("/")
    public String index() {
        return "REST Back end server is initiated!";
    }

    @CrossOrigin()
    @GetMapping("/users")
    public @ResponseBody
    Iterable<user> GetUsers() {
        return this.userService.GetUsers();
    }

    @PostMapping("/user")
    public void SaveUser(@RequestBody user newUser) { userService.SaveUser(newUser);}

    @PutMapping("/user")
    public void UpdateUser(@RequestBody user editUser) { userService.UpdateUser(editUser);}


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

    @PostMapping("/saveSchedules")
    void SaveSchedules(@RequestBody List<schedule> schedulePayload)
    {
        List<schedule> mockSchedules = createMockSchedule();

        try {
            //this.scheduleService.SaveSchedule(mockSchedules);
        } catch ( Exception ex) {
            System.out.println("Error Found: !" + ex.getMessage());
        }

        try {
            File temporaryFile = pdfGeneratorUtil.CreatePdf(schedulePayload);
            //mailService.sendSimpleMessage(temporaryFile);
        }catch (Exception ex) {
            System.out.println("Error Found: !" + ex.getMessage());
        }
    }

    private List<schedule> createMockSchedule()
    {
        /*
         * 1 => Worship Leader
         * 2 => Singer
         * 3 => Keyboard
         * 4 => Filler
         * 5 => Drum
         * 6 => Bass
         * 7 => Guitar
         * 8 => Multimedia
         * 9 => Sound System
         * */

        System.out.println("Creating mock schedules...");

        Date week1 = new Date();
        LocalDateTime dt = week1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        dt = dt.plusWeeks(1);
        Date week2 = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
        dt = dt.plusWeeks(1);
        Date week3 = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
        dt = dt.plusWeeks(1);
        Date week4 = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
        dt = dt.plusWeeks(1);
        Date week5 = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());

        List<schedule> mockSchedules = new ArrayList<schedule>();
        mockSchedules.add(new schedule(1,1,15, week1.getTime()));
        mockSchedules.add(new schedule(1,2,15, week1.getTime()));
        mockSchedules.add(new schedule(1,2,15, week1.getTime()));
        mockSchedules.add(new schedule(1,2,15, week1.getTime()));
        mockSchedules.add(new schedule(1,3,15, week1.getTime()));
        mockSchedules.add(new schedule(1,4,15, week1.getTime()));
        mockSchedules.add(new schedule(1,5,15, week1.getTime()));
        mockSchedules.add(new schedule(1,6,15, week1.getTime()));
        mockSchedules.add(new schedule(1,7,15, week1.getTime()));
        mockSchedules.add(new schedule(1,8,15, week1.getTime()));
        mockSchedules.add(new schedule(1,9,15, week1.getTime()));

        mockSchedules.add(new schedule(1,1,15, week2.getTime()));
        mockSchedules.add(new schedule(1,2,15, week2.getTime()));
        mockSchedules.add(new schedule(1,2,15, week2.getTime()));
        mockSchedules.add(new schedule(1,2,15, week2.getTime()));
        mockSchedules.add(new schedule(1,3,15, week2.getTime()));
        mockSchedules.add(new schedule(1,4,15, week2.getTime()));
        mockSchedules.add(new schedule(1,5,15, week2.getTime()));
        mockSchedules.add(new schedule(1,6,15, week2.getTime()));
        mockSchedules.add(new schedule(1,7,15, week2.getTime()));
        mockSchedules.add(new schedule(1,8,15, week2.getTime()));
        mockSchedules.add(new schedule(1,9,15, week2.getTime()));

        mockSchedules.add(new schedule(1,1,15, week3.getTime()));
        mockSchedules.add(new schedule(1,2,15, week3.getTime()));
        mockSchedules.add(new schedule(1,2,15, week3.getTime()));
        mockSchedules.add(new schedule(1,2,15, week3.getTime()));
        mockSchedules.add(new schedule(1,3,15, week3.getTime()));
        mockSchedules.add(new schedule(1,4,15, week3.getTime()));
        mockSchedules.add(new schedule(1,5,15, week3.getTime()));
        mockSchedules.add(new schedule(1,6,15, week3.getTime()));
        mockSchedules.add(new schedule(1,7,15, week3.getTime()));
        mockSchedules.add(new schedule(1,8,15, week3.getTime()));
        mockSchedules.add(new schedule(1,9,15, week3.getTime()));

        mockSchedules.add(new schedule(1,1,15, week4.getTime()));
        mockSchedules.add(new schedule(1,2,15, week4.getTime()));
        mockSchedules.add(new schedule(1,2,15, week4.getTime()));
        mockSchedules.add(new schedule(1,2,15, week4.getTime()));
        mockSchedules.add(new schedule(1,3,15, week4.getTime()));
        mockSchedules.add(new schedule(1,4,15, week4.getTime()));
        mockSchedules.add(new schedule(1,5,15, week4.getTime()));
        mockSchedules.add(new schedule(1,6,15, week4.getTime()));
        mockSchedules.add(new schedule(1,7,15, week4.getTime()));
        mockSchedules.add(new schedule(1,8,15, week4.getTime()));
        mockSchedules.add(new schedule(1,9,15, week4.getTime()));

        mockSchedules.add(new schedule(1,1,15, week5.getTime()));
        mockSchedules.add(new schedule(1,2,15, week5.getTime()));
        mockSchedules.add(new schedule(1,2,15, week5.getTime()));
        mockSchedules.add(new schedule(1,2,15, week5.getTime()));
        mockSchedules.add(new schedule(1,3,15, week5.getTime()));
        mockSchedules.add(new schedule(1,4,15, week5.getTime()));
        mockSchedules.add(new schedule(1,5,15, week5.getTime()));
        mockSchedules.add(new schedule(1,6,15, week5.getTime()));
        mockSchedules.add(new schedule(1,7,15, week5.getTime()));
        mockSchedules.add(new schedule(1,8,15, week5.getTime()));
        mockSchedules.add(new schedule(1,9,15, week5.getTime()));

        System.out.println("Mock Schedules created successfully!");
        return mockSchedules;
    }
}
