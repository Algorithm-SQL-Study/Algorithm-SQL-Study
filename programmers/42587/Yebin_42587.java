import java.util.Queue;
import java.util.LinkedList;
/**
* 출력: 특정 프로세스가 몇 번째로 실행되는지
* 입력: 프로세스 중요도, 알고싶은 프로세스 위치
*/
class Solution {
    // 큐 안에 더 큰게 있다는 걸 어떻게 알지? 
    // 큐를 만들면서 우선순위를 셀까. 1~9니까 배열로 저장하고. 프로세스를 실행하고 나서 지워. 
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int n = priorities.length;
        
        int[] pCount = new int[10];// 우선순위 개수 저장 
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int p = priorities[i];
            pCount[p]++;
            queue.offer(i);
        }
        
        while (!queue.isEmpty()) {
            int process = queue.poll();
            int p = priorities[process];
            
            boolean priorTo = false;
            for (int i = p + 1; i < 10; i++) {
                if (pCount[i] == 0) continue;
                priorTo = true;
                break;
            }
            if (priorTo) {
                queue.offer(process);
                continue;
            }
            
            answer++;
            pCount[p]--;
            if (process == location) break;
        }
        
        return answer;
    }
}
