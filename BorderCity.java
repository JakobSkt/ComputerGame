/**
 * This class models the BorderCity class and its methods
 * 
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
*/

public class BorderCity extends City {

    public BorderCity(String name, int value, Country country) {
        super(name, value, country);
    }

    @Override
    public int arrive(Player p) {
        int bonus = getCountry().bonus(getValue());
        int wealth  = p.getMoney();
        Country previousCountry = p.getFromCountry();
        if(!previousCountry.equals(getCountry())) {
            double customsPercentage = getCountry().getGame().getSettings().getTollToBePaid() / 100.0;
            int customsPaid = (int) (wealth * customsPercentage);
            changeValue(customsPaid - bonus);
            return bonus - customsPaid;
        }
        changeValue(-bonus);
        return bonus;
    }
}
