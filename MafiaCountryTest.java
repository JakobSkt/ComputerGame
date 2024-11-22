
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * The test class MafiaCountryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MafiaCountryTest
{
    private Game game;
    private Country country1, country2;

    /**
     * Default constructor for test class MafiaCountryTest
     */
    public MafiaCountryTest()
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
        game = new Game(0);

        country1 = new Country("Country 1");
        country2 = new MafiaCountry("Country 2");
        country1.setGame(game);
        country2.setGame(game);
    }

    @Test
    public void testBonus(){
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
                    assertTrue(bonus <= -10 && bonus >= -50);
                    loss -= bonus;
                    values.add(bonus);
                }
                else{
                    bonuses.add(bonus);
                    assertTrue(bonus <= 80 && bonus >= 0);
                }
            }
            double percentRobs = ((double)robs / 10000.0) * 100.0;
            double averageLoss = loss / robs;
            assertTrue(percentRobs * 1.1 >= 20.0 && percentRobs * 0.9 <= 20.0);
            assertTrue(averageLoss <= 30.0 * 1.1 && averageLoss >= 30.0 * 0.9);

            assertEquals(41, values.size());
            assertEquals(81, bonuses.size());

        }
    }
    
    @Test
    public void testBonusZero(){
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
                    assertTrue(bonus <= -10 && bonus >= -50);
                    loss -= bonus;
                    values.add(bonus);
                }
                else{
                    bonuses.add(bonus);
                }
            }
            double percentRobs = ((double)robs / 10000.0) * 100.0;
            double averageLoss = loss / robs;
            assertTrue(percentRobs * 1.1 >= 20.0 && percentRobs * 0.9 <= 20.0);
            assertTrue(averageLoss <= 30.0 * 1.1 && averageLoss >= 30.0 * 0.9);

            assertEquals(41, values.size());
            assertEquals(1, bonuses.size());

        }
    }
    
    @Test
    public void testBonusNegative(){
        for(int seed = 0; seed < 1000; seed++){
            game.getRandom().setSeed(seed);
            float loss = 0;
            int robs = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> bonuses = new HashSet<>();

            for(int i = 0; i < 10000; i++){
                int bonus = country2.bonus(-80);//Will always return 0
                if(bonus < 0){
                    robs++;
                    assertTrue(bonus <= -10 && bonus >= -50);
                    loss -= bonus;
                    values.add(bonus);
                }
                else{
                    bonuses.add(bonus);
                }
            }
            double percentRobs = ((double)robs / 10000.0) * 100.0;
            double averageLoss = loss / robs;
            assertTrue(percentRobs * 1.1 >= 20.0 && percentRobs * 0.9 <= 20.0);
            assertTrue(averageLoss <= 30.0 * 1.1 && averageLoss >= 30.0 * 0.9);

            assertEquals(41, values.size());
            assertEquals(1, bonuses.size());

        }
    }
}
