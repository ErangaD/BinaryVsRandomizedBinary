/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment02;
import java.util.Random;
/**
 *
 * @author Eranga
 */
public class Assignment02 {
    

    static Random rand = new Random();

    int  n = rand.nextInt(50) + 1;
    /**
     * @param args the command line arguments
     */
    
    static int getRandom(int x, int y)
    {
        return (x + rand.nextInt() % (y-x+1));
    }

    // A recursive randomized binary search function.
    // It returns location of x in
    // given array arr[l..r] is present, otherwise -1
    static int randomizedBinarySearch(int arr[], int l,
                                int r, int x)
    {
        if (r >= l)
        {
            // Here we have defined middle as
            // random index between l and r ie.. [l, r]
            int mid = getRandom(l, r);

            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
              return randomizedBinarySearch(arr, l,
                                        mid-1, x);

            // Else the element can only be present
            // in right subarray
            return randomizedBinarySearch(arr, mid+1,
                                             r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
    // Returns index of x if it is present in arr[l..
    // r], else return -1
    static int binarySearch(int arr[], int l, int r, int x)
    {
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            // If the element is present at the 
            // middle itself
            if (arr[mid] == x)
               return mid;
 
            // If element is smaller than mid, then 
            // it can only be present in left subarray
            if (arr[mid] > x)
               return binarySearch(arr, l, mid-1, x);
 
            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid+1, r, x);
        }
 
        // We reach here when element is not present
        //  in array
        return -1;
    }
    
    static int[] getSortedArray(int arraySize) {
        int[] array = new int[arraySize];
        for(int i=0; i<arraySize; i++) {
            array[i] = i;
        }
        return array;
    }
 
    static int[] getNumbersWithinArray(int limit, int size) {
        int[] array = new int[size];
         for(int i=0; i<size; i++) {
            array[i] = rand.nextInt(limit);
        }
        return array;
    }
    
    static int[] getNumbersOutsideArray(int upperLimit, int size) {
        int[] array = new int[size];
         for(int i=0; i<size; i++) {
            array[i] = rand.nextInt(upperLimit) + upperLimit;
        }
        return array;
    }
    
    // Driver method to test above
    public static void main(String args[])
    {
        int[] arraySizes = {100000, 1000000, 10000000};
        
        for(int N: arraySizes) {
            
            int numberOfTimes = 10000;
            long totalTimeInMiSecondBinary = 0;
            long totalTimeInMiSecondRandomized = 0;
            int arr[] = getSortedArray(N);
            int n = arr.length;
            for (int times=0; times < numberOfTimes; times++) {
               
                int[] valuesToSearchWithinArray = getNumbersWithinArray(N, 700);
                int[] valuesToSearchOutsideArray = getNumbersOutsideArray(N, 300);
                long startOutsideSearch = System.nanoTime();
                for(int x: valuesToSearchOutsideArray) {
                    binarySearch(arr, 0, n-1, x);
                }
                long finishOutsideSearch = System.nanoTime();

                long startWithinSearch = System.nanoTime();
                for(int x: valuesToSearchWithinArray) {
                    binarySearch(arr, 0, n-1, x);
                }
                long finishWithinSearch = System.nanoTime();

                long totalSearchTime = (finishOutsideSearch - startOutsideSearch) +
                                        (finishWithinSearch - startWithinSearch);
                totalTimeInMiSecondBinary += totalSearchTime;
                
                // randomized search start
                startOutsideSearch = System.nanoTime();
                for(int x: valuesToSearchOutsideArray) {
                    binarySearch(arr, 0, n-1, x);
                }
                finishOutsideSearch = System.nanoTime();

                startWithinSearch = System.nanoTime();
                for(int x: valuesToSearchWithinArray) {
                    binarySearch(arr, 0, n-1, x);
                }
                finishWithinSearch = System.nanoTime();

                totalSearchTime = (finishOutsideSearch - startOutsideSearch) +
                                        (finishWithinSearch - startWithinSearch);
                totalTimeInMiSecondRandomized += totalSearchTime;
            }
            
            System.out.println("Average Binary Search time for N = " + N + "   is  " 
                                + totalTimeInMiSecondBinary/(1000 * numberOfTimes));
            System.out.println("Average Randomized Binary Search time for N = " + N + "   is  " 
                                + totalTimeInMiSecondRandomized/(1000 * numberOfTimes));
            
            
            
        }
        
        
    }
    
}
