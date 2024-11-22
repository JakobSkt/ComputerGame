/**
 * This class models a MafiaCountry
 * 
 * @author  Jakob Skøt Nielsen 202407223
 * @author  Daniel Dupont 202407440
 * @version november 2024
 */

public class MafiaCountry extends Country {

    /**
     * Creates a Country
     *
     * @param name Country name
     */
    public MafiaCountry(String name) {
        super(name);
    }

    /**
     * Calculates the bonus for the player, the chance for getting robbed and the given loss
     */
    @Override
    public int bonus(int value) {
        // Calculate random number between 0 and 100
        int randomValue = getGame().getRandom().nextInt(100 + 1);
        // Get risk of robbery from game
        int robberyRisk = getGame().getSettings().getRisk();
        // If the risk of robbery is lower than the random number, enter the statement
        if(robberyRisk <= randomValue) {
            return super.bonus(value);
        }
        return -getGame().getLoss();
    }
}
