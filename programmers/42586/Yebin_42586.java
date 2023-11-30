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
////////////////////Queue를 이용한 풀이/////////////////////
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

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
        
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        for (int d : day) {
            if (!queue.isEmpty() && d > queue.peek()) {
                answer.add(queue.size());
                queue.clear();
            }
            queue.offer(d);
        }
        answer.add(queue.size());
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

