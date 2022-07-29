import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Comparator;

public class Solution {
    public static String solution(String x, String y) {
        // Your code here
        BigInteger machTarget = new BigInteger(x);
        BigInteger facTarget = new BigInteger(y);
        BigInteger machCount = new BigInteger("1");
        BigInteger facCount = new BigInteger("1");
        BigInteger[][] bombCounts = {{machCount,facCount}};
        int numIterations = 0;
        return recurIterationCheck(machTarget,facTarget,bombCounts,numIterations);
    }

    private static String recurIterationCheck(BigInteger x, BigInteger y, BigInteger[][] bombCounts, int numIterations) {
        boolean isPossible = false;
        for(BigInteger[] count: bombCounts){
            if(count[0].equals(x)&&count[1].equals(y)){
                return ""+numIterations;
            }
        }
        ArrayList<Integer> badCells = new ArrayList<Integer>();
        /*
        * Implement Arrays.sort and then reduce array size based on entries that are not possible
        *   sort in ascending order of first cell values, create a new array that only accepts values below x
        *   sort in ascending order of second cell values, create a new array that only accepts values below y
        */
        for(int i = 0; i<bombCounts.length; i++){
            BigInteger[] count = bombCounts[i];
            if(count[0].add(count[1]).max(x).equals(x)&&count[1].add(count[0]).max(y).equals(y)){
                isPossible = true;
            }
            else if(count[0].equals(x)&&count[1].add(count[0]).max(y).equals(y)){
                isPossible = true;
            }
            else if(count[1].equals(y)&&count[0].add(count[1]).max(x).equals(x)){
                isPossible = true;
            }
            else {
                badCells.add(i);
            }
        }
        if(isPossible) {
            bombCounts = iterateAllCounts(x, y, bombCounts);
            numIterations++;
            return recurIterationCheck(x, y, bombCounts, numIterations);
        }
        return "impossible";
    }

    private static BigInteger[][] iterateAllCounts(BigInteger x, BigInteger y, BigInteger[][] bombCounts) {
//        bombCounts = trimList(x, y, bombCounts);
        int newLen = bombCounts.length*2;
        BigInteger[][] newBombCounts = new BigInteger[newLen][2];
        for (int i = 0; i < newLen; i=i+2) {
            int bombCountsInd = (int) Math.floor(i/2);
            BigInteger sum = bombCounts[bombCountsInd][0].add(bombCounts[bombCountsInd][1]);
            newBombCounts[i][0] = sum;
            newBombCounts[i][1] = bombCounts[bombCountsInd][1];
            newBombCounts[i+1][0] = bombCounts[bombCountsInd][0];
            newBombCounts[i+1][1] = sum;
        }
//        newBombCounts = trimList(x,y,newBombCounts);
        return newBombCounts;
    }

    private static BigInteger[][] trimList(BigInteger x, BigInteger y, BigInteger[][] bombCounts) {
        ArrayList<BigInteger[]> newList = new ArrayList<BigInteger[]>();
        for (BigInteger[] count :
                bombCounts) {
           if(checkCell(x,y,count)){
               newList.add(count);
           }
        }
        BigInteger[][] trimmedList = new BigInteger[newList.size()][2];
        for (int i = 0; i < newList.size(); i++) {
            trimmedList[i] = newList.get(i);
        }
        return trimmedList;
    }

    private static boolean checkCell(BigInteger x, BigInteger y, BigInteger[] count) {
        BigInteger sum = count[1].add(count[0]);
        boolean equalsX = sum.max(x).equals(x);
        boolean equalsY = sum.max(y).equals(y);
        if(equalsX &&count[1].equals(y)|| equalsY &&count[0].equals(x)|| equalsY && equalsX){
            return true;
        }
        return false;
    }
}