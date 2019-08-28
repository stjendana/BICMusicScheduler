package BIC.Vancouver.music_scheduler.util;

import BIC.Vancouver.music_scheduler.model.schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Component
public class PdfGeneratorUtil {

    @Autowired
    private TemplateEngine templateEngine;
    private String templateName = "pdfHtmlTemplate";

    public String CreatePdf(Iterable<schedule> schedules) throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");
        Context ctx = new Context();
        ctx.setVariable("schedules", schedules);

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
