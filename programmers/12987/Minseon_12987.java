/**
 * == 12987. 숫자 게임 ==
 * 입력 : 출전 순서대로 나열된 A팀 배열 A, B팀 배열 B
 * 출력 : B 팀원들이 얻을 수 있는 최대 승점
 */

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        /* 정렬 */
        Arrays.sort(A);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : B) {
            pq.add(num);
        }

        /* 숫자 게임 */
        int score = 0;
        for (int i = 0; i < A.length; i++) {
            // 승점을 얻을 수 없으면 그 뒤 상대에게도 승점을 얻을 수 없음
            while (!pq.isEmpty() && A[i] >= pq.peek()) pq.poll();
            if (pq.isEmpty()) break;
            pq.poll(); score++;
        }

        return score;
    }
}