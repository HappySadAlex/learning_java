import org.json.simple.parser.ParseException;
import parsingJsonCsv.Root;
import parsingJsonCsv.parseCsv;
import parsingJsonCsv.parseJson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        // HTML to string
        // System.out.println(parseHtml.htmlToCode("D:\\Java\\java_basics\\FilesAndNetwork\\DataCollector\\files\\metro.html"));

        // Creating obj of classes for parsing Json and Csv
        parseJson parseJson = new parseJson();
        parseCsv parseCsv = new parseCsv();
        // Parse html doc
        parseHtml html = new parseHtml();
        parseHtml.parseHtmlCode();
        builderJson builder = new builderJson();

        //Finding files in directory
        List<File> listJsonCsv = new ArrayList<>();
        listJsonCsv = findFiles.parseFiles(new File("DataCollector/files/data"));
        //System.out.println(listJsonCsv);

        List<List<Root>> listOfParsedFiles = new ArrayList<>(); // List for data from json and csv files
        //Parsing json and csv files and getting data from them into one List
        for(File file : listJsonCsv){
            if(file.getName().endsWith(".json")){
                listOfParsedFiles.add(parseJson.parseJson(file.getPath()));
            }
            if(file.getName().endsWith(".csv")){
                listOfParsedFiles.add(parseCsv.parseCsv(file.getPath()));
            }
        }
        //Sorting all data from json and csv files
        List<station> allStationsInfo = station.sortStationData(listOfParsedFiles, parseHtml.Stations, parseHtml.Lines);

        //Creating map.json and stations.json by using builderJson class
        builderJson.buildMapJson(parseHtml.Stations, parseHtml.Lines); // map.json
        builderJson.buildStationsJson(allStationsInfo); // stations.json
    }
}
