
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * This class tests the MafiaCountry class and its methods
 *
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */
public class MafiaCountryTest
{
    private Game game;
    private Country country1, country2;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        game = new Game(0);

        country1 = new Country("Country 1");
        country2 = new MafiaCountry("Country 2");
        country1.setGame(game);
        country2.setGame(game);
    }

    @Test
    public void bonus(){
        for(int seed = 0; seed < 1000; seed++){
            game.getRandom().setSeed(seed);
            float loss = 0;
            int robs = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 10000; i++){
                int bonus = country2.bonus(80); 
                if(bonus < 0){
                    robs++;
                    // Check that loss is in correct range
                    assertTrue(bonus <= -10 && bonus >= -50);
                    loss -= bonus;
                    values.add(bonus);
                }
                else{
                    bonuses.add(bonus);
                    // Check that bonus is in correct range
                    assertTrue(bonus <= 80 && bonus >= 0);
                }
            }
            // Calculate rob percentage
            double percentRobs = ((double)robs / 10000.0) * 100.0;
            // Calculate average loss
            double averageLoss = loss / robs;
            // Check that the calculated values in inside 10% of expected values
            assertTrue(percentRobs * 1.1 >= 20.0 && percentRobs * 0.9 <= 20.0);
            assertTrue(averageLoss <= 30.0 * 1.1 && averageLoss >= 30.0 * 0.9);

            assertEquals(41, values.size());
            assertEquals(81, bonuses.size());

        }
    }
    
    @Test
    public void bonusZero(){
        for(int seed = 0; seed < 1000; seed++){
            game.getRandom().setSeed(seed);
            float loss = 0;
            int robs = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 10000; i++){
                int bonus = country2.bonus(0); //will always retun 0
                if(bonus < 0){
                    robs++;
                    // Check that loss is in correct range
                    assertTrue(bonus <= -10 && bonus >= -50);
                    loss -= bonus;
                    values.add(bonus);
                }
                else{
                    bonuses.add(bonus);
                }
            }
            // Calculate rob percentage
            double percentRobs = ((double)robs / 10000.0) * 100.0;
            // Calculate average loss
            double averageLoss = loss / robs;
            // Check that the calculated values in inside 10% of expected values
            assertTrue(percentRobs * 1.1 >= 20.0 && percentRobs * 0.9 <= 20.0);
            assertTrue(averageLoss <= 30.0 * 1.1 && averageLoss >= 30.0 * 0.9);

            assertEquals(41, values.size());
            // Set size of 1, since bonus is always 0
            assertEquals(1, bonuses.size());
        }
    }
    
    @Test
    public void bonusNegative(){
        for(int seed = 0; seed < 1000; seed++){
            game.getRandom().setSeed(seed);
            float loss = 0;
            int robs = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 10000; i++){
                int bonus = country2.bonus(-80); // Will always return 0
                if(bonus < 0){
                    robs++;
                    // Check that loss is in correct range
                    assertTrue(bonus <= -10 && bonus >= -50);
                    loss -= bonus;
                    values.add(bonus);
                }
                else{
                    bonuses.add(bonus);
                }
            }
            // Calculate rob percentage
            double percentRobs = ((double)robs / 10000.0) * 100.0;
            // Calculate average loss
            double averageLoss = loss / robs;
            // Check that the calculated values in inside 10% of expected values
            assertTrue(percentRobs * 1.1 >= 20.0 && percentRobs * 0.9 <= 20.0);
            assertTrue(averageLoss <= 30.0 * 1.1 && averageLoss >= 30.0 * 0.9);

            assertEquals(41, values.size());
            // Set size of 1, since bonus is always 0
            assertEquals(1, bonuses.size());
        }
    }
}
