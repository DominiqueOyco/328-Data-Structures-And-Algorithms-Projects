//CECS 328 PROGRAMMING ASSIGNMENT 2
//Dominique Oyco - 014605758

import java.util.Arrays;
import java.util.Scanner;

public class ProgrammingAssignment2
{		
	/**
	 * Finds the square root of a number
	 * @param N
	 * @return mid - the square root of a number
	 */
	public static double sqrt(int N)
	{				
		//BABYLONIAN METHOD/NEWTONIAN METHOD OF SQUARE ROOTS: x_(n+1) = ((x_n + (number/x_n)) / 2)
		double x; 
		if (N == 0)
		{
	        return 0; //SQUARE ROOT OF ZERO IS EQUAL TO ZERO
		}
		
		else if (N < 0)
		{
			System.out.println("NOT POSSIBLE"); //A NEGATIVE SQUARE ROOT
		}
		
		else
		{
			double SQRT = N / 2.0; //DIVIDES THE USER GENERATED VALUE TO TWO		 
			do
			{
				x = SQRT; //STORE THE ORIGINAL VALUE OF THE SQRT TO X
				SQRT = (x + (N / x)) / 2; //ADDS THE QUOTIENT OF THE ORIGINAL NUMBER (N) WITH THE VALUE OF SQRT (X) TO X AND DIVIDES IT BY 2 AND STORE IT TO THE SQRT;
			}while ((x - SQRT) != 0); //DO ALL OF THIS WHILE THE DIFFERENCE OLD SQRT AND THE NEW SQRT IS NOT EQUAL TO 0
		 
			return SQRT;
		}
		return -1; 
	}
	
	/**
	 * Searches for the value of inside of an array using binary search algorithm
	 * @param a - the integer array
	 * @param key - the value to be searched inside the array
	 * @return the position of the array or -1 if the value is not inside of the array 
	 */
	public static int checkPosition(int[] a, int first, int last, int key)
	{		
		if(first <= last) //first element in the array not equal to the length of the array
		{			
			int mid = (first + last) / 2;	//DIVIDES THE ARRAY INTO TWO
			
			if(a[mid - 1] == key)		
			{
				return checkPosition(a, first, mid - 1, key); //IF KEY IS FOUND ON THE LEFT SIDE OF THE LAST MARKER TO THE LEFT OF MID
			}
			
			if(a[mid - 1] == 0 && a[mid] == key) 			
			{
				return mid; //IF THE MIDDLE AND RIGHT SIDE OF THE ARRAY IS THE KEY AND THE LEFT SIDE IS 0, RETURN THE MID
			}
			
			if(a[mid] == 0)
			{
				return checkPosition(a, mid + 1, last, key); //IF KEY IS NOT FOUND ON THE LEFT SIDE OR MID, SET THE FIRST MARKER TO THE RIGHT OF MID
			}
		}
		return -1;
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter a number to find the square root: ");
		int N = scanner.nextInt();
		
		double result = Math.ceil(sqrt(N));
		System.out.println(result);
		
		
		System.out.println("-------------------------------------------------");
		System.out.println("*************************************************");
		System.out.println("-------------------------------------------------");
        System.out.println("Enter the size of the binary array: ");
        int size = scanner.nextInt();
        
        int[] a = new int[size]; //THE BINARY ARRAY
        
		System.out.println("\nPlease enter a binary digit. Must start with a 0: ");
		
        for(int i = 0; i < a.length; i++)
        {
        	a[i] = scanner.nextInt();  
        }
        
        if(a[0] != 0)
        {
        	System.out.println("MUST START WITH A ZERO"); //NEED TO START WITH 0 FROM INDEX O TO INDEX N
        }
        
        else
        {
            int key = 1;    
            int separator = checkPosition(a, 0, a.length - 1, key); //FINDS THE LOCATION OF 1 INSIDE THE ARRAY

    		System.out.println(Arrays.toString(a));
    		System.out.println("The position that separates 0 and 1 is: " + separator);
        }	
	}
}