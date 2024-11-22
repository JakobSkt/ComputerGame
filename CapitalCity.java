import java.util.*;
/**
 * This class models a CapitalCity
 *
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */
public class CapitalCity extends BorderCity
{
    /**
     * Constructor for objects of class BorderCity
     */
    public CapitalCity(String name, int value, Country country)
    {
        super(name, value, country);
    }

    /**
     * Calculate the consumption based on random number in range of 0 to player's money.
     * @param p Player reference
     * @return bonus minus the consumption
     */
    @Override
    public int arrive(Player p){
        int bonus = super.arrive(p);
        int money = p.getMoney() + bonus;
        Random rand = getCountry().getGame().getRandom();
        int consumption = rand.nextInt(money + 1);
        changeValue(consumption);
        return bonus - consumption;
    }
}