import java.util.*;
/**
 * Write a description of class CapitalCity here.
 *
 * @author (your name)
 * @version (a version number or a date)
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