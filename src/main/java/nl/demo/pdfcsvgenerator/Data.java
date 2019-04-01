package nl.demo.pdfcsvgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Data {
    public static List<String[]> getData(int amount) {
        return createData(amount);
    }

    private static List<String[]> createData(int amount) {
        List<String[]> out = Collections.synchronizedList(new ArrayList<>());

        String[] addValue = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};

        IntStream.range(0, amount)
                .parallel()
                .forEach(value -> out.add(addValue));

        return out;
    }
}
