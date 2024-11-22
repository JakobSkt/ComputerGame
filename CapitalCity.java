import java.util.*;

/**
 * This class tests the CapitalCity class and its methods
 * 
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */

public class CapitalCity extends BorderCity {
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     * Arrives at the city and calculates the bonus, customs and consumption
     * @param p player object
     * @return bonus after subtracting customs and consumption
     */
    @Override
    public int arrive(Player p) {
        int bonus = super.arrive(p);
        int wealth  = p.getMoney() + bonus;
        Random rand = getCountry().getGame().getRandom();
        int consumption = rand.nextInt(wealth + 1);
        changeValue(consumption);
        return bonus - consumption;
    }

}
