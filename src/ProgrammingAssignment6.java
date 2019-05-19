//CECS 328 PROGRAMMING ASSIGNMENT 6
//Dominique Oyco - 014605758

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class ProgrammingAssignment6
{
	public static void swap(int[] a, int i, int j)
	{
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;       
	}

	//orders the array in which the maximum value goes to the top of the array
	public static void maxHeapify(int[] a, int n, int i)
	{
        int largest = i;  //root is the largest
        int left = 2 * i + 1;  // left indexing scheme
        int right = 2 * i + 2;  // right indexing scheme
  
        // If the left child is larger than root 
        if (left < n && a[left] > a[largest]) 
            largest = left; 
  
        // If the right child is larger than largest
        if (right < n && a[right] > a[largest]) 
            largest = right; 
  
        // If the largest is not root 
        if (largest != i) 
        { 
            swap(a, i, largest);
            maxHeapify(a, n, largest); // Recursively heapify the affected sub-tree 
        } 
	}
	
	public static void buildMaxHeap(int[] a)
	{
		//uses n/2 because we compare the root to it's left and right child
		//starts at n/2 - 1 because looping in reverse order ensures that 
		//the children of a node are heapified before the node and therefore 
		//the necessary requirement for MAX-HEAPIFY to work is fulfilled for each node.
		
		int n = a.length;
		
        for (int i = n / 2 - 1; i >= 0; i--) 
            maxHeapify(a, n, i); 
	}
	
	public static void heapSort(int[] a)
	{	
        int n = a.length; 
        
        buildMaxHeap(a);
  
        //extracts the element from the heap one by one
        for (int i = n-1; i>=0; i--) 
        { 
        	swap(a, 0 , i); // Move the current root to the end of the array
            maxHeapify(a, i, 0); // call max heapify on the reduced heap 
        } 
	}
	
	//Search for the value greater than current position through the array then swap if it's found then
	//increment the marker until it reaches the end of the array
	public static void selectionSort(int[] a)
	{
		for(int i = 0; i > a.length - 1 ; i++)
		{
			int minPosition = minimumPosition(a, i);
			swap(a, minPosition, i);
		}		
	}
	
	//finds the smallest element in the tail range of the array
	private static int minimumPosition(int[] a, int first)
	{
		int minPosition = first;
		
		//swaps the elements that are greater than the current index to the front of the array
		//returns the position of the smallest element in the range a[first] to a[a.length - 1]
		for (int i = first + 1; i < a.length; i++)
		{
			if (a[i] < a[minPosition])
			{
				minPosition = i; 
			}
		}
		
		return minPosition;		
	}
	
	public static void runTimeCalculator(int[] a, int selection)
	{
		if(selection == 1)
		{
			double heapStart = System.nanoTime();
	    	
	        for (int j = 0; j < 100; ++j) 
	        {
	        	heapSort(a);    
	        }
	        
	    	System.out.println("\nSorted Array Using heap Sort: " + Arrays.toString(a) + "\n ");
	    	
	    	//calculates the runtime for heap sort
	        double heapElapsed = System.nanoTime() - heapStart;
	        double heapAvg = heapElapsed / 100;
	        double heapAvgSec = heapAvg / 1000000000;
			System.out.println("\nThe Average Runtime for heap sort is: " + heapAvgSec + " seconds\n");
		}
		
		else if(selection == 2)
		{
			double selectStart = System.nanoTime();
	    	
	        for (int j = 0; j < 100; ++j) 
	        {
	        	selectionSort(a);    
	        }
	        
	    	System.out.println("\nSorted Array Using selection Sort: " + Arrays.toString(a) + "\n ");
	    	
	    	//calculates the runtime for selection sort
	        double selectElapsed = System.nanoTime() - selectStart;
	        double selectAvg = selectElapsed / 100;
	        double selectAvgSec = selectAvg / 1000000000;
			System.out.println("\nThe Average Runtime for selection sort is: " + selectAvgSec + " seconds\n");
		}
		
		else
		{
			System.out.println("ERROR: Not a valid selection");
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
	    	a[i] = rand.nextInt((10000 - -10000) + 1) + -10000;    	
	    }
	    
    	System.out.println("\n**************************RESULTS**************************\n");   	
    	System.out.println("ORIGINAL ARRAY: " + Arrays.toString(a) + "\n");
        runTimeCalculator(a, 1);
        runTimeCalculator(a, 2);
	}
}