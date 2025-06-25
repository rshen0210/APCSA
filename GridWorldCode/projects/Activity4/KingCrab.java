import java.util.ArrayList;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;

public class KingCrab extends CrabCritter {
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            Location loc = a.getLocation().getAdjacentLocation(getDirection());
            if (getGrid().isValid(loc)) {
                a.moveTo(loc);
            }
            else {
                a.removeSelfFromGrid();
            }
        }
    }
}