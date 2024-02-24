// https://school.programmers.co.kr/learn/courses/30/lessons/92344
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] sum = new int[n+1][m+1];
        
        // 스킬 사용
        for (int[] s : skill) {
            int sign = s[0] == 1? -1: 1;
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
            skillSum(sum, r1, c1, r2, c2, sign * degree);
        }
        
        // 아래로 누적합 
        for (int j = 0; j < m + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                sum[i][j] += sum[i-1][j];
            }
        }
        
        // 오른쪽으로 누적합
        for (int i = 0; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                sum[i][j] += sum[i][j-1];
            }
        }
        
        // 파괴되지 않은 건물 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += sum[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    private void skillSum(int[][] sum
                          , int r1, int c1, int r2, int c2, int power) {
        sum[r1][c1] += power;
        sum[r2+1][c2+1] += power;
        sum[r1][c2+1] -= power;
        sum[r2+1][c1] -= power;
    }
}
