import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import parsingJsonCsv.Root;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class builderJson {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void buildMapJson(List<station> stations, List<line> lines) throws IOException {
        File mapJson = new File("DataCollector/files/map.json");
        if(mapJson.exists()) System.out.println("File 'map.json' already exists!");
        else {
            boolean created = mapJson.createNewFile();
            System.out.println("File \"map.json\" created!");
        }

        LinkedHashMap<String, List<String>> linesAndStations = new LinkedHashMap<>(); //создание коллекции для map.json с линиями и станциями на этих линиях

        for(line l : lines){
            List<String> stationsOnLine = new ArrayList<>();
            linesAndStations.put(l.numbOfLine, stationsOnLine);
            for(station s :stations){
                if(l.numbOfLine.equals(s.numbOfLine)){
                    stationsOnLine.add(s.nameOfStation);
                }
            }
            linesAndStations.replace(l.numbOfLine, stationsOnLine);
        }

        String lAndS = GSON.toJson(linesAndStations); // массив станции на линиях метро
        String l = GSON.toJson(lines); // массив номеров линий и названия

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("stations", linesAndStations);
        result.put("lines", lines);
        String res = GSON.toJson(result);

        try {
            FileWriter writer = new FileWriter(mapJson);
            writer.write(res);
            writer.close();
        }catch (Exception e) {
            System.out.println("Error while writing file");
            e.printStackTrace();
        }
    }

    public static void buildStationsJson(List<station> stations) throws IOException {

        HashMap<String, List<station>> stationsToJson = new HashMap<>();
        stationsToJson.put("stations", stations);
        String result = GSON.toJson(stationsToJson).replaceAll("\"depth\": 1000.0,", "").replaceAll("(?m)^\\s*$[\r\n]+", "");

        File stationsJson = new File("DataCollector/files/stations.json");
        if(stationsJson.exists()) System.out.println("File 'stations.json' already exists!");
        else {
            boolean created = stationsJson.createNewFile();
            System.out.println("File \"station.json\" created!");
        }

        try {
            FileWriter writer = new FileWriter(stationsJson);
            writer.write(result);
            writer.close();
        }catch (Exception e) {
            System.out.println("Error while writing file");
            e.printStackTrace();
        }

    }

}
