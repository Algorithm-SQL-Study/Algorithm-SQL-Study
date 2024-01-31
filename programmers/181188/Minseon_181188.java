/**
 * == 181188. 요격 시스템 ==
 * 입력 : 폭격 미사일의 x좌표 범위 목록 targets
 * 출력 : 모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일의 최소 개수
 */

import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        /* 폭격 미사일 좌표 끝구간 기준으로 정렬 */
        PriorityQueue<int[]> heapq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));

        for (int[] target : targets) {
            heapq.add(target);
        }

        /* 요격 미사일 발사 */
        int missiles = 0;               // 요격 미사일 개수
        double location = 0.0;          // 요격 위치

        while (!heapq.isEmpty()) {
            int[] target = heapq.poll();
            if (target[0] < location) continue;     // 요격 가능한 위치
            else {              // 요격 불가능한 경우 -> 새로운 요격 미사일 발사
                location = target[1] - 0.5;
                missiles++;
            }
        }

        return missiles;
    }
}