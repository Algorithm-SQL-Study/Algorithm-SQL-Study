/**
 * 118668. 코딩 테스트 공부
 * 입력: 현재 알고력 alp, 코딩력 cop, 문제 정보(alp_req, cop_req, alp_rwd, cop_rwd, cost) 배열 problems
 * 출력: 모든 문제를 풀 수 있는 알고력과 코딩력을 얻는 최단시간
 */

import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        /* 목표 알고력, 코딩력 */
        int target_alp = alp;   // 알고력
        int target_cop = cop;   // 코딩력
        for (int[] problem : problems) {
            target_alp = Math.max(target_alp, problem[0]);
            target_cop = Math.max(target_cop, problem[1]);
        }

        // 현재 알고력, 코딩력으로 모든 문제를 풀 수 있는 경우
        if (target_alp <= alp && target_cop <= cop) return 0;

        //== DP ==//
        int[][] dp = init();
        dp[alp][cop] = 0;

        for (int i = alp; i <= target_alp; i++) {
            for (int j = cop; j <= target_cop; j++) {
                if (i + 1 <= target_alp) dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                if (j + 1 <= target_cop) dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);

                /* 문제 풀기 */
                for (int[] problem : problems) {
                    if (i < problem[0] || j < problem[1]) continue;   // 풀 수 없는 문제는 continue
                    int alp_rwd = problem[2]; int cop_rwd = problem[3];
                    int cost = problem[4];

                    // 알고력, 코딩력 높이기
                    int new_alp = (i + alp_rwd <= target_alp) ? i + alp_rwd : target_alp;
                    int new_cop = (j + cop_rwd <= target_cop) ? j + cop_rwd : target_cop;
                    dp[new_alp][new_cop]
                            = Math.min(dp[new_alp][new_cop], dp[i][j] + cost);
                }
            }
        }

        return dp[target_alp][target_cop];
    }

    /* dp 배열 초기화 */
    public int[][] init() {
        int[][] dp = new int[151][151];
        for (int i = 0; i < 151; i++) {
            for (int j = 0; j < 151; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        return dp;
    }
}