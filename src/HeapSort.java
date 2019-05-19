// Java program for implementation of QuickSort 
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HeapSort 
{ 
	public static void swap(int[] a, int i, int j)
	{
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;       
	}
	
	public static void buildHeap(int arr[])
	{
		int n = arr.length;
		
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
	}
	
    public static void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        buildHeap(arr);
  
        // One by one extract an element from heap 
        for (int i=n-1; i>=0; i--) 
        { 
            // Move current root to end 
        	swap(arr, 0 , i);
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    static void heapify(int arr[], int n, int i) 
    { 
        int largest = i;  // Initialize largest as root 
        int l = 2*i + 1;  // left = 2*i + 1 
        int r = 2*i + 2;  // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            swap(arr, i, largest);
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 
    
	public static void runTimeCalculator(int[] arr, int selection)
	{
		if(selection == 1)
		{
			double heapStart = System.nanoTime();
	    	
	        for (int j = 0; j < 100; ++j) 
	        {
	        	sort(arr);    
	        }
	        
	    	System.out.println("\nSorted Array Using heap Sort: " + Arrays.toString(arr) + "\n ");
	    	
	    	//calculates the runtime for heap sort
	        double heapElapsed = System.nanoTime() - heapStart;
	        double heapAvg = heapElapsed / 100;
	        double heapAvgSec = heapAvg / 1000000000;
			System.out.println("\nThe Average Runtime for heap sort is: " + heapAvgSec + " seconds\n");
		}
		
//		else if(selection == 2)
//		{
//			double selectStart = System.nanoTime();
//	    	
//	        for (int j = 0; j < 100; ++j) 
//	        {
//	        	selectionSort(a);    
//	        }
//	        
//	    	System.out.println("\nSorted Array Using selection Sort: " + Arrays.toString(a) + "\n ");
//	    	
//	    	//calculates the runtime for selection sort
//	        double selectElapsed = System.nanoTime() - selectStart;
//	        double selectAvg = selectElapsed / 100;
//	        double selectAvgSec = selectAvg / 1000000000;
//			System.out.println("\nThe Average Runtime for selection sort is: " + selectAvgSec + " seconds\n");
//		}
		
		else
		{
			System.out.println("ERROR: Not a valid selection");
		}
	}
  
    // Driver program 
    public static void main(String args[]) 
    { 
//        int arr[] = {12, 11, 13, 5, 6, 7}; 
//        int n = arr.length; 
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter a positive integer: ");
	    int n = scanner.nextInt();
	    
	    int[] arr = new int[n];
	    
	    Random rand = new Random();
	    
	    for(int i = 0; i < arr.length; i++)
	    {
	    	arr[i] = rand.nextInt((10000 - -10000) + 1) + -10000;    	
	    }
  
        System.out.println("Original Array: " + Arrays.toString(arr));
        //HeapSort ob = new HeapSort(); 
        sort(arr); 
        runTimeCalculator(arr, 1);
    } 
} 
/*This code is contributed by Rajat Mishra */
