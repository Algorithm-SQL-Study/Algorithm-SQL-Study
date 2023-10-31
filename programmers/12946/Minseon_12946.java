/**
 * == 12946. 하노이의 탑 ==
 * 입력 : 1번 기둥에 있는 원판의 개수 n
 * 출력 : n개의 원판을 3번 원판으로 최소로 옮기는 방법
 */

import java.util.*;

class Solution {
    static List<int[]> result = new ArrayList<>();

    public int[][] solution(int n) {
        int[][] answer = new int[(int) Math.pow(2, n)-1][2];    // 최소 이동 횟수 : 2^n-1번

        hanoi(n, 1, 2, 3);

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private void hanoi(int n, int start, int via, int end) {
        if (n == 1) {
            result.add(new int[]{start, end});
            return;
        }
        hanoi(n-1, start, end, via);        // 1~n-1 원판을 start -> via로 이동
        result.add(new int[]{start, end});  // n 원판을 start -> end로 이동
        hanoi(n-1, via, start, end);        // 1~n-1 원판을 via -> end로 이동
    }
}
