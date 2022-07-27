import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] numsArr = new int[6];
        for (int i = 0; i < 6; i++) {
            numsArr[i] = (int) Math.floor(Math.random()*200)-100;
        }
        System.out.println(
                "For array: "+Arrays.toString(numsArr)+"\n" +
                "Solution calculated is: "+solution(numsArr)
        );
    }



    public static String solution(int[] xs) {
        // Your code here
//        System.out.println("Code started!");
        // there's two problems here
        // determining if it is possible to use an even number of, or zero, negative numbers
        // finding the highest multiple given the above

        if(xs.length==1){
            return ""+xs[0];
        }

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
//            System.out.println("Value of cell is: " + cell);
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

//        System.out.println(
//                "Smallest Absolute Value: " + smallestAbsVal + "\n" +
//                        "Smallest Absolute Value i: " + smallestAbsValI + "\n" +
//                        "Smallest Positive Value: " + smallestPositive + "\n" +
//                        "Smallest Positive Value i: " + smallestPositiveI + "\n" +
//                        "Smallest Negative Value: " + smallestNegative + "\n" +
//                        "Smallest Negative Value i: " + smallestNegativeI + "\n" +
//                        "Odd Negatives boolean: " + oddNegatives + "\n" +
//                        "Negative count: " + negatives + "\n" +
//                        "Indices of zero: " + zeroCells.toString()
//        );

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

        if(!reduceList(noZerosList).max(new BigInteger("0")).equals(new BigInteger("0"))&&zeroCells.size()>0){
//            System.out.println("Comparison result is: "+reduceList(noZerosList).max(new BigInteger("0")));
            return ""+reduceList(noZerosList);
        }
//        System.out.println("Largest product assigned 1");
        BigInteger largestProduct = new BigInteger("1");
//        System.out.println(noZerosList.length);
        // on each level, check the total for one index removed against largestProduct, build a layer inside that removes another index and checks the reduced total

        if(noZerosList.length>=3){
            int len = noZerosList.length;
            int numCombos = len*(len-1)*(len-2);
            for (int i = 0; i < len; i++) {
                int temp = noZerosList[i];
                noZerosList[i] = 1;
                BigInteger tempReduced = reduceList(noZerosList);
//                System.out.println("On round "+i+" of i loop, \n" +
//                        "the list is " + Arrays.toString(noZerosList)+"\n" +
//                        "while temp is:"+temp+"\n"+
//                        "while tempReduced is "+tempReduced);
//                System.out.println("-------------------------------------");
                if(tempReduced.max(largestProduct).equals(tempReduced)){
//                    System.out.println("Largest product assigned: "+tempReduced);
                    largestProduct = tempReduced;
                }
                for (int j = 0; j < len; j++) {
                    if(j==i){
                        continue;
                    }
                    int tempJ = noZerosList[j];
                    noZerosList[j] = 1;
                    BigInteger tempJReduced = reduceList(noZerosList);
//                    System.out.println("On round "+j+" of j loop, \n" +
//                            "the list is " + Arrays.toString(noZerosList)+"\n" +
//                            "while temp is:"+tempJ+"\n"+
//                            "while tempReduced is "+tempJReduced);
//                    System.out.println("-------------------------------------");
                    if(tempJReduced.max(largestProduct).equals(tempJReduced)){
//                        System.out.println("Largest product assigned: "+tempJReduced);
                        largestProduct = tempJReduced;
                    }
                    for (int k = 0; k < len; k++) {
                        if(k==i||k==j){
                            continue;
                        }
                        int tempK = noZerosList[k];
                        noZerosList[k] = 1;
                        BigInteger tempKReduced = reduceList(noZerosList);
//                        System.out.println("On round "+k+" of k loop, \n" +
//                                "the list is " + Arrays.toString(noZerosList)+"\n" +
//                                "while temp is:"+tempK+"\n"+
//                                "while tempReduced is "+tempKReduced);
//                        System.out.println("-------------------------------------");
                        if(tempKReduced.max(largestProduct).equals(tempKReduced)){
//                            System.out.println("Largest product assigned: "+tempKReduced);
                            largestProduct = tempKReduced;
                        }
                        noZerosList[k] = tempK;
                    }
                    noZerosList[j] = tempJ;
                }
                noZerosList[i] = temp;
            }
        } else if (noZerosList.length==2){
            int len = 2;
            int temp = noZerosList[0];
            noZerosList[0] = 1;
            BigInteger checker1 = reduceList(noZerosList);
            noZerosList[0] = temp;
            temp = noZerosList[1];
            noZerosList[1] = 1;
            BigInteger checker2 = reduceList(noZerosList);
            largestProduct = checker2.max(checker1);
        } else if (noZerosList.length==1){
            return "0";
        }

        return ""+largestProduct;
    }

    private static BigInteger reduceList(int[] List) {
        BigInteger bigInteger = new BigInteger("1");
        for( int entry : List){
            bigInteger = bigInteger.multiply(new BigInteger(""+entry));
        }
        return bigInteger;
    }
}