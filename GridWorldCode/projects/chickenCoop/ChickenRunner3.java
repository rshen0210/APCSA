/**
 * This is the runner that makes an equillibrium between the foxes and the chickens
 * 
 * @author Ryan Shen
 * @since 3/31/24
 * 
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class ChickenRunner3
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(25,25);
        ActorWorld world = new ActorWorld(mygrid);
        
        for (int i = 0;i<25;i+=3) {
            for (int j = 0;j<25;j+=3) {
                world.add(new Location(i,j),new Chicken());
            }
        }
        world.add(new Location(2,2),new Fox());
        world.add(new Location(12,5),new Fox());
        world.add(new Location(20,3),new Fox());
        world.add(new Location(20,23),new Fox());
        world.add(new Location(2,23),new Fox());
        world.add(new Location(21,23),new Fox());
        world.add(new Location(2,18),new Fox());
        world.add(new Location(14,14),new Fox());
        world.add(new Location(12,8),new Fox());
        
        world.add(new Location(3,0),new Rock());
        world.add(new Location(0,3),new Rock());
        world.add(new Location(15,7),new Rock());
        world.add(new Location(11,6),new Rock());
        world.add(new Location(0,7),new Rock());	
        world.add(new Location(19,1),new Rock());
        		
        world.show();
    }
}