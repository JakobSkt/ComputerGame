import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * This class tests the Road class and its methods
 * 
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */
public class RoadTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD;
    private Road road1, road2, road3;

    /**
     * Sets up the test fixture that is used on every test case
    */
    @BeforeEach
    public void setUp()
    {
        // Create game object
        game = new Game(0);
        
        // Create countries
        country1 = new Country("Country 1");
        country1.setGame(game);
        
        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);

        road1 = new Road(cityA, cityB, 4);
        road2 = new Road(cityC, cityD, 2);
        road3 = new Road(cityA, cityC, -3);
    }
    
    @Test
    public void constructor() {
        // Check that road1 starts in cityA, ends in cityB og has a distance of 4
        assertEquals(cityA, road1.getFrom());
        assertEquals(cityB, road1.getTo());
        assertEquals(4, road1.getLength());

        // Check that road2 starts in cityC, ends in cityD og has a distance of 2
        assertEquals(cityC, road2.getFrom());
        assertEquals(cityD, road2.getTo());
        assertEquals(2, road2.getLength());

        assertEquals(cityA, road3.getFrom());
        assertEquals(cityC, road3.getTo());
        assertEquals(-3, road3.getLength());
    }

    @Test
    public void testToString() {
        assertEquals("City A (80) -> City B (60) : 4", road1.toString());
        assertEquals("City C (40) -> City D (100) : 2", road2.toString());
        assertEquals("City A (80) -> City C (40) : -3", road3.toString());
    }
}
