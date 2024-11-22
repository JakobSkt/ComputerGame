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

    public int getSeed() {
        return seed;
    }

    public Settings getSettings() {
        return settings;
    }

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

    public void add(int step, City city) {
        choices.put(step, city);
    }
}
