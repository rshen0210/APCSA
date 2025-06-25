import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - This code uses many sorting algorithms in order to sort
 *	cities and states by a variety of ways.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Ryan Shen
 *	@since	12/5/23
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	private List<City> temps;
	private long endMillisec;
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	/** 
	 * 	Opens the txt file and creates a scanner
	 * 	@param none
	 * 	@return void
	 */
	public void openData() {
		cities = new ArrayList<>();
		Scanner input = FileUtils.openToRead(DATA_FILE);
		input.useDelimiter("[\t\n]");
		while (input.hasNext()) {
			String state = input.next(); // since state is first column
			String cityName = input.next(); // somce city is second column
			String cityType = input.next(); // since cityType is third column
			int population = input.nextInt(); // since population is fourth column
			input.nextLine();
			cities.add(new City(state, cityName, cityType, population));
		}
		input.close();
	}
	/** 
	 * 	this method runs the population code out to terminal
	 * 	@param none
	 * 	@return void
	 */
	public void run() {
		int index = 1;
		printIntroduction();
		while (index!= 0) {
			int choice = 0;
			String prefer = "";
			String top = "";
			boolean valid = false;
			top = String.format("%-28s %-22s %-12s %12s", "      State", "City", "Type",
			"Population");
			printMenu();
			openData();
			System.out.println("\n");
			choice = Prompt.getInt("Enter Selection ");
			System.out.println("\n");
			long startMillisec = System.currentTimeMillis();
			if (choice == 1) {
				System.out.println(top);
				leastPopulous();
				System.out.println("\n");
				System.out.print("Elasped Time ");
				System.out.print(endMillisec-startMillisec);
				System.out.print(" milliseconds");
			}
			else if (choice == 2) {
				System.out.println(top);
				mostPopulous();
				System.out.println("\n");
				System.out.print("Elasped Time ");
				System.out.print(endMillisec-startMillisec);
				System.out.print(" milliseconds");
			}
			
			else if (choice == 3) {
				System.out.println(top);
				firstName();
				System.out.println("\n");
				System.out.print("Elasped Time ");
				System.out.print(endMillisec-startMillisec);
				System.out.print(" milliseconds");
			}
			
			else if (choice == 4) {
				System.out.println(top);
				lastName();
				System.out.println("\n");
				System.out.print("Elasped Time ");
				System.out.print(endMillisec-startMillisec);
				System.out.print(" milliseconds");
			}
			
			else if (choice == 5) {
				while (!valid) {
					prefer = Prompt.getString("Enter state name (ie. Alabama) ");
					if (checkInput(prefer)) {
						System.out.println("\n");
						valid = true;
						System.out.println(top);
						mostPopulousByState(prefer);
					}
					else {
						System.out.println("ERROR: "+prefer+" is not valid");
					}
				}
			}
			else if (choice == 6) {
				while (!valid) {
					prefer = Prompt.getString("Enter City Name");
					if (checkCityInput(prefer)) {
						System.out.println("\n");
						valid = true;
						System.out.println(top);
						mostPopulousByCity(prefer);
					}
					else
					System.out.println("ERROR: "+prefer+" is not valid");
				}
			}
			else if (choice == 9) {
				index=0;
				System.out.println("\n");
				System.out.print("Thank you for using population!");
			}
			System.out.println("\n");
		}
	}
	/**
	 * checks if the input is a valid state
	 * @param prefer
	 * @return
	 */
	public boolean checkInput(String prefer) {
		Scanner input = FileUtils.openToRead(DATA_FILE);
		input.useDelimiter("[\t\n]");
		while (input.hasNextLine()) {
			String state = input.next(); 
			input.nextLine();
			if (prefer.equals(state))
			return true;
		}
		input.close();
		return false;
	}
	public boolean checkCityInput(String prefer) {
		Scanner input = FileUtils.openToRead(DATA_FILE);
		input.useDelimiter("[\t\n]");
		while (input.hasNextLine()) {
			String state = input.next(); 
			String city = input.next();
			input.nextLine();
			if (prefer.equals(city))
			return true;
		}
		input.close();
		return false;
	}
	/**
	 * adds that particular states city objects into a new arraylist, then calls
	 * old merge sort method to sort by population
	 * @param state2	the state that was inputed by user
	 * @return void
	 */
	public void mostPopulousByState(String state2) {
		temps = new ArrayList<>(cities.size());
		List<City> particularState = new ArrayList<City>();
		for (int i = 0;i<cities.size();i++) {
			City currentCity = cities.get(i);
			if (currentCity.getState().compareTo(state2) == 0) {
				particularState.add(currentCity);
			}
		}
		endMillisec = System.currentTimeMillis();
		recursiveSort(particularState, 0, particularState.size()-1);
		if (particularState.size() > 50) {
			for (int i = 0;i<50;i++) {
				if (i < 9)
				System.out.println(" "+(i+1)+":"+"   "+particularState.get(i));
				else
				System.out.println(" "+(i+1)+":"+"  "+particularState.get(i));
			}
		}
		else {
			for (int i = 0;i<particularState.size();i++) {
				if (i < 9)
				System.out.println(" "+(i+1)+":"+"   "+particularState.get(i));
				else
				System.out.println(" "+(i+1)+":"+"  "+particularState.get(i));
			}
		}
	}
	public void mostPopulousByCity(String state2) {
		temps = new ArrayList<>(cities.size());
		List<City> particularCity = new ArrayList<City>();
		for (int i = 0;i<cities.size();i++) {
			City currentCity = cities.get(i);
			if (currentCity.getCity().compareTo(state2) == 0) {
				particularCity.add(currentCity);
			}
		}
		endMillisec = System.currentTimeMillis();
		recursiveSort(particularCity, 0, particularCity.size()-1);
		for (int i = 0;i<particularCity.size();i++) {
			if (i < 9)
			System.out.println(" "+(i+1)+":"+"   "+particularCity.get(i));
			else
			System.out.println(" "+(i+1)+":"+"  "+particularCity.get(i));
		}
	}
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> city, int x, int y) {
		City temp = city.get(x);
		city.set(x, city.get(y));
		city.set(y, temp);
	}
	/** 
	 * 	Sorts the fifty least populous cities in the USA using a selection sort
	 * 	@param none
	 * 	@return void
	 */
	public void leastPopulous() {
		for (int outer = cities.size()-1;outer>0;outer--) {
			int maxIndex = 0;
			for (int inner = 0;inner<=outer;inner++) {
				if (cities.get(inner).compareTo(cities.get(maxIndex)) > 0) {
					maxIndex = inner;
				}
			}
			swap(cities, maxIndex, outer);
		}
		endMillisec = System.currentTimeMillis();
		for (int i = 0;i<50;i++) {
			if (i < 9)
			System.out.println(" "+(i+1)+":"+"   "+cities.get(i));
			else
			System.out.println(" "+(i+1)+":"+"  "+cities.get(i));
		}
	}
	/** 
	 * 	Sorts the fifty most populous cities in the USA using a merge sort
	 * 	@param none
	 * 	@return void
	 */
	public void mostPopulous() {
        temps = new ArrayList<>(cities.size());
        recursiveSort(cities, 0, cities.size() - 1);
		endMillisec = System.currentTimeMillis();
		for (int i = 0;i<50;i++) {
			if (i < 9)
			System.out.println(" "+(i+1)+":"+"   "+cities.get(i));
			else
			System.out.println(" "+(i+1)+":"+"  "+cities.get(i));
		}
    }
	/**
	 * Uses recursion to split the list in half and so on until split size is two or less
	 * then swaps the final two objects if needed.
	 * @param cities
	 * @param from
	 * @param to
	 */
    public void recursiveSort(List<City> cities, int from, int to) {
        if (to - from < 2) {
            if (to > from && cities.get(to).compareTo(cities.get(from)) > 0) {
				swap(cities, to, from);
            }
        } 
		else {
            int middle = (from + to) / 2;
            recursiveSort(cities, from, middle);
            recursiveSort(cities, middle + 1, to);
            merge(cities, from, middle, to);
        }
    }
	/**
	 * merges the list back together in correct order by comparing city by city
	 * and adding to the new temps list.
	 * @param cities
	 * @param from
	 * @param middle
	 * @param to
	 */
    public void merge(List<City> cities, int from, int middle, int to) {
        int i = from, j = middle + 1, k = from;
        while (i <= middle && j <= to) {
            if (cities.get(i).compareTo(cities.get(j)) > 0) {
                temps.add(cities.get(i));
                i++;
            } 
			else {
                temps.add(cities.get(j));
                j++;
            }
            k++;
        }
        while (i <= middle) {
            temps.add(cities.get(i));
            i++;
            k++;
        }
        while (j <= to) {
            temps.add(cities.get(j));
            j++;
            k++;
        }
        for (k = from; k <= to; k++) {
            cities.set(k, temps.get(k - from));
        }
        temps.clear();
    }
	/** 
	 * Sorts the first 50 cities by name using insertion sort.
	 * @param none
	 * @return void
	 */
	public void firstName() {
		temps = new ArrayList<>(cities.size());
		recursiveSort(cities, 0, cities.size()-1);
		for (int outer = 1;outer<cities.size();++outer) {
			City temp = cities.get(outer);
			int inner = outer;
			while (inner > 0 && temp.getCity().compareTo(cities.get(inner-1).getCity()) < 0) {
				if ( temp.getCity().compareTo(cities.get(inner-1).getCity()) == 0) {
					if (temp.getPopulation() < cities.get(inner-1).getPopulation())
					cities.set(inner, cities.get(inner-1));
				}
				else
				cities.set(inner, cities.get(inner-1));
				inner--;
			}
			cities.set(inner, temp);
		}
		endMillisec = System.currentTimeMillis();
		for (int i = 0;i<50;i++) {
			if (i < 9)
			System.out.println(" "+(i+1)+":"+"   "+cities.get(i));
			else
			System.out.println(" "+(i+1)+":"+"  "+cities.get(i));
		}
	}
	/**
	 * 	Sorts the last 50 cities in order by name by using merge sort.
	 * 	@param none
	 * 	@return void
	 */
	public void lastName() {
		temps = new ArrayList<>(cities.size());
		recursiveSort3(cities, 0, cities.size()-1);
        temps = new ArrayList<>(cities.size());
        recursiveSort2(cities, 0, cities.size() - 1);
		endMillisec = System.currentTimeMillis();
		for (int i = 0;i<50;i++) {
			if (i < 9)
			System.out.println(" "+(i+1)+":"+"   "+cities.get(i));
			else
			System.out.println(" "+(i+1)+":"+"  "+cities.get(i));
		}
    }
	/**
	 * Uses recursion to split the list in half and so on until split size is two or less
	 * then swaps the final two objects if needed. Sorts cities in alphabetical order 
	 * descending.
	 *
	 * @param cities
	 * @param from
	 * @param to
	 */
    public void recursiveSort2(List<City> cities, int from, int to) {
        if (to - from < 2) {
            if (to > from && cities.get(to).getCity().compareTo(cities.get(from).getCity()) > 0) {
                City temp = cities.get(to);
                cities.set(to, cities.get(from));
                cities.set(from, temp);
            }
        } else {
            int middle = (from + to) / 2;
            recursiveSort2(cities, from, middle);
            recursiveSort2(cities, middle + 1, to);
            merge2(cities, from, middle, to);
        }
    }
	/**
	 * Merges the list back together in correct order by comparing city by city
	 * and adding to the new temps list.
	 *
	 * @param cities
	 * @param from
	 * @param middle
	 * @param to
	 */
    public void merge2(List<City> cities, int from, int middle, int to) {
        int i = from, j = middle + 1, k = from;
        while (i <= middle && j <= to) {
            if (cities.get(i).getCity().compareTo(cities.get(j).getCity()) > 0) {
                temps.add(cities.get(i));
                i++;
            } else {
                temps.add(cities.get(j));
                j++;
            }
            k++;
        }
        while (i <= middle) {
            temps.add(cities.get(i));
            i++;
            k++;
        }
        while (j <= to) {
            temps.add(cities.get(j));
            j++;
            k++;
        }
        for (k = from; k <= to; k++) {
            cities.set(k, temps.get(k - from));
        }
        temps.clear();
	}
	/**
	 * Uses recursion to split the list in half and so on until split size is two or less
	 * then swaps the final two objects if needed.
	 * This sort sorts from least to greatest in terms of population.
	 *
	 * @param cities
	 * @param from
	 * @param to
	 */
	public void recursiveSort3(List<City> cities, int from, int to) {
        if (to - from < 2) {
            if (to > from && cities.get(to).compareTo(cities.get(from)) < 0) {
                swap(cities, to, from);
            }
        } else {
            int middle = (from + to) / 2;
            recursiveSort(cities, from, middle);
            recursiveSort(cities, middle + 1, to);
            merge(cities, from, middle, to);
        }
    }

    public void merge3(List<City> cities, int from, int middle, int to) {
        int i = from, j = middle + 1, k = from;
        while (i <= middle && j <= to) {
            if (cities.get(i).compareTo(cities.get(j)) < 0) {
                temps.add(cities.get(i));
                i++;
            } else {
                temps.add(cities.get(j));
                j++;
            }
            k++;
        }
        while (i <= middle) {
            temps.add(cities.get(i));
            i++;
            k++;
        }
        while (j <= to) {
            temps.add(cities.get(j));
            j++;
            k++;
        }
        for (k = from; k <= to; k++) {
            cities.set(k, temps.get(k - from));
        }
        temps.clear();
    }

	public static void main(String [] args) {
		Population p1 = new Population();
		p1.run();
	}
}