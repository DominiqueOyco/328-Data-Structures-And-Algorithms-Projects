//CECS 328 PROGRAMMING ASSIGNMENT 3
//Dominique Oyco - 014605758

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ProgrammingAssignment3
{
	/**
	 * performs insertion sort to sort the array
	 * @param a - The array that contains the integer values
	 */
	public static void insertionSort(int[] a)
	{
		//starts at index 1 since index 0 (array of length 1) by itself is sorted
		for (int i = 1; i < a.length; i++)
		{
			int temp = a[i]; //prepares the next element to be sorted			
			
			int j = i; //j is equal to the original i
			
			//Compares the element of each indexes. If the element being checked is greater than 
			//the element on its left (element - 1), insert the current element before (element - 1)
			//starts with j > 0 b/c array of length is 1 is already sorted. 
			while (j > 0 && a[j - 1] > temp)
			{
				a[j] = a[j - 1]; 
				j--;  
			}
			
			// Inserting the element 
			a[j] = temp;
		}
	}
	
	/**
	 * finds the median of the first, middle, and last elements in an array
	 * @param b - the array to find the median for
	 * @return the media of three values
	 */
	public static int medianOfThree(int[] b, int first, int last)
	{		
		int mid = (first + last) / 2;
		
		/**
		 * Sorting on ascending order and returning the value in the middle
		 */
		
		//first < mid < last OR first > mid > last
		if((b[first] < b[mid] && b[mid] < b[last]) || (b[first] > b[mid] && b[mid] > b[last]))
		{
			//return b[mid];
			return mid;
		}
		
		//(mid < first < last OR last > first > mid) OR (mid > first > last OR last < first < mid)
		else if((b[first] > b[mid] && b[first] < b[last]) || (b[first] < b[mid] && b[first] > b[last]))
		{
			//return b[first];
			return first;
		}
		
		//(first < last < mid OR mid > last > first) OR (first > last > mid OR mid < last < first)
		else if((b[first] < b[last] && b[mid] > b[last]) || (b[first] > b[last] || b[mid] < b[last]))
		{
			//return b[last];
			return last;
		}
		
		else
		{
			return -1;
		}		
	}
	
	/**
	 * swap the elements inside the array
	 * @param a - the integer that contains the elements inside the array
	 * @param first - the beginning element of the array
	 * @param last - the last element of the array
	 */
	public static void swap(int[] b, int i, int j)
	{
        int temp = b[i];
        b[i] = b[j];
        b[j] = temp;       
	}
	
	/**
	 * divides the array into two arrays
	 * @param b - the array to be divided
	 * @param first - element at index 0
	 * @param last - element at index a.length - 1
	 * @return i + 1 - the index of the first partition
	 */	
	private static int partition(int[] b, int first, int last)
    {
		int median = medianOfThree(b, first, last);
		int pivot = b[median]; 
		swap(b, median, last);
   
	    int i = (first - 1);  //index where the left marker of the array begins
	    //determines the elements that goes to the left of the pivot
	    for (int j = first; j < last; j++) //j is the index inside the marker. Starts at b[0]
	    {
	        if (b[j] <= pivot)
	        {
	            i++;    // the left marker increments to the next element if b[j] <= pivot
	            swap(b, i, j); 
	            //swap the current position of the marker with the current position of the element inside
	            //to determine which elements are in the left side of the pivot. Once the pivot is reached, 
	            //the remaining elements are in the right half of the array
	        }
	    }	    
	    
	    //needs to swap the last element with the index of the marker + 1 b/c marker + 1 is the index of the 
	    //last element of the first partition
	    swap(b, i + 1, last); 
	    return (i + 1); 
    }
	
	/**
	 * 
	 * @param b - the array that contains the values to be sorted
	 * @param first - the beginning of the array
	 * @param last - the last element in the array
	 */
	public static void quickSort(int[] b, int first, int last)
	{
		//if first = last, there is only one element and thus no sorting is needed
		//if first > last, it is not valid since last is the length of the array
		if(first < last) 
		{
			int p = partition(b, first, last);	
			quickSort(b, first, p - 1); //sorts the element on the left side of the pivot
			quickSort(b, p + 1, last); //sorts the elements on the right side of the pivot
		}
	}
	
	/**
	 * Calls the quickSort function by passing in just one parameter
	 * @param b
	 */
	public static void quickSort(int[] b)
	{
		quickSort(b, 0, b.length - 1);
	}
	
	public static void runTimeCalculator(int[] a, int[] b, int selection)
	{
		if(selection == 1)
		{
			double insStart = System.nanoTime();
	        for (int j = 0; j < 100; ++j) 
	        {
	        	insertionSort(a);    
	        }
	    	System.out.println("\nSorted Array Using Insertion Sort: " + Arrays.toString(a) + "\n ");
	    	
	    	//calculates the runtime for insertion sort
	        double insElapsed = System.nanoTime() - insStart;
	        double insAvg = insElapsed / 100;
	        double insAvgSec = insAvg / 1000000000;
			System.out.println("\nThe Average Runtime for insertion sort is: " + insAvgSec + " seconds\n");
		}
		
		else if(selection == 2)
		{
	        double quickStart = System.nanoTime();
	        for (int j = 0; j < 100; ++j) 
	        {
	        	quickSort(b);    
	        }
	    	System.out.println("\nSorted Array Using Quick Sort: " + Arrays.toString(b) + "\n ");
	    	
	    	//calculates the runtime for quick sort
	        double quickElapsed = System.nanoTime() - quickStart;
	        double quickAvg = quickElapsed / 100;
	        double quickAvgSec = quickAvg / 1000000000;
			System.out.println("\nThe Average Runtime for quick sort is: " + quickAvgSec + " seconds\n");
		}
		
		else
		{
			System.out.println("ERROR");
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a positive integer: ");
        int n = scanner.nextInt();
        
        int[] a = new int[n];
        
        Random rand = new Random();
        
        for(int i = 0; i < a.length; i++)
        {
        	a[i] = rand.nextInt((5000 - -5000) + 1) + -5000;     
        } 
               
    	int[] b = Arrays.copyOf(a, n);
		double quickStart = System.nanoTime();
		insertionSort(b);
        double quickElapsed = System.nanoTime() - quickStart;
        
        double runOnce = quickElapsed / Math.pow(n, 2); //divide by n^2 because n^2 is the running time for quickSort

    	System.out.println("\n**************************RESULTS**************************\n");   	
    	System.out.println("ORIGINAL ARRAY: " + Arrays.toString(a) + "\n");
    	runTimeCalculator(a, b, 1);
    	System.out.println("\n");
    	   	
    	System.out.println("ORIGINAL ARRAY: " + Arrays.toString(b) + "\n");
    	runTimeCalculator(a, b, 2);
    	System.out.println("\n");
    	
    	System.out.println("It takes " + runOnce + " instructions in a second to run Quick Sort in my computer\n");
	}
}