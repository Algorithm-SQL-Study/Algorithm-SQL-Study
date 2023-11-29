import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);     // 바위 위치 정렬

        /* 이분 탐색 */
        int mid = 0; int left = 0;
        int right = distance;
        while (left <= right) {
            mid = (left + right) / 2;   // 제거 후 바위 사이의 거리의 최솟값

            /* 제거한 바위 개수 */
            int rock = 0; int prev = 0; // 제거한 바위 개수, 이전 바위
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) rock++;  // 바위 사이 간격이 최솟값보다 작으면 제거
                else prev = rocks[i];               // 제거하지 않을 경우 이전 바위 업데이트
            }
            if (distance - prev < mid) rock++;      // 도착 지점과 마지막 바위의 간격 확인

            if (rock > n) right = mid - 1;  // 제거한 바위 개수 > 제거할 바위 개수 -> 간격 좁히기
            else left = mid + 1;            // 제거한 바위 개수 < 제거할 바위 개수 -> 간격 늘리기
        }

        return right;
    }
}