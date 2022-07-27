import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;


/*
* Test brainstorm:
* some zeros, all zeros, no zeros
* big numbers, small numbers, mix
* even num negative, odd num negatives, no negatives
*/

class SolutionTest {

    @Test
    void solution27() {
        String act = Solution.solution(new int[] {-1, 0});
        String exp = "0";

        assertEquals(exp,act);
    }

    @Test
    void solution26() {
        int[] arr = new int[50];
        BigInteger prod = new BigInteger("1");
        for (int i = -25; i < 24; i++) {
            arr[i+25] = i*1000 - 500;
            if(i!=0){
                prod = prod.multiply(new BigInteger((i*1000-500)+""));
            }
        }
        String act = Solution.solution(arr);
        prod = prod.multiply(new BigInteger("-500"));
        String exp = ""+prod;

        assertEquals(exp,act);
    }

    @Test
    void solution25() {
        int[] arr = new int[50];
        BigInteger prod = new BigInteger("1");
        for (int i = -24; i < 25; i++) {
            arr[i+24] = i;
            if(i!=0){
                prod = prod.multiply(new BigInteger(i+""));
            }
        }
        String act = Solution.solution(arr);
        String exp = ""+prod;

        assertEquals(exp,act);
    }

    @Test
    void solution24() {
        int[] arr = new int[50];
        BigInteger prod = new BigInteger("1");
        for (int i = 0; i < 50; i++) {
            arr[i] = i;
            if(i!=0){
                prod = prod.multiply(new BigInteger(i+""));
            }
        }
        String act = Solution.solution(arr);
        String exp = ""+prod;

        assertEquals(exp,act);
    }

    @Test
    void solution23() {
        String act = Solution.solution(new int[] {-3, -2, -1, 0, 10});
        String exp = "60";

        assertEquals(exp,act);
    }

    @Test
    void solution22() {
        String act = Solution.solution(new int[] {-2, -2, -2, -2, -2});
        String exp = "16";

        assertEquals(exp,act);
    }

    @Test
    void solution21() {
        String act = Solution.solution(new int[] {-1, -2, -2});
        String exp = "4";

        assertEquals(exp,act);
    }

    @Test
    void solution20() {
        String act = Solution.solution(new int[] {-1, 2});
        String exp = "2";

        assertEquals(exp,act);
    }

    @Test
    void solution19() {
        String act = Solution.solution(new int[] {-1, -1, -1, 1});
        String exp = "1";

        assertEquals(exp,act);
    }

    @Test
    void solution18() {
        String act = Solution.solution(new int[] {-1, -1, 1});
        String exp = "1";

        assertEquals(exp,act);
    }

    @Test
    void solution17() {
        String act = Solution.solution(new int[] {-1, 1});
        String exp = "1";

        assertEquals(exp,act);
    }

    @Test
    void solution16() {
        String act = Solution.solution(new int[] {0, -1, 1});
        String exp = "1";

        assertEquals(exp,act);
    }

    @Test
    void solution15() {
        String act = Solution.solution(new int[] {100000, 2, 3, 15678});
        String exp = "4703400000";

        assertEquals(exp,act);
    }

    @Test
    void solution14() {
        String act = Solution.solution(new int[] {0,0,0,0,0});
        String exp = "0";

        assertEquals(exp,act);
    }

    @Test
    void solution1() {
        String act = Solution.solution(new int[] {0,0,0,2,3,1,-1});
        String exp = "6";

        assertEquals(exp,act);
    }

    @Test
    void solution2() {
        String act = Solution.solution(new int[] {0,0,0,2,3,-1});
        String exp = "6";

        assertEquals(exp,act);
    }

    @Test
    void solution3() {
        String act = Solution.solution(new int[] {0,0,0,2,-3});
        String exp = "2";

        assertEquals(exp,act);
    }

    @Test
    void solution4() {
        String act = Solution.solution(new int[] {0,0,0,-2,3});
        String exp = "3";

        assertEquals(exp,act);
    }

    @Test
    void solution5() {
        String act = Solution.solution(new int[] {0,0,0,2,3,1,-1,-10,-10});
        String exp = "600";

        assertEquals(exp,act);
    }

    @Test
    void solution6() {
        String act = Solution.solution(new int[] {0,0,0,2,3,1,-10,-10});
        String exp = "600";

        assertEquals(exp,act);
    }

    @Test
    void solution7() {
        String act = Solution.solution(new int[] {2,3,1,-10,-10});
        String exp = "600";

        assertEquals(exp,act);
    }

    @Test
    void solution8() {
        String act = Solution.solution(new int[] {20, -1, -3, -4});
        String exp = "240";

        assertEquals(exp,act);
    }

    @Test
    void solution9() {
        String act = Solution.solution(new int[] {20, -1, -4});
        String exp = "20";

        assertEquals(exp,act);
    }

    @Test
    void solution10() {
        String act = Solution.solution(new int[] {10000, 10000, 500, 6});
        String exp = "50000000000";

        assertEquals(exp,act);
    }

    @Test
    void solution11() {
        String act = Solution.solution(new int[] {10000, 10000, -500, 6});
        String exp = "600000000";

        assertEquals(exp,act);
    }

    @Test
    void solution12() {
        String act = Solution.solution(new int[] {0, 10000, 10000, -500, 6});
        String exp = "600000000";

        assertEquals(exp,act);
    }

    @Test
    void solution13() {
        String act = Solution.solution(new int[] {1,1,1,1,1,1,1,1,1,1,1});
        String exp = "1";

        assertEquals(exp,act);
    }
}