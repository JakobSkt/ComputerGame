import java.util.*;
/**
 * This class models a MafiaCountry
 *
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */
public class MafiaCountry extends Country
{
    /**
     * Constructor for objects of class MafiaCountry
     * @param name Name of country
     */
    public MafiaCountry(String name)
    {
        super(name);
    }

    /**
     * Returns the loss 20% of times and bonus 80%
     * @param value Int value of city
     * @return loss or bonus
     */
    @Override
    public int bonus(int value){
        //get to risk of robbery in percent
        int rand = getGame().getRandom().nextInt(100 + 1);
        int risk = getGame(). getSettings().getRisk();
        if(risk <= rand){
            return super.bonus(value);
        }
        return -getGame().getLoss();
    }
}
