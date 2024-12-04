import java.awt.*;
/**
 * Models a Player which chooses its path based on a log (replaying a GUI player).
 * @author Nikolaj Ignatieff Schwartzbach. 
 * @version December 2020.
 */
public class LogPlayer extends GUIPlayer {

    private Log log;    // Log
    private int step;   // Step
    
    /**
     * Instantiates a new LogPlayer from a given log at a given position.
     * @param log   Log to be replayed.
     * @param pos   Position of this LogPlayer.
     */
    public LogPlayer(Log log, Position pos) {
        super(pos);
        this.log = log;
        step = 0;        
    }
    
    @Override
    public boolean step() {
        boolean stepped = super.step();
        String choice = log.getChoice(++step);        
        if(choice==null) {
            return stepped;
        }        
        if(choice.equals("Aborted")) {
            getCountry().getGame().abort();
            return stepped;
        }        
        travelTo(getCountry().getGame().getCity(choice));
        return stepped;
    }

    @Override
    public String getName() {
        return "GUI Player (log)";
    }
    
    @Override
    public Color getColor() {
        return Color.RED;
    }
}