/**
 * == 154538. 숫자 변환하기 ==
 * 입력: 자연수 x, y, n
 * 출력: x를 y로 변환하기 위해 필요한 최소 연산 횟수
 */

//== 1. DP를 이용한 풀이 ==//
class Solution {
    public int solution(int x, int y, int n) {
        int dp[] = new int[1000001];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        dp[x] = 0;

        for (int i = x; i < y; i++) {
            if (dp[i] == -1) continue;
            if (i+n <= y) {
                if (dp[i+n] == -1) dp[i+n] = dp[i] + 1;
                else dp[i+n] = Math.min(dp[i+n], dp[i] + 1);
            }

            if (i*2 <= y) {
                if (dp[i*2] == -1) dp[i*2] = dp[i] + 1;
                else dp[i*2] = Math.min(dp[i*2], dp[i] + 1);
            }

            if (i*3 <= y) {
                if (dp[i*3] == -1) dp[i*3] = dp[i] + 1;
                else dp[i*3] = Math.min(dp[i*3], dp[i] + 1);
            }
        }

        return dp[y];
    }
}


//== 2. bfs를 이용한 풀이 ==//

import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) return 0;

        Queue<Count> queue = new ArrayDeque<>();
        queue.offer(new Count(y, 0));

        while (!queue.isEmpty()) {
            Count cur = queue.poll();
            if (cur.value == x) return cur.count;

            if (cur.value > x) {
                if (cur.value % 3 == 0) queue.offer(new Count(cur.value / 3, cur.count + 1));
                if (cur.value % 2 == 0) queue.offer(new Count(cur.value / 2, cur.count + 1));
                queue.offer(new Count(cur.value - n, cur.count + 1));
            }
        }

        return -1;
    }
}

class Count {
    int value;  // 값
    int count;  // 연산 횟수

    public Count(int value, int count) {
        this.value = value;
        this.count = count;
    }
}