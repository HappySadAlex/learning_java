package parsingJsonCsv;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class parseJson {
    public String typeOfData = "";
    public String typeOfName = "";
    public List<Root> parseJson(String filePath) throws IOException, ParseException {

        List<Root> jsonFile = new ArrayList<Root>();
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        JSONArray stationsInfo = (JSONArray) parser.parse(reader);
        JSONObject stat = (JSONObject) stationsInfo.get(0);

        if(stat.containsKey("station_name")) {typeOfName ="station_name";} else typeOfName = "name";
        if(stat.containsKey("date")) {typeOfData ="date";};
        if(stat.containsKey("depth_meters")) {typeOfData = "depth_meters";};
        if(stat.containsKey("depth")) {typeOfData = "depth";};

        for(Object e : stationsInfo){
            JSONObject stationJson = (JSONObject) e;
            String name = (String) stationJson.get(typeOfName);
            String data = (String) stationJson.get(typeOfData).toString();
            Root station = new Root(name.toLowerCase(Locale.ROOT).replace("ё","е"), data);
            jsonFile.add(station);
        }

        return jsonFile;
    }

}
