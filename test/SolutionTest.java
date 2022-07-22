import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @org.junit.jupiter.api.Test
    void solution1() {
        int act = Solution.solution(19,36);
        int exp = 1;

        assertEquals(exp,act);
    }

    void solution2() {
        int act = Solution.solution(0,1);
        int exp = 3;

        assertEquals(exp, act);
    }

    void solution3() {
        int act = Solution.solution(54,63);
        int exp = 4;

        assertEquals(exp,act);
    }

    void solution4(){
        int act = Solution.solution(9,24);
        int exp = 1;

        assertEquals(exp,act);
    }
}