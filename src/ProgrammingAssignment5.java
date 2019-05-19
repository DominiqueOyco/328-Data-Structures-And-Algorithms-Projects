//CECS 328 PROGRAMMING ASSIGNMENT 5
//Dominique Oyco - 014605758

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class ProgrammingAssignment5
{		
	//RUNNING TIME: O(n)
	public static int nMSS(int[] a)
	{
		int n = a.length;
		int maxSum = 0;
		int sum = 0;
		
		//
		for(int i = 0; i < n; i++)
		{
			sum += a[i];
			
			if(sum > maxSum)
			{
				maxSum = sum;
			}

			if(sum < 0)
			{
				sum = 0;
			}
		}
		
		return maxSum;
	}
	
	//RUNNING TIME: O(nlogn)
	public static int nLogMSS(int[] b, int first, int last)
	{
		if(last == first)
		{
			return b[first]; //because there is only one value
		}
		
		if(last == first + 1) //if last = b[1], find the mss (mss = max(first, last, maxfirst+maxlast) because there at least 2 values on the array
		{
			return Integer.max(Integer.max(b[first], b[last]), b[first] + b[last]);
		}
		
		int mid = (first + last) / 2; //divide the array in half
		
		int mssLeft = nLogMSS(b, first, mid); //the mss left is in the left side of the divided array (0 to mid)
		int mssRight = nLogMSS(b, mid + 1, last); //the mss right is in the right side of divided the array (mid to n-1)
		int mssMid = nLogMSSCrossing(b, first, mid, last); //if the mss is in the elements in the middle of the divided array. Need to cross!
		
		return Integer.max(Integer.max(mssLeft, mssRight), mssMid);
	}
	
	//Function that finds the crossing subarray for the recursive nLogMSS
	public static int nLogMSSCrossing(int[] b, int first, int mid, int last)
	{
		//Initializing first sum to smallest int to avoid cases where first sum doesn't have a value yet
		int firstSum = Integer.MIN_VALUE; 
		int lastSum = Integer.MIN_VALUE; //same reason as firstSum
		int sum = 0;
		
		for (int i = mid; i >= first; i--)
		{
			sum += b[i];
			
			if (sum > firstSum)
			{
				firstSum = sum;
			}
		}
		
		sum = 0;
		
		for (int j = mid + 1; j <= last; j++)
		{
			sum += b[j];
			
			if (sum > lastSum)
			{
				lastSum = sum;
			}
		}
		
		return firstSum + lastSum;
	}
	
	//testing the runtime to determine which function has a runtime of O(n) or O(nlogn)
	public static void runTimeCalculator(int[] a, int[] b, int selection)
	{
		if(selection == 1)
		{
			double nStart = System.nanoTime();
			
        	nMSS(a);    
	    	
	    	//calculates the runtime for nMSS
	        double nElapsed = System.nanoTime() - nStart;
	        double nAvg = nElapsed / 100;
	        double nAvgSec = nAvg / 1000000000;
			System.out.println("\nThe Average Runtime for nMSS is: " + nAvgSec + " seconds\n");
		}
		
		else if(selection == 2)
		{
	        double nLogStart = System.nanoTime();

        	nLogMSS(b, 0, b.length - 1);    
	    	
	    	//calculates the runtime for nLogMSS
	        double nLogElapsed = System.nanoTime() - nLogStart;
	        double nLogAvg = nLogElapsed / 100;
	        double nLogAvgSec = nLogAvg / 1000000000;
			System.out.println("\nThe Average Runtime for quick sort is: " + nLogAvgSec + " seconds\n");
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
	    	a[i] = rand.nextInt((100 - -100) + 1) + -100;    	
	    }
	    
	    int[] b = Arrays.copyOf(a, n);
		
		int N = nMSS(a);
		int NLOG = nLogMSS(b, 0, n - 1);
		
		System.out.println("\nOriginal Array: " + Arrays.toString(a));
		System.out.println("\nMSS of the nMSS function with a runtime of O(n) is: " + N);
    	runTimeCalculator(a, b, 1);
    	
		System.out.println("\nMSS of the nlogn MSS function with a runtime of O(nlogn) is: " + NLOG);
    	runTimeCalculator(a, b, 2);
	}
}