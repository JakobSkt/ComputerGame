import java.util.*;
/**
 * Write a description of class MafiaCountry here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MafiaCountry extends Country
{
    /**
     * Constructor for objects of class MafiaCountry
     */
    public MafiaCountry(String name)
    {
        super(name);
    }

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
