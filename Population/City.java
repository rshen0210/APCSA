/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Ryan Shen
 *	@since	12/5/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String state, city, cityType;
	private int population;
	
	// constructor
	public City (String state1, String city1, String cityType1, int population1) {
		state = state1;
		city = city1;
		cityType = cityType1;
		population = population1;
	}
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	@Override
	public int compareTo(City other) {
		// compare population first
		if ((population - other.population) != 0) {
			return population - other.population;
		}
		// if population same then compare state names
		else if (!state.equals(other.state)) {
			return state.compareTo(other.state);
		}
		// if those are the same then compare city names
		else
		return city.compareTo(other.city);
	}
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other) {
		if (this.city.equals(other.city)) {
			return true;
		}
		return false;
	}
	/**	Accessor methods */
	public String getState() {
		return state;
	}
	public String getCity () {
		return city;
	}
	public String getCityType() {
		return cityType;
	}
	public int getPopulation() {
		return population;
	}
	
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, city, cityType,
						population);
	}
}