import java.io.IOException;
import java.util.*;

public class Run {
    public static void main(String[] args) throws IOException {
        MafiaCountry mc = new MafiaCountry("Sverige");
        Game game = new Game(0);
        mc.setGame(game);
        mc.bonus(50);
    }
}
