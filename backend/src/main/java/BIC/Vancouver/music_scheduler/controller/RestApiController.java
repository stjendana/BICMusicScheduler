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

    @RequestMapping("/testSave")
    void SaveSchedules()
    {
        List<schedule> mockSchedules = createMockSchedule();

        try {
            //this.scheduleService.SaveSchedule(mockSchedules);
        } catch ( Exception ex) {
            System.out.println("Error Found: !" + ex.getMessage());
        }

        try {
            File temporaryFile = pdfGeneratorUtil.CreatePdf(mockSchedules);
            mailService.sendSimpleMessage(temporaryFile);
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

        Date week1 = new Date();
        LocalDateTime dt = week1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        dt = dt.plusWeeks(1);
        Date week2 = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
        dt = dt.plusWeeks(1);
        Date week3 = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
        dt = dt.plusWeeks(1);
        Date week4 = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());

        List<schedule> mockSchedules = new ArrayList<schedule>();
        mockSchedules.add(new schedule(1,1,5, week1));
        mockSchedules.add(new schedule(1,2,10, week1));
        mockSchedules.add(new schedule(1,2,9, week1));
        mockSchedules.add(new schedule(1,2,11, week1));
        mockSchedules.add(new schedule(1,3,6, week1));
        mockSchedules.add(new schedule(1,4,15, week1));
        mockSchedules.add(new schedule(1,5,7, week1));
        mockSchedules.add(new schedule(1,6,13, week1));
        mockSchedules.add(new schedule(1,7,14, week1));
        mockSchedules.add(new schedule(1,8,8, week1));
        mockSchedules.add(new schedule(1,9,12, week1));

        mockSchedules.add(new schedule(1,1,6, week2));
        mockSchedules.add(new schedule(1,2,9, week2));
        mockSchedules.add(new schedule(1,2,10, week2));
        mockSchedules.add(new schedule(1,2,11, week2));
        mockSchedules.add(new schedule(1,3,5, week2));
        mockSchedules.add(new schedule(1,4,15, week2));
        mockSchedules.add(new schedule(1,5,7, week2));
        mockSchedules.add(new schedule(1,6,13, week2));
        mockSchedules.add(new schedule(1,7,14, week2));
        mockSchedules.add(new schedule(1,8,12, week2));
        mockSchedules.add(new schedule(1,9,8, week2));

        mockSchedules.add(new schedule(1,1,5, week3));
        mockSchedules.add(new schedule(1,2,10, week3));
        mockSchedules.add(new schedule(1,2,9, week3));
        mockSchedules.add(new schedule(1,2,11, week3));
        mockSchedules.add(new schedule(1,3,6, week3));
        mockSchedules.add(new schedule(1,4,15, week3));
        mockSchedules.add(new schedule(1,5,7, week3));
        mockSchedules.add(new schedule(1,6,13, week3));
        mockSchedules.add(new schedule(1,7,14, week3));
        mockSchedules.add(new schedule(1,8,8, week3));
        mockSchedules.add(new schedule(1,9,12, week3));

        mockSchedules.add(new schedule(1,1,6, week4));
        mockSchedules.add(new schedule(1,2,9, week4));
        mockSchedules.add(new schedule(1,2,10, week4));
        mockSchedules.add(new schedule(1,2,11, week4));
        mockSchedules.add(new schedule(1,3,5, week4));
        mockSchedules.add(new schedule(1,4,15, week4));
        mockSchedules.add(new schedule(1,5,7, week4));
        mockSchedules.add(new schedule(1,6,13, week4));
        mockSchedules.add(new schedule(1,7,14, week4));
        mockSchedules.add(new schedule(1,8,12, week4));
        mockSchedules.add(new schedule(1,9,8, week4));

        return mockSchedules;
    }
}
