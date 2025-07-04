/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Flower;
import java.awt.Color;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Blossom extends Flower
{
	private int lifetime;
	private int steps;
	private Color initialColor = Color.GREEN;

    /**
     * constructor
     * @param length decides the lifetime of the blossom
     */
    public Blossom(int length) {
        lifetime = length;
        setColor(initialColor);
    }

	public Blossom() {
		lifetime = 10;
		setColor(initialColor);
	}
	
	
    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
		if(steps < lifetime){
			super.act();
			steps++;
		}
		else
			removeSelfFromGrid();
    }
}
