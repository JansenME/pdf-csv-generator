package nl.demo.pdfcsvgenerator.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import nl.demo.pdfcsvgenerator.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class PDFService {
    private static final String FILE_NAME = "./output.pdf";

    public File createFile(int amount) throws FileNotFoundException, DocumentException {
        File file = new File(FILE_NAME);
        final Document document =  new Document();

        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();

        PdfPTable table = new PdfPTable(29);

        List<String[]> data = Data.getData(amount);

        data.forEach(value -> addData(value, table));

        document.add(table);

        document.close();

        return file;
    }

    private void addData(String[] value, PdfPTable table) {
        Arrays.stream(value)
                .forEach(table::addCell);
    }
}
