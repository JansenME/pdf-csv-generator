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
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController
public class Controller {
    private final CSVService csvService;
    private final PDFService pdfService;

    @Autowired
    public Controller(CSVService csvService, PDFService pdfService) {
        this.csvService = csvService;
        this.pdfService = pdfService;
    }

    @GetMapping(value = "/getcsv", produces = "application/csv")
    public HttpEntity<byte[]> getCSV() throws IOException {
        File file = csvService.createFile();

        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders headers = setHeaders(file, new MediaType("application", "csv"), document);

        Files.deleteIfExists(file.toPath());

        return new HttpEntity<>(document, headers);
    }

    @GetMapping(value = "/getpdf", produces = "application/pdf")
    public HttpEntity<byte[]> getPDF() throws IOException, DocumentException {
        File file = pdfService.createFile();

        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders headers = setHeaders(file, new MediaType("application", "pdf"), document);

        Files.deleteIfExists(file.toPath());

        return new HttpEntity<>(document, headers);
    }

    private HttpHeaders setHeaders(final File file, final MediaType mediaType, final byte[] document) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(mediaType);
        headers.set("Content-Disposition", "attachment; filename=" + file.getName());
        headers.setContentLength(document.length);

        return headers;
    }

}
