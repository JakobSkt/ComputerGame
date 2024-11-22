import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the BorderCity class and its methods
 * 
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */

public class BorderCityTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityC;
    private BorderCity cityB, cityD, cityE;

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
        cityB = new BorderCity("City B", 0, country1);

        // Create cities in country 2
        cityC = new City("City C", 80, country2);
        cityD = new BorderCity("City D", 60, country2);
        cityE = new BorderCity("City E", -100, country2);
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
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityA, cityD, 0), 250);
            game.getRandom().setSeed(seed);
            int bonus = country2.bonus(60);
            // Calculate customs
            int customs = 250 / 5;
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus is the same minus the customs paid
            assertEquals(bonus - customs, cityD.arrive(player));
            // Check that city value is changing based on the bonus and customs paid
            assertEquals(cityD.getInitialValue()+customs-bonus, cityD.getValue());
            cityD.reset();
        }
    } 
    
    @Test
    public void arriveFromSameCountry() {
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityC, cityD, 0), 250);
            game.getRandom().setSeed(seed);
            int bonus = country2.bonus(60);
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus
            assertEquals(bonus, cityD.arrive(player));
            // Check that city value is changing based on the bonus
            assertEquals(cityD.getInitialValue()-bonus, cityD.getValue());
            cityD.reset();
        }
    }
    
    @Test
    public void arriveBonus0() {
        // Testing with a bonus of 0
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityC, cityB, 0), 250);
            game.getRandom().setSeed(seed);
            int bonus = country1.bonus(0);
            // Calculate customs
            int customs = 250 / 5;
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus is the same minus the customs paid
            assertEquals(bonus - customs, cityB.arrive(player));
            // Check that city value is changing based on the bonus and customs paid
            assertEquals(cityB.getInitialValue()+customs-bonus, cityB.getValue());
            cityB.reset();
        }
    }
    
    @Test
    public void arriveBonusNegative() {
        // Testing with negative bonuses
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityA, cityE, 0), 250);
            game.getRandom().setSeed(seed);
            int bonus = country2.bonus(-100);
            // Calculate customs
            int customs = 250 / 5;
            game.getRandom().setSeed(seed);
            // Check that with the same seed, the bonus is the same minus the customs paid
            assertEquals(bonus - customs, cityE.arrive(player));
            // Check that city value is changing based on the bonus and customs paid
            assertEquals(cityE.getInitialValue()+customs-bonus, cityE.getValue());
            cityE.reset();
        }
    }
}
