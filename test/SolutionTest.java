import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution1() {
        String act = Solution.solution(new int[] {0,0,0,2,3});
        String exp = "6";

        assertEquals(exp,act);
    }
}