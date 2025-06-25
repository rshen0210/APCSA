/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Ryan Shen
 *	@since	11/30/23
 */
public class SortMethods {
	Integer [] temps;
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		for (int outer = arr.length-1;outer>0;outer--) {
			for (int inner = 0;inner<outer;++inner) {
				if (arr[inner].compareTo(arr[inner+1]) > 0) { // compareTo compares objects
					swap(arr, inner, inner+1);
				}
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		for (int outer = arr.length-1;outer>0;outer--) {
			int maxIndex = 0;
			for (int inner = 0;inner<=outer;inner++) {
				if (arr[inner].compareTo(arr[maxIndex]) > 0) {
					maxIndex = inner;
				}
			}
			swap(arr, maxIndex, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for (int outer = 1;outer<arr.length;++outer) {
			Integer temp = arr[outer];
			int inner = outer;
			while (inner > 0 && temp < arr[inner-1]) {
				arr[inner] = arr[inner-1];
				inner--;
			}
			arr[inner] = temp;
			
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
		int n = arr.length;
		temps = new Integer [n];
		recursiveSort(arr, 0, n-1);
	}
	public void recursiveSort(Integer [] arr, int from, int to) {
		if (to - from < 2) {
			if (to > from && arr[to] < arr[from]) {
				Integer temp = arr[to]; arr[to] = arr[from]; arr[from] = temp;
			}
		}
		else
		{
			int middle = (from + to) / 2;
			recursiveSort(arr, from, middle);
			recursiveSort(arr, middle+1, to);
			merge(arr, from, middle, to);
		}
	}
	public void merge(Integer [] arr, int from, int middle, int to) {
		int i = from, j = middle + 1, k = from;
		while (i <= middle && j <= to) {
			if (arr[i] < arr[j]) {
				temps[k] = arr[i];
				i++;
			}
			else
			{
				temps[k] = arr[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			temps[k] = arr[i];
			i++;
			k++;
		}
		while (j<= to) {
			temps[k] = arr[j];
			j++;
			k++;
		}
		for (k = from;k <= to; k++) {
			arr[k] = temps[k];
		}
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}
}
