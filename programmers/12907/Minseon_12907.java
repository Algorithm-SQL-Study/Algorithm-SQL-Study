/**
 * == 12907. 거스름돈 ==
 * 입력 : 거슬러 줘야 하는 금액 n, 현재 보유하고 있는 돈의 종류 money
 * 출력 : n원을 거슬러 줄 방법의 수 % 1000000007
 */

class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n+1];
        dp[0] = 1;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] += dp[i-m];
            }
        }

        return dp[n] % 1000000007;
    }
}