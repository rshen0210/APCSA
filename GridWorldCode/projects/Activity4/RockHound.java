import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;


public class RockHound extends Critter {
    /**
    * removes all instances of rocks nearby the Rockhound
    */
    public void processActors(ArrayList<Actor> actors) {
        for (Actor act : actors) {
            if (act instanceof Rock) {
                act.removeSelfFromGrid();
            }
        }
    }
}