package BIC.Vancouver.music_scheduler.util;

import BIC.Vancouver.music_scheduler.model.monthSchedule;
import BIC.Vancouver.music_scheduler.model.schedule;
import BIC.Vancouver.music_scheduler.model.user;
import BIC.Vancouver.music_scheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PdfGeneratorUtil {

    @Autowired
    private UserService userService;

    @Autowired
    private TemplateEngine templateEngine;
    private String templateName = "pdfHtmlTemplate";

    private monthSchedule process(Iterable<schedule> schedules)
    {
        // Convert to List
        monthSchedule ms = new monthSchedule();
        List<schedule> lst  = new ArrayList<schedule>();
        schedules.forEach(lst::add);

        // Process Data
        List<Date> distinctDates = lst.stream().map(schedule::getDate).distinct().collect(Collectors.toList());
        ms.setDates(lst.stream().map(schedule::getDate).map(dt -> formatDate(dt) ).distinct().collect(Collectors.toList()));
        ms.setWorshipLeaders(this.GetTableDataPerRole(lst, distinctDates, 1));
        ms.setSingers(this.GetTableDataPerRole(lst, distinctDates, 2));
        ms.setKeyboardUsers(this.GetTableDataPerRole(lst, distinctDates, 3));
        ms.setFillerUsers(this.GetTableDataPerRole(lst, distinctDates, 4));
        ms.setDrumUsers(this.GetTableDataPerRole(lst, distinctDates, 5));
        ms.setBassUsers(this.GetTableDataPerRole(lst, distinctDates, 6));
        ms.setGuitarUsers(this.GetTableDataPerRole(lst, distinctDates, 7));
        ms.setMultimediaUsers(this.GetTableDataPerRole(lst, distinctDates, 8));
        ms.setSoundSystemUsers(this.GetTableDataPerRole(lst, distinctDates, 9));

        return ms;
    }

    private List<List<user>> GetUsersPerWeek(List<schedule> schedules, List<Date> distinctDates, int ministryId)
    {
        return distinctDates.stream().map(date -> GetRoleForThisWeek( schedules, date, ministryId)).collect(Collectors.toList());
    }

    private List<List<user>> TranslateToTableData(List<List<user>> role)
    {
        int max = role.stream().mapToInt(List::size).filter(q -> q >= 0).max().orElse(0);
        List<List<user>> processed = new ArrayList<>();
        for(int i = 0; i<max; i++) {
            int finalI = i;
            List<user> x = role.stream().map(a -> a.get(finalI)).collect(Collectors.toList());
            processed.add(x);
        }
        return processed;
    }

    private List<List<user>> GetTableDataPerRole(List<schedule> schedules, List<Date> distinctDates, int ministryId)
    {
        // Get users per week
        List<List<user>> usersPerWeek = GetUsersPerWeek(schedules, distinctDates, ministryId);
        return TranslateToTableData(usersPerWeek);
    }

    private List<user> GetRoleForThisWeek(List<schedule> lst, Date dt, int ministryId)
    {
        return lst.stream()
                .filter(x -> x.getDate() == dt)
                .filter(x -> x.getMinistryId() == ministryId)
                .map(x -> getUser(x.getUserId()))
                .collect(Collectors.toList());
    }

    private String formatDate(Date dt)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy");
        return formatter.format(dt);
    }

    private user getUser(int userId)
    {
        return this.userService.GetUserById(userId);
    }

    public String CreatePdf(Iterable<schedule> schedules) throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");
        Context ctx = new Context();
        ctx.setVariable("schedule", process(schedules));

        String processedHtml = templateEngine.process(templateName, ctx);
        FileOutputStream os = null;

        final File outputFile = File.createTempFile("testPdfGenerated", ".pdf");
        try {
            os = new FileOutputStream(outputFile);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
            System.out.println("PDF created successfully");
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) { /*ignore*/ }
            }
        }

        return outputFile.getName();
    }
}
