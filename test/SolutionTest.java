import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @org.junit.jupiter.api.Test
    void solution() {
        int act = Solution.solution(19,36);
        int exp = 1;

        assertEquals(exp,act);
    }
}