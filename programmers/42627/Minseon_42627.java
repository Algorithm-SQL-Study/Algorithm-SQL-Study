/**
 * == 42627. 디스크 컨트롤러 ==
 * 입력 : 작업이 요청되는 시점, 작업의 소요시간 배열 jobs
 * 출력 : 작업의 요청부터 종료까지 걸린 시간의 평균의 최솟값
 */

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        /* 요청 시간 순 정렬 */
        PriorityQueue<int[]> heapq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] disk1, int[] disk2) {
                int result = Integer.compare(disk1[0], disk2[0]);
                if (result == 0) result = Integer.compare(disk1[1], disk2[1]);
                return result;
            }
        });

        for (int[] job : jobs) {
            heapq.offer(job);
        }

        /* 현재 시점에서 수행가능한 요청 정렬 */
        PriorityQueue<int[]> requestq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] disk1, int[] disk2) {
                return Integer.compare(disk1[1], disk2[1]);
            }
        });

        int currentTime = 0;   // 현재 시간
        int time = 0;          // 작업 소요 시간의 합
        requestq.offer(heapq.poll());
        while (!requestq.isEmpty()) {
            int[] job = requestq.poll();
            int waiting = 0;   // 작업 대기 시간

            /* 대기 시간 계산 */
            if (currentTime >= job[0]) {
                waiting = currentTime - job[0];
                currentTime += job[1];
            } else currentTime = job[0] + job[1];

            time += waiting + job[1];

            if (requestq.isEmpty() && !heapq.isEmpty() && heapq.peek()[0] > currentTime) currentTime = heapq.peek()[0]; // 요청 시간이 현재 시점보다 뒤인 경우(작업이 남아있는데 requestq가 비어버린 경우)

            /* 현재 시점에서 수행할 수 있는 작업 */
            while (!heapq.isEmpty() && heapq.peek()[0] <= currentTime) {
                requestq.offer(heapq.poll());
            }
        }

        return time / jobs.length;
    }
}