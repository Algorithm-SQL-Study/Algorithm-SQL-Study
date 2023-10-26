/**
 * == 42898. 등굣길 ==
 * 입력 : 격자의 크기 m, n, 물에 잠긴 지역 배열 puddles
 * 출력 : 집에서 학교까지 갈 수 있는 최단 경로의 개수
 */

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        boolean[][] puddle = new boolean[n][m];

        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][1] - 1;
            int y = puddles[i][0] - 1;
            puddle[x][y] = true;
        }

        dp[0][0] = 1;

        /* 이동 */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 잠긴 지역 피해가기
                if (puddle[i][j]) continue;

                // 이동
                if (i > 0 && j > 0) dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
                else if (j > 0 && !puddle[i][j-1]) dp[i][j] = dp[i][j-1] % 1000000007;
                else if (i > 0 && !puddle[i-1][j]) dp[i][j] = dp[i-1][j] % 1000000007;
            }
        }

        return dp[n-1][m-1] % 1000000007;
    }
}