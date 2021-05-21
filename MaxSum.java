
/**
 * Find the maximum sum in a pyramid of numbers. You can only walk downwards 
 * or diagonally. You can only walk over non-prime numbers.
 *
 * @author Yasemin Akkaya
 * @version 5/20/21
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxSum
{
    private static int[][] pyramid = new int[15][15];
    
    /**
     * input file to 2D array
     * 
     * @param fname name of file which holds input
     */
    private static void readNums(String fname){
        try{
            InputStream instream = new FileInputStream(fname);
            Scanner scnr = new Scanner(instream);
            int index = 0;
            
            while(scnr.hasNextLine()){
                String s = scnr.nextLine();
                String[] nums = s.split("\\s+");
                
                for(int i = 0; i < nums.length; i++){
                    pyramid[index][i] = Integer.parseInt(nums[i]);
                }
                
                index++;
            }
            
        }
        catch (Exception e) {
            System.out.println("There was an error inputting the file");
            java.lang.System.exit(1);
        }
    
    }
    
    /**
     * Checks if a number is prime
     * 
     * @param num the number which is being checked
     */
    
    private static boolean isPrime(int num) {
        if(num == 1){
            return false;
        }

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * set all prime numbers in the array to 0
     * 
     */
    private static void primesToZero(){
        for(int r = 0; r < pyramid.length; r++){
            for(int c = 0; c < pyramid[0].length; c++){
                if(isPrime(pyramid[r][c])){
                    pyramid[r][c] = 0;
                }
            }
        }
    }
    
    /**
     * Find the max sum according to the rules defined in the problem. Checks numbers 
     * directly below and to the right to accumulate max into first pos of arr.
     *
     */
    private static void findMaxSum(){
        int largestColInd = pyramid[0].length - 1;
        for(int i = pyramid.length - 1; i > 0 ; i--){
            for(int j = 0; j < largestColInd; j++){
                if(pyramid[i][j] == 0 && pyramid[i][j+1] == 0){
                    pyramid[i-1][j] = 0;
                }
                else{
                    if(pyramid[i][j] > pyramid[i][j+1]){
                            pyramid[i-1][j] += pyramid[i][j];
                    }
                    else{
                            pyramid[i-1][j] += pyramid[i][j+1];
                    }
                }
            }
            largestColInd--;
        }
    }
    
    /**
     * print array
     */
    private static void printArr(){
        for(int r = 0; r < pyramid.length; r++){
            for(int c = 0; c < pyramid[0].length; c++){
                System.out.print(pyramid[r][c] + " ");
            }
            System.out.print("\n");
        }
    }
    
    public static void main(String[] args){
        //Fill the array with input
        readNums("/Users/yaseminakkaya/Desktop/test.txt");
        
        //Print original array
        System.out.println("The original array: ");
        printArr();
        
        //Set all primes to 0
        primesToZero();
        
        //Find max sum and store max in first position
        findMaxSum();
        
        //print result
        System.out.println("\nArray after max sum calculations: ");
        printArr();
        System.out.println("\n*********** The max sum is " + pyramid[0][0] + " ***********");
    }

}
