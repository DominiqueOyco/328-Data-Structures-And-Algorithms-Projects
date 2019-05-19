//CECS 328 PROGRAMMING ASSIGNMENT 1
//Dominique Oyco - 014605758

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
	
public class AverageCaseSearcher
{
	/**
	 * Searches for the value of inside of an array using linear search algorithm
	 * @param a - the integer array
	 * @param key - the value to be searched inside the array
	 * @return the position of the array or -1 if the value is not inside of the array 
	 */
	public static int linSearch(int[] a, int key)
	{
		for(int i = 0; i < a.length; i++)
		{
			if (a[i] == key)
			{
				return i;
			}
		}
  		return -1;		
	}
	
	/**
	 * Searches for the value of inside of an array using binary search algorithm
	 * @param a - the integer array
	 * @param key - the value to be searched inside the array
	 * @return the position of the array or -1 if the value is not inside of the array 
	 */
	public static int binSearch(int[] a, int first, int last, int key)
	{	
		if(first <= last)
		{
			int mid = (first + last) / 2; //Divides the beginning of the array and the length of the array into 2
			
			if (a[mid] == key)
			{
				return mid; 
			}
			
			else if (a[mid] > key) //IF THE INDEX(WHICH IS THE MID) OF ARRAY A IS GREATER THAN THE KEY
			{
				return last = mid - 1; //RETURNS THE LENGTH OF THE ARRAY WHICH IS THE DECREMENTED MID
			}
			
			else //IF THE INDEX(WHICH IS THE MID) OF ARRAY A IS LESS THAN THE KEY
			{
				return first = mid + 1; //RETURNS THE FIRST = INCREMENTED MID		
			}
		}
		return -1;	
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
		
    	Arrays.sort(a);
    	
        //PART A
        double linStart = System.nanoTime();
        for (int j = 0; j < 100; ++j) 
        {
            int key = rand.nextInt((1000 - -1000) + 1) + -1000;
            linSearch(a, key);      
        }
        
        double linElapsed = System.nanoTime() - linStart;
        double linAvg = linElapsed / 100;
        double linAvgSec = linAvg / 1000000000;
		System.out.println("\nThe Average Runtime for linear search is: " + linAvgSec + " seconds\n");
		
	
        double binStart = System.nanoTime();
        for (int k = 0; k < 100; ++k) 
        {
            int key = rand.nextInt((1000 - -1000) + 1) + -1000;
        	binSearch(a, 0, a.length, key);
      
        }        
        double binElapsed = System.nanoTime() - binStart;
        double binAvg = binElapsed / 100;
        double binAvgSec = binAvg / 1000000000;
		System.out.println("\nThe Average Runtime for binary search is: " + binAvgSec + " seconds\n");
		
		
		//-------------------------------------------------------------------------------//
		//PART B
		    
        int key = 5000;       
        linSearch(a, key);    
    	binSearch(a, 0, a.length, key);          

        double machineTime = Math.pow(10, -7);
        System.out.println("\nThe machine time is: " + machineTime + " seconds\n");
        
        double linWorstCase = (machineTime * Math.pow(10, 7));
        double binWorstCase = (machineTime * Math.log(Math.pow(10, 7)));
    	
		System.out.println("\nThe Worst Case Runtime for linear search is: " + linWorstCase + " seconds\n");
		System.out.println("\nThe Worst Case Runtime for binary search is: " + binWorstCase + " seconds\n");
	}
}
