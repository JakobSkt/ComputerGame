
/**
 * Write a description of class BorderCity here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BorderCity extends City
{
    /**
     * Constructor for objects of class BorderCity
     */
    public BorderCity(String name, int value, Country country)
    {
        super(name, value, country);
    }

    @Override
    public int arrive(Player p){
        Country prevCountry = p.getFromCountry();
        int bonus = getCountry().bonus(getValue());
        int money = p.getMoney();
        if(!getCountry().equals(prevCountry)){
            double toll = getCountry().getGame().getSettings().getTollToBePaid() / 100.0;
            int paid =  (int) (money * toll);
            changeValue(paid - bonus);
            
            return bonus - paid;
        }
        changeValue(-bonus);
        return bonus;
    }
}