import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the CapitalCity class and its methods
 * 
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */

public class CapitalCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityC;
    private CapitalCity cityB, cityD, cityE;

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
        country2 = new Country("Country 2");
        country1.setGame(game);
        country2.setGame(game);

        // Create cities in country 1
        cityA = new City("City A", 40, country1);
        cityB = new CapitalCity("City B", 0, country1);

        // Create cities in country 2
        cityC = new City("City C", 80, country2);
        cityD = new CapitalCity("City D", 60, country2);
        cityE = new CapitalCity("City E", -100, country2);
    }
    
    @Test
    public void constructor() {
        // Check that fields are initialized correctly 
        assertEquals("City B", cityB.getName());
        assertEquals(0, cityB.getValue());
        assertEquals(0, cityB.getInitialValue());
        assertEquals(country1, cityB.getCountry());
        
        assertEquals("City D", cityD.getName());
        assertEquals(60, cityD.getValue());
        assertEquals(60, cityD.getInitialValue());
        assertEquals(country2, cityD.getCountry());
        
        assertEquals("City E", cityE.getName());
        assertEquals(-100, cityE.getValue());
        assertEquals(-100, cityE.getInitialValue());
        assertEquals(country2, cityE.getCountry());
    }

    @Test
    public void arriveFromAnotherCountry() {
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed);
            Player player = new GUIPlayer(new Position(cityA, cityD, 0), 250);
            
            int bonus = country2.bonus(60);
            // Calculate customs and comsumption
            int customs = 250 / 5;
            int consumption = game.getRandom().nextInt(250 + bonus - customs + 1);     
            
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus is the same minus the customs paid and comsumption
            assertEquals(bonus - customs - consumption, cityD.arrive(player));
            // Check that city value is changing based on the bonus, customs paid and comsumption
            assertEquals(60 + customs + consumption - bonus, cityD.getValue());
            cityD.reset();
        }
    } 
    
    @Test
    public void arriveFromSameCountry() {
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed);
            Player player = new GUIPlayer(new Position(cityC, cityD, 0), 250);
            
            int bonus = country2.bonus(60);
            // Calculate comsumption
            int consumption = game.getRandom().nextInt(250 + bonus + 1);     
            
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus is the same minus the consumption
            assertEquals(bonus - consumption, cityD.arrive(player));
            // Check that city value is changing based on the bonus and consumption
            assertEquals(60 + consumption - bonus, cityD.getValue());
            cityD.reset();
        }
    }
    
    @Test
    public void arriveBonus0() {
        // Testing with a bonus of 0 and same country
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed);
            Player player = new GUIPlayer(new Position(cityA, cityB, 0), 250);
            
            int bonus = country1.bonus(0);
            // Calculate consumption
            int consumption = game.getRandom().nextInt(250 + bonus + 1);  
            
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus is the same minus the consumption
            assertEquals(bonus - consumption, cityB.arrive(player));
            // Check that city value is changing based on the bonus and consumption
            assertEquals(0 + consumption - bonus, cityB.getValue());
            cityB.reset();
        }
    }
    
    @Test
    public void arriveBonusNegative() {
        // Testing with negative bonuses
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed);
            Player player = new GUIPlayer(new Position(cityA, cityE, 0), 250);
            
            int bonus = country2.bonus(-100);
            // Calculate customs and consumption
            int customs = 250 / 5;
            int consumption = game.getRandom().nextInt(250 + bonus - customs + 1);
            
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus is the same minus the customs paid and the consumption
            assertEquals(bonus - consumption - customs, cityE.arrive(player));
            // Check that city value is changing based on the bonus, customs paid and the consumption
            assertEquals(-100 + customs + consumption - bonus, cityE.getValue());
            cityE.reset();
        }
    }
}
