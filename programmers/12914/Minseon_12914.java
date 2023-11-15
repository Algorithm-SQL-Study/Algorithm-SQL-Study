/**
 * == 12914. 멀리 뛰기 ==
 * 입력 : 멀리뛰기에 사용될 칸의 수 n
 * 출력 : 끝에 도달하는 방법의 수를 1234567로 나눈 나머지
 */

class Solution {
    public long solution(int n) {
        long[] dp = new long[n+1];
        dp[1] = 1;              // 1칸 점프
        if (n > 1) dp[2] = 2;   // 2칸 점프 + 1에서 1칸 점프

        /* 1칸 점프 + 2칸 점프 */
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1234567;
        }

        return dp[n];
    }
}