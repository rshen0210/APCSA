/**
 * This is the Chicken class that extends Critter
 * Chickens lay eggs and run around
 * @author Ryan Shen
 * @since 3/31/24
 */
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.util.ArrayList;
import java.awt.Color;

public class Chicken extends Critter {
	private int age;
	private boolean middleAge;
	private static final double DARKENING_FACTOR = 0.05;
	public Chicken() {
		setColor(Color.WHITE);
		age = 300;
		middleAge = false;
	}
	/**
	 * This method selects the move locations for the chicken
	 * it'll decide whether the chicken will move forward or turn 
	 * in a random direction
	 * @param locs
	 * @return
	 */
	public Location selectMoveLocation(ArrayList<Location> locs)
    {
		int n = locs.size();
		int choice = (int)(Math.random()*2);
		if (choice == 1) {
			if (getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))) {
				Actor a = getGrid().get(getLocation().getAdjacentLocation(getDirection()));
				if (a == null)
				return getLocation().getAdjacentLocation(getDirection()); 
			}
		}
		int [] turningLocations = new int[] { 0, 45, 90, 135, 180, 225, 270, 315 };
		int r = (int)(Math.random()*8);
		int num = turningLocations[r]/45;
		for (int i = 0;i<num;i++) {
			turn();
		}
		return getLocation();
	}
	
	/**
	 * overrides the processActors method to make it do nothing
	 * @param actors
	 */
    public void processActors(ArrayList<Actor> actors)
    {
    }
	/**
	 * This method makes the Chicken move to the location specified 
	 * in the parameter
	 * How a chicken decides to move is based on its age
	 * @param loc
	 */
	public void makeMove(Location loc)
    {
		age--;
		
        if (loc == null)
            removeSelfFromGrid();
        else if (age == 0) { 
			Actor bob = new Tombstone();
			bob.putSelfInGrid(getGrid(), loc);
		}
		else if (age % 4 == 0 && age <= 20) 
			moveTo(loc);
        else if (age % 2 == 0 && age <= 100)
            moveTo(loc);
		else if (loc!=null && loc!=getLocation()){
			int chance = (int)(Math.random() * 20);
			Location loc2 = getLocation();
			Actor bob = new Egg();
			moveTo(loc);
			if (chance == 2) {
				bob.putSelfInGrid(getGrid(),loc2);
			}
			
		}
    }
	/**
	 * just a helper method to turn the chicken
	 */
	private void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
	/**
	 * just a helper method to darken the chicken
	 */
    private void darken() {
		Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
	}
}