/**
 * This is the Egg class that extends Actor
 * An egg will hatch a chicken
 * @author Ryan Shen
 * @since 3/31/24
 */
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.awt.Color;
public class Egg extends Actor{
	private int lifetime;
	private static final double DARKENING_FACTOR = 0.01;
	public Egg() {
		setColor(Color.WHITE);
		lifetime = 50;
	}
	/**
	 * this is the act method that makes an egg hatch a chicken
	 * as well as controls the darkening of the egg
	 * @param none
	 * @return void
	 */
	public void act() {
		lifetime--;
		Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
		
		if (lifetime == 0) {
			Location loc = getLocation();
			Actor bob = new Chicken();
			bob.putSelfInGrid(getGrid(), loc);
		}
		else if (lifetime <= 5)
		setColor(Color.RED);
		else
        setColor(new Color(red, green, blue));
	}
}