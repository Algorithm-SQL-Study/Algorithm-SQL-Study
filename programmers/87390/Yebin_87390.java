import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int n, long left, long right) {
        List<Long> answer = new ArrayList<>();
        for (long i = left; i <= right; i++) {
            long nr = i / n;
            long nc = i % n;
            answer.add(Math.max(nr, nc) + 1);
        }

        return answer.stream().mapToInt(Long::intValue).toArray();
    }
}