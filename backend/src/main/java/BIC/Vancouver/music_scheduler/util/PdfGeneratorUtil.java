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
        ms.setWorshipLeaders(this.RetrieveUsersByMinistryId(lst, 1));
        ms.setKeyboardUsers(this.RetrieveUsersByMinistryId(lst, 3));
        ms.setFillerUsers(this.RetrieveUsersByMinistryId(lst, 4));
        ms.setDrumUsers(this.RetrieveUsersByMinistryId(lst, 5));
        ms.setBassUsers(this.RetrieveUsersByMinistryId(lst, 6));
        ms.setGuitarUsers(this.RetrieveUsersByMinistryId(lst, 7));
        ms.setMultimediaUsers(this.RetrieveUsersByMinistryId(lst, 8));
        ms.setSoundSystemUsers(this.RetrieveUsersByMinistryId(lst, 9));

        List<List<user>> singers = distinctDates.stream().map(date -> GetRoleForThisWeek( lst, date, 2)).collect(Collectors.toList());
        int max = singers.stream().mapToInt(List::size).filter(q -> q >= 0).max().orElse(0);
        List<List<user>> processed = new ArrayList<>();
        for(int i = 0; i<max; i++) {
            int finalI = i;
            List<user> bar = singers.stream().map(a -> a.get(finalI)).collect(Collectors.toList());
            processed.add(bar);
        }

        ms.setSingers(processed);
        return ms;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(dt);
    }

    private List<user> RetrieveUsersByMinistryId(List<schedule> lst, int ministryId)
    {
        return lst.stream()
                  .filter(x -> x.getMinistryId() == ministryId)
                  .map(x -> getUser(x.getUserId()))
                  .collect(Collectors.toList());
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
