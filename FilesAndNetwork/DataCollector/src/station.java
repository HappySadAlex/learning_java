import com.google.gson.annotations.SerializedName;
import parsingJsonCsv.Root;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class station {
    @SerializedName("name")
    public String nameOfStation;
    @SerializedName("line")
    public String numbOfLine;
    @SerializedName("date")
    public String date;
    @SerializedName("depth")
    public float depth = 1000;
    public boolean hasConnection;

    public station(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }

    public station(String nameOfStation, String stationOnLine) {
        this.nameOfStation = nameOfStation;
        this.numbOfLine = stationOnLine;
    }

    public String getNameOfStation() {
        return nameOfStation;
    }

    public void setNameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }

    public String getStationOnLine() {
        return numbOfLine;
    }

    public void setStationOnLine(String stationOnLine) {
        this.numbOfLine = stationOnLine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        String station = new String();
        station = nameOfStation + " - " + numbOfLine + " - has connection: " + hasConnection + " - Date: " + date + " - depth: " + depth;
        return station;
    }

    public static List<station> sortStationData(List<List<Root>> stationData, List<station> stations, List<line> lines){

        List<station> sortedStations = new ArrayList<>();
        List<Root> allData = new ArrayList<>();
        stationData.forEach(e -> allData.addAll(e));
        allData.removeIf(data -> data.getData().equals("?"));
        for(Root r : allData){
            if(r.getName().contains("\u0451")) { //код буквы "ё" в UTF-8 - "\u0451", код буквы "е" - "\u0435"
                r.setName(r.getName().replace("\u0451", "\u0435"));
            }
        }
        //allData.forEach(System.out::println);

        LinkedHashMap<String, String> stationsDates = new LinkedHashMap<>(); // stations and dates
        LinkedHashMap<String, Float> stationsDepths = new LinkedHashMap<>(); // stations and depths(in Float format)

        for(line l : lines){
            for(station s : stations){
                if(l.numbOfLine.equals(s.numbOfLine)){
                    sortedStations.add(new station(s.nameOfStation, l.nameOfLine));
                }
            }
        }
        List<Root> allDepth = new ArrayList<>(); // List for data about depths
        // Parsing all data from json and csv files
        for(Root dataLine : allData) {
            if (dataLine.getData().matches("^([0-2][0-9]||3[0-1]).(0[0-9]||1[0-2]).([0-9][0-9])?[0-9][0-9]$")) {
                stationsDates.put(dataLine.getName(), dataLine.getData());
            }
            else allDepth.add(dataLine);
        }

        for(Root r : allDepth){
            stationsDepths.put(r.getName(), stringToFloat(r.getData()));
        }
        for(Root r : allDepth){
            for (Map.Entry<String, Float> entry : stationsDepths.entrySet()){
                if(entry.getKey().equals(r.getName())){
                    float oldDepth = entry.getValue();
                    float newDepth = stringToFloat(r.getData());
                    if(oldDepth > newDepth){
                        entry.setValue(newDepth);
                    }
                }
            }
        }

        // добавление в новый список станций информации о переходах и номера линий
        for (station s : sortedStations){
            for (station a : stations){
                if (s.nameOfStation.equals(a.nameOfStation)){
                    s.setHasConnection(a.hasConnection);
                }
            }
        }
        // добавление в новый список станций информации о датах открытия и о глубине
        // добавление инфы о датах из мапа с датами (String, String)
        for (station s : sortedStations) {
            String name = s.nameOfStation.toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
            for (Map.Entry<String, String> entry : stationsDates.entrySet()) {
                String keyName = entry.getKey().toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
                if(name.equals(keyName)){
                    s.setDate(entry.getValue());
                }
            }
        }
        // добавление инфы о глубинах из мапа с глубинами (String, Float)
        for(station s : sortedStations){
            String name = s.nameOfStation.toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
            for (Map.Entry<String, Float> entry : stationsDepths.entrySet()) {
                String keyName = entry.getKey().toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
                if(name.equals(keyName)){
                    s.setDepth(entry.getValue());
                }
            }
        }
        // dates for stations on different lines, but with one name
        LinkedHashMap<String, List<String>> twice = new LinkedHashMap<>();
        List<String> namesForTwice = new ArrayList<>();
        for(station s : sortedStations){
            String name = s.nameOfStation.toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
            for(station a : sortedStations){
                String nameA = a.nameOfStation.toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
                if (name.equals(nameA) && !s.numbOfLine.equals(a.numbOfLine) && !namesForTwice.contains(name)) {
                    namesForTwice.add(name);
                }
            }
        }
        for (String s : namesForTwice){
            List<String> datesForTwice = new ArrayList<>();
            for (Root r : allData) {
                if(r.getData().matches("^([0-2][0-9]||3[0-1]).(0[0-9]||1[0-2]).([0-9][0-9])?[0-9][0-9]$") && r.getName().equals(s)) {
                    if(datesForTwice.isEmpty()) {
                        datesForTwice.add(r.getData());
                    }
                    else if(!datesForTwice.contains(r.getData()))
                    {
                        datesForTwice.add(r.getData());
                    }
                }
            }
            if(datesForTwice.size() != 1) {
                twice.put(s, datesForTwice);
                //System.out.println("name of station: " + s + " - dates: " + datesForTwice + "\n");
            }
        }

        /*for (Map.Entry<String, List<String>> entry : twice.entrySet()){
            List<String> values = entry.getValue();
            String key = entry.getKey();
            String value1 = values.get(0);
            String value2 = values.get(1);
            k:for (station s : sortedStations){
                String name = s.nameOfStation.toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
                if(name.equals(key) && !(s.date.equals(value1))){
                    s.setDate(value1);
                    for (station n: sortedStations){
                        String nName = n.nameOfStation.toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
                        if(nName.equals(key) && !n.date.equals(value1)){
                            n.setDate(value2);
                            break k;
                        }
                    }
                }
            }
        }*/

        for (Map.Entry<String, List<String>> entry : twice.entrySet()) {
            List<String> values = entry.getValue();
            String key = entry.getKey();
            String value1 = values.get(0);
            String value2 = values.get(1);
            int i = 0;
            for (station s : sortedStations) {
                String name = s.nameOfStation.toLowerCase(Locale.ROOT).replace("\u0451", "\u0435");
                if(name.equals(key) && i == 0){
                    s.setDate(value1);
                    i++;
                    continue;
                }
                if(name.equals(key) && i == 1){
                    s.setDate(value2);
                    i=0;
                    break;
                }
            }
        }

        //System.out.println(twice);
        return sortedStations;
    }


    //Method to convert string data(depth) from Root to Float format
    public static float stringToFloat(String num){
        float n = 888;
        try {
            return Float.parseFloat(num);
        }catch (Exception e) {
            String res = num.strip().replace(num.charAt(0), '-').replace(',', '.');
            if (num.matches("^-?[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$")) {
                if (Character.isDigit(num.charAt(0))) {
                    n = Float.parseFloat(num.strip().replace(',', '.'));
                    return n;
                } else {
                    n = Float.parseFloat(res);
                }
            } else {
                return Float.parseFloat(res);
            }
        }
        return n;
    }

}


