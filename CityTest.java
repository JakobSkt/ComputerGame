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
public class CityTest
{
    private Game game;
    private Country country1;
    private City cityA, cityB;

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
    }
    
    @Test
    public void constructor() {
        // Check that fields are initialized correctly 
        assertEquals("City A", cityA.getName());
        assertEquals(80, cityA.getValue());
        assertEquals(80, cityA.getInitialValue());
        assertEquals(country1, cityA.getCountry());
        
        assertEquals("City B", cityB.getName());
        assertEquals(60, cityB.getValue());
        assertEquals(60, cityB.getInitialValue());
        assertEquals(country1, cityB.getCountry());
    }
    
    @Test
    public void changeValue() {
        cityA.changeValue(20); cityA.changeValue(20); cityA.changeValue(20);
        assertEquals(140, cityA.getValue());
        
        cityB.changeValue(20); cityB.changeValue(20); cityB.changeValue(20);
        assertEquals(120, cityB.getValue());
    }
    
    @Test 
    public void changeValueNegativeValue() {
        // Testing with negative numbers
        cityA.reset();
        cityA.changeValue(-20); cityA.changeValue(-20); cityA.changeValue(-20);
        assertEquals(20, cityA.getValue());
    }
    
    @Test 
    public void changeValueNegativeCityValue() {
        // Testing changing to a negative city value
        cityB.reset();
        cityB.changeValue(-40); cityB.changeValue(-40); cityB.changeValue(-40);
        assertEquals(-60, cityB.getValue());
    }
    
    @Test 
    public void changeValueZeroValue() {
        // Testing changing to a negative city value
        cityB.reset();
        cityB.changeValue(0); cityB.changeValue(0); cityB.changeValue(0);
        assertEquals(60, cityB.getValue());
    }
    
    @Test 
    public void changeValueZeroCity() {
        // Testing changing to a negative city value
        cityB.reset();
        cityB.changeValue(-20); cityB.changeValue(-20); cityB.changeValue(-20);
        assertEquals(0, cityB.getValue());
    }
    
    @Test
    public void resetByArrive() {
        // Testing with arrive method
        cityA.arrive(); cityA.arrive();
        cityA.reset();
        assertEquals(cityA.getInitialValue(), cityA.getValue());
    }
    
    @Test
    public void resetByChangeValue() {
        // Testing with changeValue method and negative city value
        cityB.changeValue(-100);
        cityB.reset();
        assertEquals(cityB.getInitialValue(), cityB.getValue());
    }

    @Test
    public void arrive() {
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed); 
            int bonus = country1.bonus(80);
            game.getRandom().setSeed(seed); 
            // Check that with the same seed, the bonus is the same
            assertEquals(bonus, cityA.arrive());
            // Check that city value is decreasing
            assertEquals(cityA.getInitialValue()-bonus, cityA.getValue());
            cityA.reset();
        }
    }
    
    @Test
    public void arriveBonus0() {
        // Testing with a bonus of 0
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            // Setting city value to 0
            cityA.changeValue(-cityA.getValue());
            // Check that bonus is 0
            assertEquals(0, cityA.arrive());
            // Check that city value is not changing
            assertEquals(0, cityA.getValue());
            cityA.reset();
        }
    }
    
    @Test
    public void arriveBonusNegative() {
        
        // Testing with negative bonuses
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            // Setting city value to -60
            cityB.changeValue(-cityB.getValue()*2);
            // Check that bonus is 0
            assertEquals(0, cityB.arrive());
            // Check that city value is not changing
            assertEquals(-60, cityB.getValue());
            cityB.reset();
        }
    }
    
    @Test
    public void testToString() {
        assertEquals("City A (80)", cityA.toString());
        assertEquals("City B (60)", cityB.toString());
        
        // Check that string representation is updating with fields
        cityA.changeValue(40);
        cityB.changeValue(-40);
        assertEquals("City A (120)", cityA.toString());
        assertEquals("City B (20)", cityB.toString());
    }
}
