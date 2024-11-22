/**
 * This class models a BorderCity
 *
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */
public class BorderCity extends City
{
    /**
     * Constructor for objects of class BorderCity
     * @param name Name of country
     * @param value Value of city
     * @param country Country to belong to
     */
    public BorderCity(String name, int value, Country country)
    {
        super(name, value, country);
    }

    /**
     * If arriving from a different country, add customs to city value. Else subtract bonus from city value.
     * @param p Player reference
     * @return bonus minus the paid customs or bonus
     */
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