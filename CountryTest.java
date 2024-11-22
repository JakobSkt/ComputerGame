import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * This class tests the Country class and its methods
 * 
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */
public class CountryTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Sets up the test fixture that is used on every test case
    */
    @BeforeEach
    public void setUp()
    {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");

        // Create game objects
        game = new Game(0);
        country1.setGame(game);
        country2.setGame(game);

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);

        country2.addCity(cityE);
        country2.addCity(cityF);
        country2.addCity(cityG);

        // Create roads
        country1.addRoads(cityA, cityB, 4);
        country1.addRoads(cityA, cityC, 3);
        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);
    }
    
    @Test
    public void constructor() {
        // Check that the name field is initialized correct
        assertEquals("Country 1", country1.getName());
    }
    
    @Test
    public void getCities() {
        // Check that the correct amount of cities are in the network
        assertEquals(4, country1.getCities().size());
    }
    
    @Test
    public void getCity() {
        // Check that the correct city is returned by the string
        assertEquals(cityA, country1.getCity("City A"));
        assertEquals(cityB, country1.getCity("City B"));
        assertEquals(cityC, country1.getCity("City C"));
        assertEquals(cityD, country1.getCity("City D"));
    }
    
    @Test
    public void getCityNotInCountry() {
        // Check that null is returned when the city is not in the country
        assertNull(country1.getCity("City E"));
    }
    
    @Test
    public void getRoads() {
        // Check that the correct amount of roads are found in the cityA's road set
        Set<Road> roads = country1.getRoads(cityA);
        assertEquals(3, roads.size());
    }
    
    @Test
    public void getRoadsCityNotInCountry() {
        // Checks that an empty set is returned if city is not in country
        assertEquals(new TreeSet<>(), country1.getRoads(cityF));
    }
    
    @Test
    public void reset() {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        // Saving the value of city E
        int valueE = cityE.getValue();
        
        // Check that City A gets reset but not city E
        country1.reset(); country1.reset(); country1.reset();
        assertEquals(cityA.getInitialValue(), cityA.getValue());
        assertEquals(valueE, cityE.getValue());
    }
    
    @Test
    public void bonus() {
        for(int seed = 0; seed < 100; seed++) {
            game.getRandom().setSeed(seed);
            int sum = 0;
            int sum1 = 0; // Variable for testing bonus with value 1
            int sum0 = 0; // Variable for testing bonus with value 0
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 100000; i++) {
                // Initialize bonus and add to sum for normal use
                int bonus = country1.bonus(80);
                sum += bonus;
                
                // Initialize bonus and add to sum for value 1
                int bonus1 = country1.bonus(1);
                sum1 += bonus1;
                
                // Initialize bonus and add to sum for value 0
                int bonus0 = country1.bonus(0);
                sum0 += bonus0;
                
                bonuses.add(bonus);
                assertTrue(bonus >= 0 && bonus <= 80);
            }
            // Check that the average of bonus(1) is correct
            int average1 = 100000/2;
            assertTrue(average1 * 0.99 <= sum1 && sum1 <= average1 * 1.01);
            
            // Check that average is 0, since the bonus(0) returns 0
            int average0 = 0;
            assertTrue(average0 * 0.99 <= sum0 && sum0 <= average0 * 1.01);
            
            // Check that average value is within 1% of 40 
            int average = 100000 * (80/2);
            assertTrue(average * 0.99 <= sum && sum <= average * 1.01);
            
            // Check that all the possible values are in the set
            assertEquals(80 + 1, bonuses.size());
        }
    }
    
    @Test
    public void addCity() {
        // Check to find non-existing city
        assertNull(country1.getCity("City J"));
        // Check that size of network is 4
        assertEquals(4, country1.getCities().size());

        // Add new city to network and check again
        City cityJ = new City("City J", 200, country1);
        country1.addCity(cityJ);
        assertEquals(cityJ, country1.getCity("City J"));
        // Check that size of network is changing -> now 5
        assertEquals(5, country1.getCities().size());
        
        // Check that city in country2 can be added
        country1.addCity(cityE);
        assertEquals(cityE, country1.getCity("City E"));
        // Check that size of network is changing -> now 6
        assertEquals(6, country1.getCities().size());
    }
    
    @Test
    public void addRoads() {
        // Saving number of roads for comparison
        int noOfRoadsA = country1.getRoads(cityA).size();
        int noOfRoadsD = country1.getRoads(cityD).size();
        country1.addRoads(cityA, cityD, 10);
        // Both cities in same country -> road is created both ways
        // Check that noOfRoads in cityA's and cityD's set has changed by one
        assertEquals(noOfRoadsA + 1, country1.getRoads(cityA).size());
        assertEquals(noOfRoadsD + 1, country1.getRoads(cityD).size());
        
        // Trying same cities with new road length 
        country1.addRoads(cityA, cityD, 12);
        // Check that noOfRoads in cityA's and cityD's set has changed by two
        assertEquals(noOfRoadsA + 2, country1.getRoads(cityA).size());
        assertEquals(noOfRoadsD + 2, country1.getRoads(cityD).size());
    }
    
    @Test
    public void addRoadsToFromSameCity() {
        // Check with same to and from city
        // Saving number of roads for comparison
        int noOfRoads = country1.getRoads(cityA).size();
        country1.addRoads(cityA, cityA, 10);
        // Check that noOfRoads in cityA's set has not changed
        assertEquals(noOfRoads, country1.getRoads(cityA).size());
    }
    
    @Test
    public void addRoadsDistanceZero() {
        // Check with distance 0
        // Saving number of roads for comparison
        int noOfRoads = country1.getRoads(cityA).size();
        country1.addRoads(cityD, cityA, 0);
        // Check that noOfRoads in cityA's set has not changed
        assertEquals(noOfRoads, country1.getRoads(cityA).size());
    }
    
    @Test
    public void addRoads4() {
        // Check with negative distance
        // Saving number of roads for comparison
        int noOfRoads = country1.getRoads(cityA).size();
        country1.addRoads(cityC, cityA, -20);
        // Check that noOfRoads in cityA's set has not changed
        assertEquals(noOfRoads, country1.getRoads(cityA).size());
    }
    
    @Test
    public void addRoadsCitiesDifferentCountries() {
        // Check with cities in different countries
        // Saving number of roads for comparison
        int noOfRoadsA = country1.getRoads(cityA).size();
        int noOfRoadsE = country2.getRoads(cityE).size();
        country1.addRoads(cityA, cityE, 10);
        // Both cities not in same country -> road is created one way
        // Check that noOfRoads in cityA's set has changed by one
        assertEquals(noOfRoadsA + 1, country1.getRoads(cityA).size());
        // Check that noOfRoads in cityE's set has not changed
        assertEquals(noOfRoadsE, country2.getRoads(cityE).size());
        
        // Other way around
        int noOfRoadsB = country1.getRoads(cityB).size();
        int noOfRoadsF = country2.getRoads(cityF).size();
        country2.addRoads(cityF, cityB, 10);
        // Both cities not in same country -> road is created one way
        // Check that noOfRoads in cityF's set has changed by one
        assertEquals(noOfRoadsF + 1, country2.getRoads(cityF).size());
        // Check that noOfRoads in cityB's set has not changed
        assertEquals(noOfRoadsB, country1.getRoads(cityB).size());
    }
    
    @Test
    public void addRoadsNoCitiesInCountry() {
        // Check with no cities in country
        // Saving number of roads for comparison
        int noOfRoadsF = country2.getRoads(cityF).size();
        int noOfRoadsE = country2.getRoads(cityE).size();
        country1.addRoads(cityF, cityE, 10);
        // No cities in country -> no roads created
        // Check that noOfRoads in both cities sets has not changed
        assertEquals(noOfRoadsF, country2.getRoads(cityF).size());
        assertEquals(noOfRoadsE, country2.getRoads(cityE).size());
    }
    
    
    @Test
    public void position() {
        assertEquals(new Position(cityA, cityA, 0), country1.position(cityA));
        assertEquals(new Position(cityB, cityB, 0), country1.position(cityB));
        // Check that position object has the same to and from city
        assertNotEquals(new Position(cityC, cityB, 0), country1.position(cityC));
        // Check that the position object has a distance of 0
        assertNotEquals(new Position(cityC, cityB, 4), country1.position(cityC));
    }
    
    @Test
    public void readyToTravel() {
        // Check that position is correct with
        // * different cities
        // * from city is in country network
        // * road to destination is found
        assertEquals(new Position(cityA, cityB, 4), country1.readyToTravel(cityA, cityB));
    }
    
    @Test
    public void readyToTravelWrongObject() {
        // Road is found, but wrong object is checked
        // Return position object of from city
        assertNotEquals(new Position(cityF, cityB, 0), country1.readyToTravel(cityF, cityE));
    }
    
    @Test
    public void readyToTravelSameFromTo() {
        // If to and from cities are equal
        // return position object of from city
        assertEquals(country1.position(cityA), country1.readyToTravel(cityA, cityA));
    }
    
    @Test
    public void readyToTravelSameWrongDistance() {
        // Check that distance is returned correctly
        assertNotEquals(new Position(cityA, cityB, 2), country1.readyToTravel(cityA, cityB));
    }

    @Test
    public void readyToTravelRoadNotFound() {
        // If road is not found to destination
        // Return position object of from city
        assertEquals(country1.position(cityA), country1.readyToTravel(cityA, cityE));
    }
    
    @Test
    public void testToString() {
        assertEquals("Country 1", country1.toString());
        assertEquals("Country 2", country2.toString());
    }
}
