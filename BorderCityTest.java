
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BorderCityTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BorderCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityC;
    private BorderCity cityB, cityD, cityE;

    /**
     * Default constructor for test class BorderCityTest
     */
    public BorderCityTest()
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
        cityB = new BorderCity("City B", 0, country1);
        cityC = new City("City C", 80, country2);
        cityD = new BorderCity("City D", 60, country2);
        cityE = new BorderCity("City E", -100, country2); 

    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testFormAnotherCountry(){
        // Testing with a bonus of 0
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityA, cityD, 0), 250);
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country2.bonus(60); // Remember bonus
            int toll = 250 / 5; // 20% of 250
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(bonus - toll, cityD.arrive(player)); // Same bonus
            assertEquals(cityD.getInitialValue() + toll - bonus, cityD.getValue());
            cityD.reset(); 
            cityA.reset(); 
        }
    }
    
    @Test
    public void testFormSameCountry(){
        // Testing with a bonus of 0
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityC, cityD, 0), 250);
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country2.bonus(60); // Remember bonus
            int toll = 250 / 5; // 20% of 250
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(bonus, cityD.arrive(player)); // Same bonus
            assertEquals(cityD.getInitialValue()    - bonus, cityD.getValue());
            cityD.reset(); 
        }
    }
    
    @Test
    public void testBonus0(){
        // Testing with a bonus of 0
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityC, cityB, 0), 250);
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country1.bonus(0); // Remember bonus
            int toll = 250 / 5; // 20% of 250
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(bonus - toll, cityB.arrive(player)); // Same bonus
            assertEquals(cityB.getInitialValue() + toll - bonus, cityB.getValue());
            cityB.reset(); 
        }
    }
    
    @Test
    public void testBonusNegative(){
        // Testing with a bonus of 0
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityA, cityE, 0), 250);
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country2.bonus(-100); // Remember bonus
            int toll = 250 / 5; // 20% of 250
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(bonus - toll, cityE.arrive(player)); // Same bonus
            assertEquals(cityE.getInitialValue() + toll - bonus, cityE.getValue());
            cityE.reset(); 
        }
    }
}
