// https://school.programmers.co.kr/learn/courses/30/lessons/150369
// 출력 : 최소 이동 거리
// 입력 : 트럭 용량, 집 개수, 배달 및 수거할 상자 개수
import java.util.Stack;
import java.util.Arrays;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;    // 누적 거리 
        Stack<Integer> dstack = new Stack<>();
        Stack<Integer> pstack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) {
                dstack.push(i);
            }
            if (pickups[i] > 0) {
                pstack.push(i);
            }
        }
        
        while (!dstack.empty() || !pstack.empty()) {
            int deliver = maxDistance(cap, dstack, deliveries);
            int pickup = maxDistance(cap, pstack, pickups);
            
            answer += (Math.max(deliver, pickup) + 1) * 2;
        }
        
        return answer;
    }
    
    private int maxDistance(int cap, Stack<Integer> points, int[] boxes) {
        int mx = -1;
        while (cap > 0) {
            // 더이상 배달/수거할 곳 없음 
            if (points.empty()) return mx;
            
            // 배달/수거할 곳 
            int i = points.pop();
            
            // 제일 먼 곳까지 가야 한다
            mx = Math.max(i, mx);
            
            // 트럭 용량만큼 배달/수거
            cap -= boxes[i];
            
            // 용량 초과
            if (cap < 0) {
                boxes[i] = Math.abs(cap);
                points.push(i);
            } else {
                boxes[i] = 0;
            }
        }
        
        return mx;
    }
}
