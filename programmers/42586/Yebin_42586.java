import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] day = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            while (progresses[i] < 100) {
                progresses[i] += speeds[i];
                count++;
            }
            day[i] = count;
        }
        
        int p = 0;
        int q = 1;
        List<Integer> answer = new ArrayList<>();
        while (p < n) {
            if (q >= n) {
                answer.add(q-p);
                break;
            }
            if (day[p] >= day[q]) {
                q++;
                continue;
            } 
            answer.add(q-p);
            p = q;
            q += 1;
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
