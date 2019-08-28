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
import java.util.*;

@RestController
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
        Date rightNow = new Date();
        List<schedule> mockSchedules = new ArrayList<schedule>();
        mockSchedules.add(new schedule(1,1,1, rightNow));
        mockSchedules.add(new schedule(2,2,2, rightNow));
        mockSchedules.add(new schedule(3,3,3, rightNow));
        mockSchedules.add(new schedule(4,4,4, rightNow));

        String pdfTemporaryPath;
        try {
            this.scheduleService.SaveSchedule(mockSchedules);
        } catch ( Exception ex) {
            System.out.println("Error Found: !" + ex.getMessage());
        }
        //
        try {
            pdfTemporaryPath = pdfGeneratorUtil.CreatePdf(mockSchedules);
        }catch (Exception ex) {
            System.out.println("Error Found: !" + ex.getMessage());
        }

    }

    @RequestMapping("/test")
    public void TestSendEmail()
    {
        mail newMail = new mail();
        newMail.setFrom("no-reply@bicvancouver.com");
        newMail.setTo("steven.tjendana@gmail.com");
        newMail.setSubject("Sending Email Attachment Configuration Example");
        newMail.setContent("This tutorial demonstrates how to send an email with attachment using Spring Framework.");

        try {
            mailService.sendSimpleMessage(newMail);
        }
        catch ( MessagingException ex) {
            System.out.println("Error Found: !" + ex.getMessage());
        }
    }


}
