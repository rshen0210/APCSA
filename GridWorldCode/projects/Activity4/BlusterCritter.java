import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Critter;

public class BlusterCritter extends Critter {
    private int courage;
    public BlusterCritter(int c) {
        courage = c;
    }
    public ArrayList<Actor> getActors() {
        int [] dir = new int[]{Location.AHEAD, Location.NORTHEAST, Location.EAST, Location.SOUTHEAST,
            Location.SOUTH, Location.SOUTHWEST, Location.WEST, Location.NORTHWEST};
        ArrayList<Actor> actors = new ArrayList<Actor>();
        for (int dirs : dir) {
            Location loc = getLocation().getAdjacentLocation(dirs);
            if (getGrid().isValid(loc)) {
                Location nextLoc = loc.getAdjacentLocation(dirs);
                if (getGrid().isValid(nextLoc)) {
                    Actor act = getGrid().get(nextLoc);
                    if (act != null)
                        actors.add(act);
                }
            }
        }
        return actors;
    }
    public void processActors(ArrayList<Actor> actors) {
        int num = 0;
        for (Actor act : actors) {
            if (act instanceof Critter)
            num++;
        }
        if (num>=courage) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - 0.05));
            int green = (int) (c.getGreen() * (1 - 0.05));
            int blue = (int) (c.getBlue() * (1 - 0.05));

            setColor(new Color(red, green, blue));
        }
        else if (num < courage) {
            Color c = getColor();
            int red, green, blue;
            red = c.getRed();
            green = c.getGreen();
            blue = c.getBlue();
            if(red < 255) red++;
            if(green < 255) green++;
            if(blue < 255) blue++;
            setColor(new Color(red, green, blue)); 
        }
    }
}