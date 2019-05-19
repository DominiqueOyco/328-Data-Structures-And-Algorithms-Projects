//CECS 328 PROGRAMMING ASSIGNMENT 4
//Dominique Oyco - 014605758

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ProgrammingAssignment4
{		
	/**
	 * swap the elements inside the array
	 * @param a - the integer that contains the elements inside the array
	 * @param first - the beginning element of the array
	 * @param last - the last element of the array
	 */
	public static void swap(int[] a, int i, int j)
	{
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;       
	}
	
	/**
	 * finds the median of the first, middle, and last elements in an array
	 * @param b - the array to find the median for
	 * @return the media of three values
	 */
	public static int medianOfThree(int[] a, int first, int last)
	{
		
		int mid = (first + last) / 2;
		
		//first < mid < last OR first > mid > last
		if((a[first] < a[mid] && a[mid] < a[last]) || (a[first] > a[mid] && a[mid] > a[last]))
		{
			//return b[mid];
			return mid;
		}
		
		//(mid < first < last OR last > first > mid) OR (mid > first > last OR last < first < mid)
		else if((a[first] > a[mid] && a[first] < a[last]) || (a[first] < a[mid] && a[first] > a[last]))
		{
			//return b[first];
			return first;
		}
		
		//(first < last < mid OR mid > last > first) OR (first > last > mid OR mid < last < first)
		else if((a[first] < a[last] && a[mid] > a[last]) || (a[first] > a[last] || a[mid] < a[last]))
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
	 * divides the array into two arrays
	 * @param a - the array to be divided
	 * @param first - the beginning element of the array
	 * @param last - the final element of the array
	 * @return i + 1 - the index 
	 */	
	private static int partition(int[] a, int first, int last)
    {
		int median = medianOfThree(a, first, last);
		int pivot = a[median]; 
		swap(a, median, last);
   
	    int i = (first - 1);  //index where the left marker of the array begins
	    //determines the elements that goes to the left of the pivot
	    for (int j = first; j < last; j++) //j is the index inside the marker. Starts at b[0]
	    {
	        if (a[j] <= pivot)
	        {
	            i++;    // the left marker increments to the next element if b[j] <= pivot
	            swap(a, i, j); 
	            //swap the current position of the marker with the current position of the element inside
	            //to determine which elements are in the left side of the pivot. Once the pivot is reached, 
	            //the remaining elements are in the right half of the array
	        }
	    }	    
	    
	    //needs to swap the last element with the index of the marker + 1 b/c marker + 1 is the index of the pivot
	    swap(a, i + 1, last); 
	    return (i + 1); 
    }
	
	/**
	 * picks the pivot of the array randomly
	 * @param a - the array to be divided
	 * @param first - the beginning element of the array
	 * @param last - the final element of the array
	 * @return part - the pivot
	 */	
	private static int randPartition(int[] a, int first, int last)
    {
	    int n = last - first + 1;
	    int pivot = (int) (Math.random()) * n;
		swap(a, first + pivot, last);
	    return partition(a, first, last); 
    }
	
	public static int quickSelect(int[] a, int first, int last, int minKey) 
	{		
		if(first == last)
		{
			return a[first];
		}
		
		int part = partition(a, first, last); //returns the index of the pivot 
		
		int n = part - first + 1; //calculates the length of the left side of the array
		
		if(n == minKey)
		{
			return a[part]; //if the length is equal to the key, return the pivot using the index of part in the array
		}
		
		//if key is less than the length of the left side, set the last marker to the left of the pivot
		//of the array 
		else if (n > minKey) 
		{
			return quickSelect(a, first, part - 1, minKey);
		}
		
		//k-th element is somewhere in the right side of the array
		else
		{
			return quickSelect(a, part + 1, last, minKey - n); //remove the length to make sure that the k-th element is somewhere in the right
		}
	}
	
	public static int[] max(int[] a, int first, int last, int maxKey)
	{
		int [] k = new int[maxKey]; //new array that stores the max k elements. Size is the max key
		int max = a.length - maxKey; //length of the array starting from the max key key all the way from the last element
		
		int part = randPartition(a, first, last); //returns the index of the pivot 
		
		//int n = (last + 1) - part; //calculates the length of the right side of the array
		
		//if pivot = max, return k with the elements after the pivot
		if(part == max)
		{
			for(int i = 0; i < maxKey; i++)
			{
				k[i] = a[part + i];
			}
			return k; //if the length is equal to the key, return the pivot using the index of part in the array
		}		
		
		//if pivot < max, set the first marker to the right of the pivot and search for the k-th largest elements 
		else if (part < max) 
		{			
			return max(a, part + 1, last, maxKey); 
		}
		
		//if pivot > max, set the last marker to the left of the pivot and search for the k-th largest elements 
		else
		{
			return max(a, first, part - 1, maxKey);
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
        	a[i] = rand.nextInt((1000 - -1000) + 1) + -1000;    	
        }
		
    	//Arrays.sort(a);
    	System.out.println("ORIGINAL ARRAY: " + Arrays.toString(a) + "\n");
    	
    	System.out.println("Find the ?-th smallest element (? is equal from 1 to n) : ");
    	int minKey = scanner.nextInt();
    	
    	System.out.println("Find the ?-th largest elements (? is equal from 1 to n) : ");
    	int maxKey = scanner.nextInt();
    	
    	int qSelectFinder = quickSelect(a, 0, a.length - 1, minKey);
    	       
    	int[] b = max(a, 0, a.length - 1, maxKey);   
    	
    	System.out.println("\n**************************RESULTS**************************\n");    	
    	System.out.println("The kth smallest element on the array is " + qSelectFinder);
    	System.out.println("\n");    
    	

    	System.out.println("The kth largest elements on the array are " + Arrays.toString(b));
    	System.out.println("\n");    
	}	
}
