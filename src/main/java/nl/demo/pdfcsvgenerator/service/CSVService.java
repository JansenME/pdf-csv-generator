package nl.demo.pdfcsvgenerator.service;

import nl.demo.pdfcsvgenerator.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Service
public class CSVService {

    public byte[] createByteArray(String amount) {
        StringJoiner out = new StringJoiner("\n");

        List<String[]> allData = Data.getData(Integer.valueOf(amount));

        allData.forEach(oneLine -> {
            StringJoiner line = new StringJoiner(",");

            Arrays.stream(oneLine)
                    .forEach(line::add);

            out.add(line.toString());
        });

        return out.toString().getBytes();
    }
}
