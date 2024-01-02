/**
 * == 150369. 택배 배달과 수거하기 ==
 * 입력 : 트럭에 실을 수 있는 상자의 최대 개수 cap, 집의 개수 n, 배달할 상자 개수 배열 deliveries, 수거할 상자 개수 배열 pickups
 * 출력 : 트럭 하나로 모든 배달과 수거를 마치고 돌아올 수 있는 최소 이동 거리
 */

import java.util.Stack;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;    // 이동 거리

        Stack<Integer> d_stack = new Stack<>(); // 배달
        Stack<Integer> p_stack = new Stack<>(); // 픽업

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < deliveries[i]; j++) d_stack.push(i+1);
            for (int j = 0; j < pickups[i]; j++) p_stack.push(i+1);
        }

        while (!d_stack.isEmpty() || !p_stack.isEmpty()) {
            int d_end = d_stack.isEmpty() ? 0 : d_stack.peek(); // 배달할 수 있는 최대 인덱스
            int p_end = p_stack.isEmpty() ? 0 : p_stack.peek(); // 수거할 수 있는 최대 인덱스

            for (int i = 0; i < cap; i++) {
                if (!d_stack.isEmpty()) d_stack.pop();
                if (!p_stack.isEmpty()) p_stack.pop();
            }

            answer += Math.max(d_end, p_end) * 2;
        }

        return answer;
    }
}