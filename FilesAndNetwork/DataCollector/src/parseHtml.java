import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class parseHtml{

    static String htmlMetro = htmlToCode("DataCollector/files/metro.html");
    static List<line> Lines = new ArrayList<>();
    static List<station> Stations = new ArrayList<>();

    public static void parseHtmlCode() {
        List<File> listFiles = new ArrayList<>();
        Document doc = Jsoup.parse(htmlMetro);

        Elements lines = doc.select("span.t-metrostation-list-header");
        List<String> nameOfLines = lines.eachText();
        List<String> numbOfLines = lines.eachAttr("data-line"); // номера линий
        nameOfLines.forEach(e -> Lines.add(new line(e, numbOfLines.get(nameOfLines.indexOf(e))))); // добавление линий и номеров линий в массив линий

        Elements stations = doc.select("p.single-station");
        List<String> nameOfStations = stations.select("span.name").eachText(); // названия станций
        List<String> num = new ArrayList<>();
        List<Boolean> hasConnection = new ArrayList<>();
        stations.forEach(element -> num.add(element.parent().attr("data-line")));
        stations.forEach(element -> hasConnection.add(element.childNodeSize() > 2 && element.children().hasAttr("title")));
        nameOfStations.forEach(e -> Stations.add(new station(e))); // добавление станций
        Stations.forEach(e -> e.setStationOnLine(num.get(Stations.indexOf(e)))); // добавление каждой станции номера линии
        Stations.forEach(e -> e.setHasConnection(hasConnection.get(Stations.indexOf(e)))); // есть ли переход со станции
        //Stations.forEach(e -> System.out.println(e.toString()));
        //Lines.forEach(e -> System.out.println(e.toString()));
    }

    public static String htmlToCode(String path){
        StringBuilder htmlCode = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(l -> htmlCode.append(l +"\n"));

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return htmlCode.toString();
    }





}

