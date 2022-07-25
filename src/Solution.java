import java.util.ArrayList;
import java.util.Arrays;

// Use BigInteger for large number math
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {

        int[] numsArr = new int[6];

//        int[] numsArr = {-3976, 6871, 6663, 10381, -10875, 2270, -926, 12159, -6598, 4996};

        for(int i = 0; i<6; i++){
            numsArr[i] = (int) Math.floor(Math.random()*30000)-15000;
        }

        System.out.println(Arrays.toString(numsArr));

        System.out.println(solution(numsArr));

    }


/*
    Still not passing two tests
    I think there are two edge cases which it would not pass:
        if the product of the three smallest negative numbers (or 5, etc.) is smaller than the smallest positive
        in both odd and even numbered negative situations

    To work around this:
        track all the negatives vs all the positives and see
        if the smallest 2 or 3 negatives multiplied are larger or smaller than the smallest positive

        Have a function for each case that takes a positive value and an array of negatives
        returns the greater of the two inputs
        if it is the positive number, replace all negative sums with 1 before reducing
        else, replace positive num with 1
 */

    public static String solution(int[] xs) {
        // Your code here

        // there's two problems here
        // determining if it is possible to use an even number of, or zero, negative numbers
        // finding the highest multiple given the above

        int size = xs.length;
        String ansStr = "";
        BigInteger prod = new BigInteger("1");

        // to assess the situation regarding presence of negatives
        boolean oddNegatives = false;
        int negatives = 0;

        // to track smallest values, abs, pos, and neg
        int smallestAbsVal = 0;
        int smallestNegative = 0;
        int secondSmallestNegative = 0;
        int smallestPositive = 0;

        // to track smallest indices
        int smallestAbsValI = -1;
        int smallestNegativeI = -1;
        int secondSmallestNegativeI = -1;
        int smallestPositiveI = -1;

        // need to create arraylist to track indices of zero cells
        ArrayList<Integer> zeroCells = new ArrayList<Integer>();


        for (int i = 0; i < size; i++) {
            int cell = xs[i];
//            System.out.println("Value of cell is: "+cell);
            if(cell != 0) {
                if (smallestAbsVal == 0) {
                    smallestAbsVal = Math.abs(cell);
                    smallestAbsValI = i;
                } else if(Math.abs(cell)<smallestAbsVal) {
                    smallestAbsVal = Math.abs(cell);
                    smallestAbsValI = i;
                }
                if (cell < 0) {
                    oddNegatives = !oddNegatives;
                    negatives++;
                    if(smallestNegative==0){
                        smallestNegative = cell;
                        smallestNegativeI = i;
                    }
                    else if(secondSmallestNegative==0){
                        secondSmallestNegative = cell;
                        secondSmallestNegativeI = i;
                    }
                    if(cell > smallestNegative){
                        smallestNegative = cell;
                        smallestNegativeI = i;
                    }
                    else if (cell > secondSmallestNegative){
                        secondSmallestNegative = cell;
                        secondSmallestNegativeI = i;
                    }
                }
                else {
                    if(smallestPositive==0){
                        smallestPositive = cell;
                        smallestPositiveI = i;
                    }
                    if(cell < smallestPositive){
                        smallestPositive = cell;
                        smallestPositiveI = i;
                    }
                }
            } else {
                zeroCells.add(i);
            }
        }

        if(zeroCells.size()==xs.length){
            return "0";
        }
//        System.out.println(
//                "Smallest Absolute Value: "+smallestAbsVal+"\n"+
//                "Smallest Absolute Value i: "+smallestAbsValI+"\n"+
//                "Smallest Positive Value: "+smallestPositive+"\n"+
//                "Smallest Positive Value i: "+smallestPositiveI+"\n"+
//                "Smallest Negative Value: "+smallestNegative+"\n"+
//                "Smallest Negative Value i: "+smallestNegativeI+"\n"+
//                "Second Smallest Negative Value: "+secondSmallestNegative+"\n"+
//                "Second Smallest Negative Value i: "+secondSmallestNegativeI+"\n"+
//                "Odd Negatives boolean: "+oddNegatives+"\n"+
//                "Negative count: "+negatives+"\n"+
//                "Indices of zero: "+zeroCells.toString()
//        );


        /*
        * in the first case, check to see which is larger
        *   the product of the two smallest negatives
        *                   or
        *   the smallest positive integer
        * drop whichever is smaller from the array
        * */
        if(!oddNegatives&&zeroCells.size()==0){
            // what if sN and sSN are both 0?
            if(twoNegGreaterThanOnePos(smallestNegative,secondSmallestNegative,smallestPositive)){
                prod = reduceOmitCells(smallestPositiveI, xs);
            }
            else{
                int[] smallNegatives = {smallestNegativeI, secondSmallestNegativeI};
                prod = reduceOmitCells(smallNegatives, xs);
            }
        }
        if(oddNegatives&&zeroCells.size()==0){
            prod = reduceOmitCells(smallestNegativeI, xs);
        }
        if(!oddNegatives&&zeroCells.size()>0){
            int[] indicesToOmit = new int[zeroCells.size()];
            for (int i = 0; i < zeroCells.size(); i++) {
                indicesToOmit[i] = zeroCells.get(i);
            }
            prod = reduceOmitCells(indicesToOmit, xs);
        }

        if(oddNegatives&&zeroCells.size()>0){
            int[] indicesToOmit = new int[zeroCells.size()+1];
            for (int i = 0; i < zeroCells.size(); i++) {
                indicesToOmit[i] = zeroCells.get(i);
            }
            indicesToOmit[zeroCells.size()] = smallestNegativeI;
            prod = reduceOmitCells(indicesToOmit, xs);
        }

        // what if the product of the three smallest negatives is lesser than the smallest positive?

        return ""+prod;
    }

    private static BigInteger reduceOmitCells(int indexToOmit, int[] arrayToReduce) {
        BigInteger prod = new BigInteger("1");
        for (int i = 0; i < arrayToReduce.length; i++) {
            if(i!=indexToOmit){
                prod = prod.multiply(new BigInteger(""+arrayToReduce[i]));
            }
        }
        return prod;
    }
    private static BigInteger reduceOmitCells(int[] indexToOmit, int[] arrayToReduce){
        BigInteger prod = new BigInteger("1");
        for(int ind : indexToOmit){
            arrayToReduce[ind] = 1;
        }
        for (int i = 0; i < arrayToReduce.length; i++) {
            prod = prod.multiply(new BigInteger(""+arrayToReduce[i]));
        }
        return prod;
    }

    private static boolean twoNegGreaterThanOnePos(int neg1, int neg2, int pos){
        if(neg1 * neg2 < pos){
            return false;
        } else return true;
    }
}