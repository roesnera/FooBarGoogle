import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Comparator;

public class Solution {
    static String imp = "impossible";
    public static String solution(String x, String y) {
        // Your code here
        BigInteger machTarget = new BigInteger(x);
        BigInteger facTarget = new BigInteger(y);
        BigInteger machCount = new BigInteger("1");
        BigInteger facCount = new BigInteger("1");
        BigInteger[][] bombCounts = {{machCount,facCount}};
        int numIterations = 0;
//        return recurFibBranchCheck(machTarget,facTarget,bombCounts,numIterations);
        return imp;
    }


//    private static String recurFibBranchCheck(BigInteger machTarget, BigInteger facTarget, BigInteger[][] bombCounts, int numIterations) {
//        if(numIterations==0){
//
//        }
//
//
//        return imp;
//    }

    public static String recurFibBranchCheck(int machTarget, int facTarget, int[] cell, int numIterations) {
        if(cell[0]==machTarget&&cell[1]==facTarget){
            return ""+numIterations;
        } else if (cell[0]==machTarget) {
            if(facTarget-cell[1]%cell[0]==0){
                return ""+(numIterations+facTarget-cell[1]/cell[0]);
            } else {
                return imp;
            }
        } else if (cell[1]==facTarget) {
            if (machTarget - cell[0] % cell[1] == 0) {
                return "" + (numIterations + machTarget - cell[0] / cell[1]);
            } else {
                return imp;
            }
        } else if (isFib(cell[0],cell[1], machTarget, facTarget)) {
            int largerFibTarget = Math.max(machTarget, facTarget);
            System.out.println("Calling whereFib()");
            return ""+(whereFib(cell[0], cell[1], largerFibTarget, numIterations));
        } else {
            if(!(recurFibBranchCheck(machTarget,facTarget,new int[] {cell[0], cell[0]+cell[1]}, numIterations+1)).equals(imp)){
                return recurFibBranchCheck(machTarget,facTarget,new int[] {cell[0], cell[0]+cell[1]}, numIterations+1);
            } else if(!(recurFibBranchCheck(machTarget,facTarget,new int[] {cell[1], cell[0]+cell[1]}, numIterations+1)).equals(imp)){
                return recurFibBranchCheck(machTarget,facTarget,new int[] {cell[1], cell[0]+cell[1]}, numIterations+1);
            }
        }


        return imp;
    }

    public static boolean isFib(int start1, int start2, int maybeFib, int maybeFib2){
        if(start1>maybeFib&&start2>maybeFib||start1>maybeFib2&&start2>maybeFib2){
            return false;
        }

        if(start1==maybeFib&&start2==maybeFib2||start1==maybeFib2&&start2==maybeFib){
            return true;
        } else {
            return isFib(start2, start2+start1, maybeFib, maybeFib2);
        }
    }

    public static int whereFib(int start1, int start2, int defFib, int count){
        System.out.println("Iteration #: "+count+"\n"+
                "start1 is: "+start1+"\n"+
                "start2 is: "+start2+"\n"+
                "defFib is: "+defFib+"\n"+
                "count is: "+count);
        if(start1==defFib||start2==defFib){
            return count;
        } else {
            return whereFib(start2, start2+start1, defFib, count+1);
        }
    }
}