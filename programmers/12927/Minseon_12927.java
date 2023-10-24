/**
 * == 12927. 야근 지수 ==
 * 입력 : 남은 작업 시간 N, 남은 작업량 배열 works
 * 출력 : N시간 동안 남은 작업량에 대해 야근 피로도를 최소화한 값
 */

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());    // 큰 수가 우선순위인 큐

        for (int work : works) {
            pq.offer(work);
        }

        long answer = 0;

        /* n시간 동안 작업량 처리 */
        while (n > 0 && !pq.isEmpty()) {
            int work = pq.poll();
            if (work > 0) pq.offer(work-1);
            n--;
        }

        /* 야근 피로도 */
        while (!pq.isEmpty()) {
            long work = (long) pq.poll();
            answer += work * work;
        }

        return answer;
    }
}