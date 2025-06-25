/**
 * This is the tombstone class that extends Actor
 * A tombstone will appear after an animal dies
 * @author Ryan Shen
 * @since 3/31/24
 */
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
public class Tombstone extends Actor {
	private int lifetime;
	public Tombstone() {
		lifetime = 20;
	}
	/**
	 * This is the act method that makes the 
	 * tombstone "age"
	 * @param none
	 * @return void
	 */
	public void act() {
		lifetime--;
		if (lifetime == 0)
		removeSelfFromGrid();
	}
	
}