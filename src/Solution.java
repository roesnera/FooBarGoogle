import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;

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
        for(BigInteger[] count: bombCounts){
            if(count[0].add(count[1]).max(x).equals(x)&&count[1].add(count[0]).max(y).equals(y)){
                isPossible = true;
            }
            if(count[0].equals(x)&&count[1].add(count[0]).max(y).equals(y)){
                isPossible = true;
            }
            if(count[1].equals(y)&&count[0].add(count[1]).max(x).equals(x)){
                isPossible = true;
            }
        }
        if(isPossible) {
            bombCounts = iterateAllCounts(bombCounts);
            numIterations++;
            return recurIterationCheck(x, y, bombCounts, numIterations);
        }
        return "impossible";
    }

    private static BigInteger[][] iterateAllCounts(BigInteger[][] bombCounts) {
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
        return newBombCounts;
    }
}