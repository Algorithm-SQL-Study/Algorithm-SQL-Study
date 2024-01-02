/**
 * == 42884. 단속카메라 ==
 * 입력 : 차량의 고속도로 진입 지점, 진출 지점이 담긴 배열 routes
 * 출력 : 모든 차량이 한 번은 단속용 카메라를 만나게 하는 최소 카메라의 개수
 */

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        /* 진출 지점을 기준으로 정렬 */
        PriorityQueue<int[]> heapq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));

        for (int[] route : routes) {
            heapq.add(route);
        }

        /* 단속카메라 설치 */
        int cameras = 1;                // 단속 카메라 개수
        int location = heapq.poll()[1]; // 카메라 설치 위치

        while (!heapq.isEmpty()) {
            int[] route = heapq.poll();
            if (route[0] <= location) continue;     // 카메라를 지나가는 경우
            else {              // 카메라를 지나가지 않는 경우 -> 새로운 카메라 설치
                location = route[1];
                cameras++;
            }
        }

        return cameras;
    }
}