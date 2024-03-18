import core.Line;
import core.Station;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    StationIndex map;
    RouteCalculator calculator;
    List<Station> route;
    List<Station> shortRoute;

    @Override
    protected void setUp() throws Exception {

        map = new StationIndex();
        map.addLine(new Line(1, "First"));
        map.addLine(new Line(2, "Second"));
        map.addLine(new Line(3, "Third"));
        map.addStation(new Station("1s", map.getLine(1)));
        map.addStation(new Station("2s", map.getLine(1)));
        map.addStation(new Station("3s", map.getLine(1)));
        map.addStation(new Station("3s2", map.getLine(2)));
        map.addStation(new Station("4s", map.getLine(2)));
        map.addStation(new Station("5s", map.getLine(2)));
        map.addStation(new Station("5s3", map.getLine(3)));
        map.addStation(new Station("6s", map.getLine(3)));
        map.getLine(1).addStation(map.getStation("1s"));
        map.getLine(1).addStation(map.getStation("2s"));
        map.getLine(1).addStation(map.getStation("3s"));
        map.getLine(2).addStation(map.getStation("3s2"));
        map.getLine(2).addStation(map.getStation("4s"));
        map.getLine(2).addStation(map.getStation("5s"));
        map.getLine(3).addStation(map.getStation("5s3"));
        map.getLine(3).addStation(map.getStation("6s"));
        List<Station> connectionStations = new ArrayList<>();
        connectionStations.add(map.getStation("3s"));
        connectionStations.add(map.getStation("3s2"));
        connectionStations.add(map.getStation("5s"));
        connectionStations.add(map.getStation("5s3"));
        map.addConnection(connectionStations);
        calculator = new RouteCalculator(map);
        route = new ArrayList<>();
        route.add(map.getStation("1s"));
        route.add(map.getStation("2s"));
        route.add(map.getStation("3s"));
        route.add(map.getStation("3s2"));
        route.add(map.getStation("4s"));
        route.add(map.getStation("5s"));
        route.add(map.getStation("5s3"));
        route.add(map.getStation("6s"));
    }

    public void testGetShortestRoute(){
        List<Station> actualList = calculator.getShortestRoute(map.getStation("1s"), map.getStation("4s"));
        List<Station> actual = actualList;
        List<Station> expected = new ArrayList<>();
        expected.add(map.getStation("1s"));
        expected.add(map.getStation("2s"));
        expected.add(map.getStation("3s"));
        expected.add(map.getStation("3s2"));
        expected.add(map.getStation("4s"));
        assertEquals(expected, actual);
    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 14.5;
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine(){
        List<Station> expected = new ArrayList<>();
        expected.add(map.getStation("1s"));
        expected.add(map.getStation("2s"));
        expected.add(map.getStation("3s"));
        List<Station> actual = calculator.getShortestRoute(map.getStation("1s"), map.getStation("3s"));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection(){
        List<Station> expected = new ArrayList<>();
        expected.add(map.getStation("2s"));
        expected.add(map.getStation("3s"));
        expected.add(map.getStation("3s2"));
        expected.add(map.getStation("4s"));
        List<Station> actual = calculator.getShortestRoute(map.getStation("2s"), map.getStation("4s"));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnections(){
        List<Station> expected = new ArrayList<>();
        expected.add(map.getStation("1s"));
        expected.add(map.getStation("2s"));
        expected.add(map.getStation("3s"));
        expected.add(map.getStation("5s3"));
        expected.add(map.getStation("6s"));
        List<Station> actual = calculator.getShortestRoute(map.getStation("1s"), map.getStation("6s"));
        assertEquals(expected, actual);
    }

    public void testIsConnected(){
        List<Station> actual = calculator.getShortestRoute(map.getStation("3s"), map.getStation("3s2"));
        List<Station> expected = new ArrayList<>();
        expected.add(map.getStation("3s"));
        expected.add(map.getStation("3s2"));
        assertEquals(expected, actual);
    }



}
