/**
 *	The object to store US state information.
 *
 *	@author	Ryan Shen
 *	@since	5/29/24
 */
public class State implements Comparable<State>
{
	private String name;
	private String abbreviation;
	private int population;
	private int area;
	private int reps;
	private String capital;
	private int month;
	private int day;
	private int year;
	
	/**
	 * Constructor: initializes the fields
	 * @param n
	 * @param a
	 * @param p
	 * @param ar
	 * @param r
	 * @param c
	 * @param m
	 * @param d
	 * @param y
	 */
	public State(String n, String a, int p, int ar, int r, String c, int m, int d, int y) 
	{
		name = n;
		abbreviation = a;
		population = p;
		area = ar;
		reps = r;
		capital = c;
		month = m;
		day = d;
		year = y;
	}
	/**
	 * compares this state's name to anothers 
	 * @param other // the State to compare to
	 */
	public int compareTo(State other) 
	{
		return this.getName().compareTo(other.getName());
	}
	/**
	 * accessor method to get the name
	 * @return the name of the state
	 */
	public String getName ( )
	{
		return name;
	}
	/**
	 * overrides the toString method to print out in a 
	 * correct format of all the relevant information
	 * @return formatted string of relevant state information
	 */
	public String toString() 
	{
		return String.format("%-20s %-5s %-10d %-10d %-5d %-20s %2d %2d %4d",
			name, abbreviation, population, area, reps, capital, month, day, year);
	}
}
