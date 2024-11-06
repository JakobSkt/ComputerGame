import java.io.IOException;
import java.util.*;

public class Run {
    public static void main(String[] args) throws IOException {
/*
        Country denmark = new Country("Denmark");

        City city1 = new City("Aarhus", 50, denmark);
        City city2 = new City("Odense", 40, denmark);
        City city3 = new City("Aalborg", 30, denmark);

        denmark.addCity(city1);
        denmark.addCity(city2);
        denmark.addCity(city3);

        denmark.addRoads(city1, city2, 10);
        denmark.addRoads(city1, city3, 20);
        denmark.addRoads(city2, city3, 30);

        System.out.println(denmark.getCities());

        denmark.reset();

        System.out.println(denmark.getCities());


        System.out.println("Aarhus1 == Aarhus2? " + city1.equals(city2));
        System.out.println("Aarhus1 == Aalborg? " + city1.equals(city3));
        System.out.println("Aarhus2 == Aalborg? " + city2.equals(city3));

        System.out.println("************* Comparing *************");
        System.out.println(city1.compareTo(city2));
        System.out.println(city2.compareTo(city2));
        System.out.println(city3.compareTo(city2));

        System.out.println("************* Roads *************");
        ArrayList<Road> roads = new ArrayList<>();
        Road road1 = new Road(city1, city3, 30);
        Road road2 = new Road(city2, city1, 0);
        Road road3 = new Road(city1, city3, 20);
        Road road4 = new Road(city1, city3, 40);
        roads.add(road2);
        roads.add(road1);
        roads.add(road4);
        roads.add(road3);
        System.out.println(road1);
        System.out.println(road2);
        System.out.println(roads);
        System.out.println("************* Sorted list *************");
        Collections.sort(roads);
        System.out.println(roads);
        */

        TestServer.test("CG1-6");
        //GUI.createGameBoard();
    }
}
