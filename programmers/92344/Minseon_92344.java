/**
 * == 92344. 파괴되지 않은 건물 ==
 * 입력 : 건물의 내구도를 나타내는 2차원 배열 board, 적 또는 아군의 스킬을 나타내는 2차원 배열 skill
 * 출력 : 적의 공격 혹은 아군의 회복 스킬이 모두 끝난 뒤 파괴되지 않은 건물의 개수
 */

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length; int m = board[0].length;
        int[][] sum_arr = new int[n+1][m+1];

        /* 공격 및 회복 */
        for (int[] s : skill) {
            int type = s[0] == 1 ? -1 : 1;  // 공격(-) 또는 회복(+)
            int r1 = s[1]; int c1 = s[2];
            int r2 = s[3]; int c2 = s[4];
            int degree = s[5];

            sum_arr[r1][c1] += degree * type;
            sum_arr[r1][c2+1] -= degree * type;
            sum_arr[r2+1][c1] -= degree * type;
            sum_arr[r2+1][c2+1] += degree * type;
        }

        /* x축 방향 누적합 */
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                sum_arr[i][j] += sum_arr[i][j-1];
            }
        }

        /* y축 방향 누적합 */
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum_arr[j][i] += sum_arr[j-1][i];
            }
        }

        /* 파괴되지 않은 건물의 개수 */
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + sum_arr[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}