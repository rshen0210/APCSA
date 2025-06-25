import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
public class QuickCrab extends CrabCritter {
	public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        int [] dir = new int[] {Location.RIGHT, Location.LEFT};
        for (int dirs : dir) {
            int direct = getDirection()+dirs;
            Location bob = getLocation().getAdjacentLocation(direct);
            if (gr.isValid(bob)) {
                if (gr.get(bob) == null) {
                    Location nextLoc = bob.getAdjacentLocation(direct);
                    if (gr.isValid(nextLoc)) {
                        if (gr.get(nextLoc) == null) {
                            locs.add(nextLoc);
                        }
                    }
                }
                
            }
        }
        if (locs.size() == 0) {
            return super.getMoveLocations();
        }
        return locs;
    }    
}