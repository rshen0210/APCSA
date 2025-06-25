import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter {

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid gr = getGrid();
        
        for (int i = 0;i<2;i++) {
            Location loc = getLocation();
            if (i == 0)
            loc = loc.getAdjacentLocation(getDirection()+Location.AHEAD);
            if (i == 1)
            loc = loc.getAdjacentLocation(getDirection()+Location.HALF_CIRCLE);
            if (gr.isValid(loc)) {
                Actor act = getGrid().get(loc);
                if (act != null) {
                    actors.add(act);
                }
            }
        }
        return actors;
    }
}