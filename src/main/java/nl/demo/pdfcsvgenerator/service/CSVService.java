package nl.demo.pdfcsvgenerator.service;

import com.opencsv.CSVWriter;
import nl.demo.pdfcsvgenerator.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CSVService {
    private static final String FILE_NAME = "./output.csv";

    public File createFile() {
        File file = new File(FILE_NAME);

        try {
            FileWriter outputFile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputFile);

            writer.writeAll(Data.getData(100000));

            writer.close();
        } catch (IOException e) {
            //Do nothing
        }

        return file;
    }
}
