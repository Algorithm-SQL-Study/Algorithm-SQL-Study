/**
 * == 42626. 더 맵게 ==
 * 입력 : 스코빌 지수를 담은 배열 scoville, 원하는 스코빌 지수 K
 * 출력 : 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
 */

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Long> food = new PriorityQueue<>();

        /* 스코빌 지수가 낮은 순서대로 정렬 */
        for (int s : scoville) {
            food.add((long) s);
        }

        /* 새로운 음식 만들기 */
        while (food.peek() < K && food.size() > 1) {
            food.add(food.poll() + food.poll() * 2);
            answer++;
        }

        if (food.poll() < K) answer = -1;
        return answer;
    }
}