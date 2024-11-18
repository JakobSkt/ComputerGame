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
public class PositionTest
{
    private Game game;
    private Country country1;
    private City cityA, cityB, cityC, cityD;
    private Position pos1, pos2;

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

        pos1 = new Position(cityA, cityB, 4);
        pos2 = new Position(cityC, cityD, 2);
    }
    
    @Test
    public void constructor() {
        // Check that pos1 starts i cityA, ends i cityB, has the remaining distance 4 og the total distance 4
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(4, pos1.getDistance());
        assertEquals(4, pos1.getTotal());
        
         // Check that pos2 starts i cityC, ends i cityD, hs the remaining  distance 2 og the total distance 2
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
        assertEquals(2, pos2.getTotal());
    }

    @Test
    public void move() {
        // Run the move test for pos1 four times
        for(int i = 4; i > 0; i--) {
            assertEquals(i, pos1.getDistance());
            assertEquals(true, pos1.move());
        }

        // Check that it can't move further
        assertEquals(false, pos1.move());
        assertEquals(0, pos1.getDistance());

        // Run the move test for pos2 two times
        for(int i = 2; i > 0; i--) {
            assertEquals(i, pos2.getDistance());
            assertEquals(true, pos2.move());
        }

        // Check that it can't move further
        assertEquals(false, pos2.move());
        assertEquals(0, pos2.getDistance());
    }
    
    @Test
    public void turnAround() {
        pos1.move();
        pos1.turnAround();
        // Check that the cities and distance left are flipped
        assertEquals(cityB, pos1.getFrom());
        assertEquals(cityA, pos1.getTo());
        assertEquals(1, pos1.getDistance());
        assertEquals(4, pos1.getTotal());

        pos1.turnAround();
        // Check that the cities and distance left are flipped
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(3, pos1.getDistance());
        assertEquals(4, pos1.getTotal());
    }
    
    @Test
    public void turnAroundNoMoving() {
        // Check that turnAround() works without moving first
        pos2.turnAround();
        assertEquals(cityD, pos2.getFrom());
        assertEquals(cityC, pos2.getTo());
        assertEquals(0, pos2.getDistance());
        assertEquals(2, pos2.getTotal());

        // Check that cities and distance left is flipped
        pos2.turnAround();
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
        assertEquals(2, pos2.getTotal());
    }

    @Test
    public void hasArrived() {
        // Check that pos1 has not arrived in the 4 moves closer to the city
        for(int i = 4; i > 0; i--) {
            assertEquals(false, pos1.hasArrived());
            pos1.move();
        }
        // Check that pos1 has arrived after 4 moves
        assertEquals(true, pos1.hasArrived());

        // Check that pos2 has not arrived in the 2 moves closer to the city
        for(int i = 2; i > 0; i--) {
            assertEquals(false, pos2.hasArrived());
            pos2.move();
        }
        // Check that pos2 has arrived after 2 moves
        assertEquals(true, pos2.hasArrived());
    }
    
    @Test
    public void testToString() {
        // Check that the pos1-string is updated correctly on each move
        for(int i = 4; i > 0; i--) {
            assertEquals("City A (80) -> City B (60) : " + i + "/4", pos1.toString());
            pos1.move();
        }

        // Check that the string representation is updating when turning around
        pos1.turnAround();
        pos1.move();
        assertEquals("City B (60) -> City A (80) : 3/4", pos1.toString());

        pos1.turnAround();
        assertEquals("City A (80) -> City B (60) : 1/4", pos1.toString());

        // Check that the pos2-string is updated correctly on each move
        for(int i = 2; i > 0; i--) {
            assertEquals("City C (40) -> City D (100) : " + i + "/2", pos2.toString());
            pos2.move();
        }

        // Check that the string representation is updating when turning around
        pos2.turnAround();
        assertEquals("City D (100) -> City C (40) : 2/2", pos2.toString());
        
        pos2.move();

        pos2.turnAround();
        assertEquals("City C (40) -> City D (100) : 1/2", pos2.toString());
    }
}
