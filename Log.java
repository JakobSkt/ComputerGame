import java.io.Serializable;
import java.util.Map;

/**
 * This class models a saved Log of the game
 *
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */

public class Log implements Serializable {
    private int seed;
    private Settings settings;
    private Map<Integer, City> choices;

    public Log(int seed, Settings settings) {
        this.seed = seed;
        this.settings = settings;
    }

    /**
     * Get seed value
     * @return seed
     */
    public int getSeed() {
        return seed;
    }

    /**
     * Get settings
     * @return settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Returns the city name selected in the specified step
     * @param step The step to return city from
     * @return City name
     */
    public String getChoice(int step) {
        City result = null;
        for(City c : choices.values()) {
            for(int steps :  choices.keySet()) {
                if (steps == step) {
                    result = c;
                    break;
                }
            }
        }
        if(result != null) {
            return result.toString();
        }
        return null;

    }

    /**
     * Add element - consisting of a Step and a City - to choices Map
     * @param step The specified step
     * @param city The specified city
     */
    public void add(int step, City city) {
        choices.put(step, city);
    }
}
