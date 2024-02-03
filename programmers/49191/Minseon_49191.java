/**
 * == 49191. 순위 ==
 * 입력 : 선수의 수 n, 경기 결과 배열 results
 * 출력 : 정확하게 순위를 매길 수 있는 선수의 수
 */

class Solution {
    public int solution(int n, int[][] results) {
        /* 그래프 만들기 */
        boolean[][] wins = new boolean[n+1][n+1];
        boolean[][] loses = new boolean[n+1][n+1];0

        for (int[] result : results) {
            int a = result[0]; int b = result[1];
            wins[a][b] = true;
            loses[b][a] = true;
        }

        /* 플로이드-워셜 */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (wins[i][k] && wins[k][j]) {
                        wins[i][j] = true;
                        loses[j][i] = true;
                    }
                    if (loses[i][k] && loses[k][j]) {
                        loses[i][j] = true;
                        wins[j][i] = true;
                    }
                }
            }
        }

        /* 순위가 확정된 선수 */
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (count(wins[i]) + count(loses[i]) == n-1) answer++;
        }
        return answer;
    }

    public int count(boolean[] graph) {
        int cnt = 0;
        for (int i = 1; i < graph.length; i++) {
            if (graph[i]) cnt++;
        }
        return cnt;
    }
}