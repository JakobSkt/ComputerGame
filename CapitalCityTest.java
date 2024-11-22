
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * The test class CapitalCityTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CapitalCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityC;
    private CapitalCity cityB, cityD, cityE;

    /**
     * Default constructor for test class CapitalCityTest
     */
    public CapitalCityTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        // Create countries
        game = new Game(0);

        country1 = new Country("Country 1");
        country2 = new Country("Country 2");
        country1.setGame(game);
        country2.setGame(game);

        // Create cities
        cityA = new City("City A", 40, country1);
        cityB = new CapitalCity("City B", 0, country1);
        cityC = new City("City C", 80, country2);
        cityD = new CapitalCity("City D", 60, country2);
        cityE = new CapitalCity("City E", -100, country2); 

    }

    @Test
    public void testConstructor(){
        assertEquals("City B", cityB.getName());
        assertEquals("City D", cityD.getName());
        assertEquals("City E", cityE.getName());
        
        assertEquals(0, cityB.getValue());
        assertEquals(60, cityD.getValue());
        assertEquals(-100, cityE.getValue());
        
        assertEquals(country1, cityB.getCountry());
        assertEquals(country2, cityD.getCountry());
        assertEquals(country2, cityE.getCountry());

    }

    @Test
    public void ArriveFromAnotherCountry(){
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed); // Reset seed
            Player player = new GUIPlayer(new Position(cityA, cityD, 0), 250);
            int bonus = country2.bonus(60);

            int toll = 250 / 5;

            int consumption = game.getRandom().nextInt(250 + bonus - toll + 1);
            game.getRandom().setSeed(seed); // Reset seed

            assertEquals(bonus - toll - consumption, cityD.arrive(player)); // Same bonus
            assertEquals(60 + toll + consumption - bonus, cityD.getValue());
            cityD.reset(); 
        }
    }

    @Test
    public void ArriveFromSameCountry(){
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed); // Reset seed
            Player player = new GUIPlayer(new Position(cityC, cityD, 0), 250);
            int bonus = country2.bonus(60);

            int consumption = game.getRandom().nextInt(250 + bonus + 1);
            game.getRandom().setSeed(seed); // Reset seed

            assertEquals(bonus - consumption, cityD.arrive(player)); // Same bonus
            assertEquals(60 + consumption - bonus, cityD.getValue());
            cityD.reset(); 
        }
    }

    @Test
    public void bonusZero(){
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed); // Reset seed
            Player player = new GUIPlayer(new Position(cityC, cityB, 0), 250);
            int bonus = country1.bonus(0);

            int toll = 250 / 5;

            int consumption = game.getRandom().nextInt(250 + bonus - toll + 1);
            game.getRandom().setSeed(seed); // Reset seed

            assertEquals(bonus - toll - consumption, cityB.arrive(player)); // Same bonus
            assertEquals(0 + toll + consumption - bonus, cityB.getValue());
            cityB.reset(); 
        }
    }

    @Test
    public void bonusNegative(){
        for(int seed = 0; seed < 100000; seed++) {
            game.getRandom().setSeed(seed); // Reset seed
            Player player = new GUIPlayer(new Position(cityA, cityE, 0), 250);
            int bonus = country2.bonus(-100);

            int toll = 250 / 5;

            int consumption = game.getRandom().nextInt(250 + bonus - toll + 1);
            game.getRandom().setSeed(seed); // Reset seed

            assertEquals(bonus - toll - consumption, cityE.arrive(player)); // Same bonus
            assertEquals(-100 + toll + consumption - bonus, cityE.getValue());
            cityE.reset(); 
        }
    }
}
