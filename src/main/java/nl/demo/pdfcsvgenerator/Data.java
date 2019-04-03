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

        String[] header = new String[]{     "IBAN/BBAN",          "Munt", "BIC",         "Volgnr", "Datum",      "Rentedatum", "Bedrag", "Saldo na trn", "Tegenrekening IBAN/BBAN", "Naam tegenpartij", "Naam uiteindelijke partij", "Naam initierende partij", "BIC tegenpartij", "Code", "BatchID", "Transactiereferentie", "Machtigingskenmerk", "Incassant ID", "Betalingskenmerk", "Omschrijving -1",  "Omschrijving -2", "Omschrijving -3", "Reden retour", "Oorspr bedrag", "Oorspr Munt", "Koers", "Rekeninghouder(s)", "Intern Adm Gebruik", "Reden retour omschrijving"};
        out.add(header);

        String[] addValue = new String[]{   "NL02RABO0123456789", "EUR",  "RABONL2UXXX", "",       "2018-10-21", "2018-10-21", "1,10",   "6.109,48",     "NL03RABO0123456789",      "Minions ST R",     "",                          "",                        "",                "ei",   "",        "",                     "",                   "",             "",                 "Description IP 1", "",                "",                "AM04",         "",              "",            "",      "N.Q. PASTIN A.R.",  "",                   "Administratieve reden"};

        IntStream.range(0, amount)
                .parallel()
                .forEach(value -> out.add(addValue));

        return out;
    }
}
