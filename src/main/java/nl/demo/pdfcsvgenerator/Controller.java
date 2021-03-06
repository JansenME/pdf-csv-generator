package nl.demo.pdfcsvgenerator;

import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import nl.demo.pdfcsvgenerator.service.CSVService;
import nl.demo.pdfcsvgenerator.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController
public class Controller {
    private static final String FILENAME_CSV = "output.csv";

    private final CSVService csvService;
    private final PDFService pdfService;

    @Autowired
    public Controller(CSVService csvService, PDFService pdfService) {
        this.csvService = csvService;
        this.pdfService = pdfService;
    }

    @GetMapping(value = "/getcsv/{amount}", produces = "application/csv")
    public HttpEntity<byte[]> getCSV(@PathVariable String amount) {
        byte[] document = csvService.createByteArray(amount);

        HttpHeaders headers = setHeaders(new MediaType("application", "csv"), document);

        return new HttpEntity<>(document, headers);
    }

    @GetMapping(value = "/getpdf/{amount}", produces = "application/pdf")
    public HttpEntity<byte[]> getPDF(@PathVariable String amount) throws IOException, DocumentException {
        File file = pdfService.createFile(Integer.valueOf(amount));

        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders headers = setHeaders(file, new MediaType("application", "pdf"), document);

        Files.deleteIfExists(file.toPath());

        return new HttpEntity<>(document, headers);
    }

    private HttpHeaders setHeaders(final MediaType mediaType, final byte[] document) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(mediaType);
        headers.set("Content-Disposition", "attachment; filename=" + FILENAME_CSV);
        headers.setContentLength(document.length);

        return headers;
    }

    private HttpHeaders setHeaders(final File file, final MediaType mediaType, final byte[] document) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(mediaType);
        headers.set("Content-Disposition", "attachment; filename=" + file.getName());
        headers.setContentLength(document.length);

        return headers;
    }

}
