import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution1(){
        String act = Solution.solution(new int[] {2,3,4,5,6});
        String exp = "360";

        assertEquals(exp,act);
    }

    @Test
    void solution2() {
        String act = Solution.solution(new int[] {2,0,2,2,0});
        String exp = "8";

        assertEquals(exp,act);
    }

    @Test
    void solution3() {
        String act = Solution.solution(new int[] {-1,1,2,3});
        String exp = "6";

        assertEquals(exp,act);
    }

    @Test
    void solution4() {
        String act = Solution.solution(new int[] {-10, -1, -1, 0, 1, 2, 3});
        String exp = "60";

        assertEquals(exp,act);
    }

    @Test
    void solution5() {
        String act = Solution.solution(new int[] {-10, -1, 0, 1, 2, 3});
        String exp = "60";

        assertEquals(exp,act);
    }

    @Test
    void solution6() {
        String act = Solution.solution(new int[] {-10, -1, -1, -1, 0, 2, 2, 3});
        String exp = "120";

        assertEquals(exp,act);
    }

    @Test
    void solution7() {
        String act = Solution.solution(new int[] {-10, -1, -1, 0, 2, 2, 3});
        String exp = "120";

        assertEquals(exp,act);
    }

    @Test
    void solution8() {
        String act = Solution.solution(new int[] {2,-3,1,0,-5});
        String exp = "30";

        assertEquals(exp,act);
    }
}