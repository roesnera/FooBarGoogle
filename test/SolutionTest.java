import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution(){
        String act = Solution.solution(new int[] {2,3,4,5,6});
        String exp = "360";

        assertEquals(exp,act);
    }

    @Test
    void solution2() {
        String act = Solution.solution(new int[] {2,0,2,2});
        String exp = "8";

        assertEquals(exp,act);
    }
}