import java.io.IOException;
import java.io.PrintWriter;
/**
 * Models the  settings of the game at a given time.
 * @author Nikolaj Ignatieff Schwartzbach.
 * @version August 2019.
 */
public class Settings implements java.io.Serializable {

    private boolean[] activePlayers;  // Active players. Boolean array of length 3 (0 = random, 1 = greedy, 2 = smart).
    private int tollSize;             // Size of the toll to be paid (in %).
    private int robRisk;              // Risk of robbery (in %).
    private int minLoss;              // Minimum loss when robbed (in €).
    private int maxLoss;              // Maximum loss when robbed (in €).
    private int gameSpeed;            // Speed of the game (0: Stop, 1: Slow, 2: Medium, 3: Fast, 4: Sonic).

    /**
     * Creates a new Settings object with default values.
     */
    public Settings() {
        this(new boolean[] {true, true, true}, 20, 20, 10, 50, 2);
    }

    /**
     * Creates a new Settings object from a set of parameters.
     * @param activePlayers  Active players. Boolean array of length 3 (0 = random, 1 = greedy, 2 = smart).
     * @param tollSize       Size of the toll to be paid (in %).
     * @param robRisk        Risk of robbery (in %).
     * @param minLoss        Minimum loss when robbed (in €).
     * @param maxLoss        Maximum loss when robbed (in €).
     * @param gameSpeed      Speed of the game (0: Stop, 1: Slow, 2: Medium, 3: Fast, 4: Sonic).
     */
    public Settings(boolean[] activePlayers, int tollSize, int robRisk, int minLoss, int maxLoss, int gameSpeed) {
        this.activePlayers = activePlayers;
        this.tollSize = tollSize;
        this.robRisk = robRisk;
        this.minLoss = minLoss;
        this.maxLoss = maxLoss;
        this.gameSpeed = gameSpeed;
    }

    /**
     * Creates a new Settings object which is a deep copy of Settings object in the paramter.
     * @param s   Settings object to be copied.
     */
    public Settings(Settings s) {
        this();
        for(int i=0; i<3; i++) {
            this.activePlayers[i] = s.activePlayers[i];
        }
        this.tollSize = s.tollSize;
        this.robRisk  = s.robRisk;
        this.minLoss  = s.minLoss;
        this.maxLoss  = s.maxLoss;
        this.gameSpeed = s.gameSpeed;
    }

    /**
     * Creates a new Settings object from a String array.
     * The first line is the active players, the second line is tollSize, and so on.
     * @param str  String array representation of a Settings object.
     * @throws     SettingsException.
     */
    public Settings(String[] str) throws SettingsException {
        this(collectStrings(str));
    }

    private static String collectStrings(String[] str) {
        StringBuilder sb = new StringBuilder();
        for(String s : str) {
            sb.append(s+"\r\n");
        }
        return sb.toString();
    }

    /**
     * Creates a new Settings object from a String representation.
     * @param s   String representation of a Settings object.
     * @throws    SettingsException.
     */
    public Settings(String str) throws SettingsException {
        this();
        String[] lines = str.replace("\r", "").split("\n");

        if(lines.length != 6) {
            throw new SettingsException("Expected a total of 6 lines, but received " + lines.length + ".");
        }

        for(int i=0; i<lines.length; i++) {
            String s = lines[i];
            switch(i) {
                case 0:
                if(s.length() != 3) {
                    throw new SettingsException("Expected first line to have only 3 characters, but received " + s.length() + ".");
                }
                char[] chars = s.toCharArray();
                for(int j=0; j<3; j++) { setActive(j, chars[j] == '1'); }
                break;
                case 1: setTollToBePaid(Integer.parseInt(s)); break;
                case 2: setRisk(Integer.parseInt(s)); break;
                case 3: minLoss = Integer.parseInt(s); break;
                case 4: maxLoss = Integer.parseInt(s); break;
                case 5: setGameSpeed(Integer.parseInt(s)); break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++) {
            sb.append(activePlayers[i] ? "1" : "0");
        }
        sb.append("\r\n"+tollSize+"\r\n");
        sb.append(robRisk+"\r\n");
        sb.append(minLoss+"\r\n");
        sb.append(maxLoss+"\r\n");
        sb.append(gameSpeed+"\r\n");
        return sb.toString();
    }

    /**
     * Saves the settings to a file.
     */
    private void save() {
        try{
            PrintWriter out = new PrintWriter("settings.dat");
            out.print(this);
            out.close();
        }
        catch(IOException e) {
            System.out.println("Unable to save settings: " + e.getMessage());
        }
    }

    /**
     * Checks whether a given player is active.
     * @param player   Player to be cheked (0: Random, 1: Greedy, 2: Smart)).
     * @return         true if and only if the specified player is active.
     */
    public boolean isActive(int player) {
        return activePlayers[player];
    }

    /**
     * Sets the current acitvity status of a the spcified player.
     * @param player   Player to be set (0: Random, 1: Greedy, 2: Smart). 
     * @param active   New activity status for the spcified player.
     */
    public void setActive(int player, boolean active) {
        activePlayers[player] = active;
        save();
    }

    /**
     * Gets the current toll.
     * @return   Toll to be paid (in %).
     */
    public int getTollToBePaid() {
        return tollSize;
    }

    /**
     * Sets the current toll.
     * @param tollSize  New toll to be paid (in %).
     */
    public void setTollToBePaid(int tollSize) {
        this.tollSize = tollSize;
        save();
    }

    /**
     * Gets the current risk of being robbed.
     * @return   Risk of robbery (in %).
     */
    public int getRisk() {
        return robRisk;
    }

    /**
     * Sets the current risk of being robbed (in %).
     * @param robRisk   New risk of robbery (in %).
     */
    public void setRisk(int robRisk) {
        this.robRisk = robRisk;
        save();
    }

    /**
     * Gets the minimum amount lost in a robbery.
     * @return   Minimum amount lost in a robbery (in €).
     */
    public int getMinRobbery() {
        return minLoss;
    }

    /**
     * Gets the maximum amount lost in a robbery.
     * @return   Maximum amount lost in a robbery €.
     */
    public int getMaxRobbery() {
        return maxLoss;
    }

    /**
     * Sets the current bounds for robbery.
     * @param min   New minimum amount lost in a robbery (in €).
     * @param max   New maximum amount lost in a robbery (in €).
     */
    public void setMinMaxRobbery(int min, int max) {
        this.minLoss = min;
        this.maxLoss = max;
        save();
    }

    /**
     * Gets the speed of the game.
     * @return   The speed of the game (0: Stop, 1: Slow, 2: Medium, 3: Fast, 4: Sonic).
     */
    public int getGameSpeed() {
        return gameSpeed;
    }

    /**
     * Sets the current speed of the game.
     * @param gameSpeed   The new speed of the game (0: Stop, 1: Slow, 2: Medium, 3: Fast, 4: Sonic).
     */
    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
        save();
    }
}

class SettingsException extends Exception {
    public SettingsException(String s) {
        super(s);
    }
}