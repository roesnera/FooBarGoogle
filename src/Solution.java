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
            System.out.println("Value of cell is: "+cell);
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
                    if(cell > smallestNegative){
                        smallestNegative = cell;
                        smallestNegativeI = i;
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

        System.out.println(
                "Smallest Absolute Value: "+smallestAbsVal+"\n"+
                "Smallest Absolute Value i: "+smallestAbsValI+"\n"+
                "Smallest Positive Value: "+smallestPositive+"\n"+
                "Smallest Positive Value i: "+smallestPositiveI+"\n"+
                "Smallest Negative Value: "+smallestNegative+"\n"+
                "Smallest Negative Value i: "+smallestNegativeI+"\n"+
                "Odd Negatives boolean: "+oddNegatives+"\n"+
                "Negative count: "+negatives+"\n"+
                "Indices of zero: "+zeroCells.toString()
        );

        if(!oddNegatives&&zeroCells.size()==0){
            prod = reduceOmitCells(smallestAbsValI, xs);
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

        /*
            this calls for a recursive method that checks
            whether the input array is better off ejecting the smallest positive or the smallest two negatives
            if it is better off with the former, return the reduced value
            if it is better off with the latter,
                return the procedure run again, passing in the product of the two negatives, and the smallest positive
            could be done with a while loop as well, but recursion is *probably* simpler logically
         */
        if(oddNegatives&&zeroCells.size()>0){
            int[] indicesToOmit = new int[zeroCells.size()+1];
            for (int i = 0; i < zeroCells.size(); i++) {
                indicesToOmit[i] = zeroCells.get(i);
            }
            indicesToOmit[zeroCells.size()] = smallestNegativeI;
            prod = reduceOmitCells(indicesToOmit, xs);
        }

        // what if the product of the two smallest negatives is lesser than the smallest positive?

        return ""+prod;
    }

    private static int reduceOmitCells(int indexToOmit, int[] arrayToReduce) {
        int prod = 1;
        for (int i = 0; i < arrayToReduce.length; i++) {
            if(i!=indexToOmit){
                prod *= arrayToReduce[i];
            }
        }
        return prod;
    }
    private static int reduceOmitCells(int[] indexToOmit, int[] arrayToReduce){
        int prod = 1;
        for(int ind : indexToOmit){
            arrayToReduce[ind] = 1;
        }
        for (int i = 0; i < arrayToReduce.length; i++) {
            prod *= arrayToReduce[i];
        }
        return prod;
    }
}