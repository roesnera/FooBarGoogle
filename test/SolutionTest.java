import org.junit.jupiter.api.Test;

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
}