/**
 * This is the fox code which extends critter
 * a fox chases after chickens and eats chickens
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

public class Fox extends Critter {
    private boolean sleep;
    private int steps;
    private int hunger;
    public Fox() {
        sleep = false;
        steps = 0;
        hunger = 20;
        setColor(null);
    }
    /**
     * gets all the chickens nearby
     * @return a list of the chickens nearby
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = getGrid().getNeighbors(getLocation());
        ArrayList<Actor> a = new ArrayList<Actor>();
        for (Actor b : actors) {
            if (b instanceof Chicken)
            a.add(b);
        }
        return a;
    }
    /**
     * kills the chickens nearby randomly
     * @param actors
     */
    public void processActors(ArrayList<Actor> actors)
    {
        if (sleep) {
            steps++;
            if (steps == 10) 
            sleep = false;
        }
        else {
            steps=0;
            if (actors.size() == 0)
            return;
            int choose = (int)(Math.random() * actors.size());
            Actor a = new Tombstone();
            a.putSelfInGrid(getGrid(), actors.get(choose).getLocation());
            sleep = true;
        }
        
    }
    /**
     * gets the move locations for the fox
     * does this by finding the chickens in the grid
     * @return a list of all the chickens' locations
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locations = getGrid().getOccupiedLocations();
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location loc : locations) {
            Actor a = getGrid().get(loc);
            if (a instanceof Chicken) {
                locs.add(loc);
            }
        }
        return locs;
    }
    /**
     * selects where to move based on the closest chicken
     * @param locs
     * @return the place to move to
     */
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        ArrayList<Location> sameDist = new ArrayList<Location>();
        double min = 10000000;
        int n = locs.size();
        if (n == 0) {
            ArrayList<Location> moveLocs = super.getMoveLocations();
            return super.selectMoveLocation(moveLocs);
        }
        for (Location loc : locs)
        {
            Actor a = getGrid().get(loc);
            int heightDifference = a.getLocation().getRow() - getLocation().getRow();
            int widthDifference = a.getLocation().getCol() - getLocation().getCol();
            int heightSquared = heightDifference*heightDifference;
            int widthSquared = widthDifference*widthDifference; 
            double distance = Math.sqrt(widthSquared+heightSquared);
            if (distance == min) {
                sameDist.add(a.getLocation());
            }
            else if (distance < min) {
                sameDist.clear();
                sameDist.add(a.getLocation());
                min = distance;
            }
        }
        int r = (int) (Math.random() * sameDist.size());
        int direct = getLocation().getDirectionToward(sameDist.get(r));
        Location loc = getLocation().getAdjacentLocation(direct);
        if (getGrid().isValid(loc)) {
            Actor a = getGrid().get(loc);
            if (a == null) {
                return loc;
            }
        }
        ArrayList<Location> moveLocs = super.getMoveLocations();
        return super.selectMoveLocation(moveLocs);
    }
    /**
     * makes a move if it can
     * if it can't it will randomly turn
     * @param loc
     */
    public void makeMove(Location loc) {
        if (sleep) {
            hunger = 20;
        }
        else {
            hunger--;
            if (hunger == 0) {
                Actor a = new Tombstone();
                a.putSelfInGrid(getGrid(), getLocation());
                return;
            }
            ArrayList<Location> bob = getGrid().getEmptyAdjacentLocations(getLocation());
            if (loc == null)
                removeSelfFromGrid();
            else if (bob.size() == 0) {
                int [] turningLocations = new int[] { 0, 45, 90, 135, 180, 225, 270, 315 };
                int r = (int)(Math.random()*8);
                int num = turningLocations[r]/45;
                for (int i = 0;i<num;i++) {
                    turn();
                }
            }
            else {
                int joe = getLocation().getDirectionToward(loc);
                setDirection(joe);
                moveTo(loc);
            }
        }
        
            
    }
    /**
     * helper method to turn the fox
     */
    private void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
}