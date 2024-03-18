package parsingJsonCsv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class parseCsv {

    public String typeOfData = "";
    public String typeOfName = "";
    public List<Root> parseCsv(String filePath){

        List<Root> csvFile = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)){
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);

            for (CSVRecord record : records){
                if (record.getRecordNumber() != 1) {
                    String name = record.get(0);
                    String data = record.get(1);
                    Root station = new Root(name.toLowerCase(Locale.ROOT).replace("ё", "е"), data);
                    csvFile.add(station);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return csvFile;
    }

}
