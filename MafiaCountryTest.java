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
    private MafiaCountry country1;
    /**
     * Sets up the test fixture that is used on every test case
    */
    @BeforeEach
    public void setUp()
    {
        // Create countries
        country1 = new MafiaCountry("Sverige");

        // Create game objects
        game = new Game(0);
        country1.setGame(game);
    }
    
    @Test
    public void constructor() {
        // Check that the name field is initialized correct
        assertEquals("Sverige", country1.getName());
    }
    
    @Test
    public void bonus() {
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 10000; i++) {
                // Initialize bonus and add to sum for normal use
                int bonus = country1.bonus(80);
                if(bonus < 0) { // Robbery occurred
                    robs++;
                    assertTrue(bonus >= -50 && bonus <= -10);
                    loss -= bonus;
                    values.add(bonus);
                } else { // No robbery occurred
                    bonuses.add(bonus);
                    // When entered, the bonus is always at least 0 -> just checking upper case
                    assertTrue(bonus <= 80);
                }
            }
            // Calculating percentage of times robbed
            double robsPercentage = ( (double) robs/10000.0) * 100.0;
            // Calculate average loss
            double lossAverage = loss/robs;
            assertTrue(robsPercentage >= 20.0 * 0.9 && robsPercentage <= 20.0  * 1.1);
            assertTrue(lossAverage >= 30.0 * 0.9 && lossAverage <= 30.0 * 1.1);
            assertEquals(41, values.size());
            assertEquals(81, bonuses.size());
        }
    }
    @Test
    public void bonusZero() {
        for(int seed = 0; seed < 100; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 10000; i++) {
                // Initialize bonus and add to sum for normal use
                int bonus = country1.bonus(0);
                if(bonus < 0) { // Robbery occurred
                    robs++;
                    assertTrue(bonus >= -50 && bonus <= -10);
                    loss -= bonus;
                    values.add(-bonus);
                } else { // No robbery occurred
                    bonuses.add(bonus);
                    // When entered, the bonus is always over 0 -> just checking upper case
                    assertTrue(bonus <= 80);
                }
            }
            // Calculating percentage of times robbed
            double robsPercentage = (robs/10000.0) * 100.0;
            // Calculate average loss
            double lossAverage = loss/robs;
            assertTrue(robsPercentage >= 20.0 * 0.9 && robsPercentage <= 20.0  * 1.1);
            assertTrue(lossAverage >= 30.0 * 0.9 && lossAverage <= 30.0 * 1.1);
            assertEquals(41, values.size());
            // Only one value in set since it take a random number between zero and zero
            assertEquals(1, bonuses.size());
        }
    }

    @Test
    public void bonusNegative() {
        for(int seed = 0; seed < 100; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 10000; i++) {
                // Initialize bonus and add to sum for normal use
                int bonus = country1.bonus(-80);
                if(bonus < 0) { // Robbery occurred
                    robs++;
                    assertTrue(bonus >= -50 && bonus <= -10);
                    loss -= bonus;
                    values.add(-bonus);
                } else { // No robbery occurred
                    bonuses.add(bonus);
                    // When entered, the bonus is always over 0 -> just checking upper case
                    assertTrue(bonus <= 80);
                }
            }
            // Calculating percentage of times robbed
            double robsPercentage = (robs/10000.0) * 100.0;
            // Calculate average loss
            double lossAverage = loss/robs;
            assertTrue(robsPercentage >= 20.0 * 0.9 && robsPercentage <= 20.0  * 1.1);
            assertTrue(lossAverage >= 30.0 * 0.9 && lossAverage <= 30.0 * 1.1);
            assertEquals(41, values.size());
            // Only one value since every bonus will be 0
            assertEquals(1, bonuses.size());
        }
    }
}
