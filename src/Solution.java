import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {

        int[] numsArr = new int[47];

        for(int i = -23; i<23; i++){
            numsArr[i+23] = i;
        }

        solution(numsArr);
    }



    public static String solution(int[] xs) {
        // Your code here

        // there's two problems here
        // determining if it is possible to use an even number of, or zero, negative numbers
        // finding the highest multiple given the above

        int size = xs.length;
        String ansStr = "";
        int prod = 1;

        // to assess the situation regarding presence of negatives
        boolean oddNegatives = false;
        int negatives = 0;

        // to track smallest values, abs, pos, and neg
        int smallestAbsVal = 0;
        int smallestNegative = 0;
        int smallestPositive = 0;

        // to track smallest indices
        int smallestAbsValI = -1;
        int smallestNegativeI = -1;
        int smallestPositiveI = -1;

        // need to create arraylist to track indices of zero cells
        ArrayList<Integer> zeroCells = new ArrayList<Integer>();


        for (int i = 0; i < size; i++) {
            int cell = xs[i];
            System.out.println("Value of cell is: " + cell);
            if (cell != 0) {
                if (smallestAbsVal == 0) {
                    smallestAbsVal = Math.abs(cell);
                    smallestAbsValI = i;
                } else if (Math.abs(cell) < smallestAbsVal) {
                    smallestAbsVal = Math.abs(cell);
                    smallestAbsValI = i;
                }
                if (cell < 0) {
                    oddNegatives = !oddNegatives;
                    negatives++;
                    if (smallestNegative == 0) {
                        smallestNegative = cell;
                        smallestNegativeI = i;
                    }
                    if (cell > smallestNegative) {
                        smallestNegative = cell;
                        smallestNegativeI = i;
                    }
                } else {
                    if (smallestPositive == 0) {
                        smallestPositive = cell;
                        smallestPositiveI = i;
                    }
                    if (cell < smallestPositive) {
                        smallestPositive = cell;
                        smallestPositiveI = i;
                    }
                }
            } else {
                zeroCells.add(i);
            }
        }

        System.out.println(
                "Smallest Absolute Value: " + smallestAbsVal + "\n" +
                        "Smallest Absolute Value i: " + smallestAbsValI + "\n" +
                        "Smallest Positive Value: " + smallestPositive + "\n" +
                        "Smallest Positive Value i: " + smallestPositiveI + "\n" +
                        "Smallest Negative Value: " + smallestNegative + "\n" +
                        "Smallest Negative Value i: " + smallestNegativeI + "\n" +
                        "Odd Negatives boolean: " + oddNegatives + "\n" +
                        "Negative count: " + negatives + "\n" +
                        "Indices of zero: " + zeroCells.toString()
        );

        if(zeroCells.size()==xs.length){
            return "0";
        }
        
        int newListLength = xs.length-zeroCells.size();
        
        int[] noZerosList = new int[newListLength];
        int noZerosIndex = 0;

        for (int i = 0; i < xs.length; i++) {
            if(!zeroCells.contains(i)){
                noZerosList[noZerosIndex] = xs[i];
                noZerosIndex++;
            }
        }

        if(reduceList(noZerosList).max(new BigInteger("0"))!=new BigInteger("0")){
            return ""+reduceList(noZerosList);
        }

        BigInteger largestProduct = new BigInteger("1");

        // on each level, check the total for one index removed against largestProduct, build a layer inside that removes another index and checks the reduced total

        if(noZerosList.length>=3){
            int len = noZerosList.length;
            int numCombos = len*(len-1)*(len-2);
            for (int i = 0; i < numCombos; i++) {
                int[] lvl1Arr = new int[len-1];

                BigInteger total1Lvl =
            }
        } else if (noZerosList.length==2){

        } else if (noZerosList.length==1){

        }

        return ansStr;
    }

    private static BigInteger reduceList(int[] List) {
        BigInteger bigInteger = new BigInteger("1");
        for( int entry : List){
            bigInteger = bigInteger.multiply(new BigInteger(""+entry));
        }
        return bigInteger;
    }
}