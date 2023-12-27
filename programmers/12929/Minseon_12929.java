/**
 * == 12929. 올바른 괄호의 갯수 ==
 * 입력 : 괄호 쌍의 개수 n
 * 출력 : n개의 괄호 쌍으로 만들 수 있는 모든 올바른 괄호 문자열의 개수
 */

class Solution {
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i+1] += dp[j] * dp[i-j]; // (0, i) ~ (i, 0) 까지
            }
        }

        return dp[n];
    }
}