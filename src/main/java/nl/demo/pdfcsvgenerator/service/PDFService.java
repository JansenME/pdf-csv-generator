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
import java.util.List;

@Service
public class PDFService {
    private static final String FILE_NAME = "./output.pdf";

    private final Document document =  new Document();

    public File createFile() throws FileNotFoundException, DocumentException {
        File file = new File(FILE_NAME);

        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();

        PdfPTable table = new PdfPTable(29);

        List<String[]> data = Data.getData(20000);

        data.forEach(value -> addData(value, table));

        document.add(table);

        document.close();

        return file;
    }

    private void addData(String[] value, PdfPTable table) {
        table.addCell(value[0]);
        table.addCell(value[1]);
        table.addCell(value[2]);
        table.addCell(value[3]);
        table.addCell(value[4]);
        table.addCell(value[5]);
        table.addCell(value[6]);
        table.addCell(value[7]);
        table.addCell(value[8]);
        table.addCell(value[9]);
        table.addCell(value[10]);
        table.addCell(value[11]);
        table.addCell(value[12]);
        table.addCell(value[13]);
        table.addCell(value[14]);
        table.addCell(value[15]);
        table.addCell(value[16]);
        table.addCell(value[17]);
        table.addCell(value[18]);
        table.addCell(value[19]);
        table.addCell(value[20]);
        table.addCell(value[21]);
        table.addCell(value[22]);
        table.addCell(value[23]);
        table.addCell(value[24]);
        table.addCell(value[25]);
        table.addCell(value[26]);
        table.addCell(value[27]);
        table.addCell(value[28]);
    }
}
