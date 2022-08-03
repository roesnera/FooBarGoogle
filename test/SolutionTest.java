import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution1() {
        String act = Solution.solution("1","1");
        String exp = "0";

        assertEquals(exp,act);
    }

    @Test
    void solution2() {
        String act = Solution.solution("1","2");
        String exp = "1";

        assertEquals(exp,act);
    }

    @Test
    void solution3() {
        String act = Solution.solution("5","2");
        String exp = "3";

        assertEquals(exp,act);
    }

    @Test
    void solution4() {
        String act = Solution.solution("6","2");
        String exp = "impossible";

        assertEquals(exp,act);
    }

    @Test
    void solution5() {
        String act = Solution.solution("71","4");
        String exp = "36";

        assertEquals(exp,act);
    }

    @Test
    void isFib() {
        boolean act = Solution.isFib(1,1, 121393, 196418);
        boolean exp = true;

        assertEquals(exp,act);
    }

    @Test
    void whereFib() {
        int act = Solution.whereFib(1,1, 144, 0);
        int exp = 10;

        assertEquals(exp,act);
    }

    @Test
    void recurFibBranchCheck() {
        String act = Solution.recurFibBranchCheck(121393, 196418, new int[] {1,1}, 0);
        String exp = "25";

        assertEquals(exp,act);
    }

    @Test
    void recurFibImpBranchCheck() {
        String act = Solution.recurFibBranchCheck(121393, 196415, new int[] {1,1}, 0);
        String exp = "impossible";

        assertEquals(exp,act);
    }
}