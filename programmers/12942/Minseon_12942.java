/**
 * == 12942. 최적의 행렬 곱셈 ==
 * 입력 : 행렬의 크기 배열 matrix_sizes
 * 출력 : matrix_sizes에 결합 법칙을 적용해서 모든 행렬을 곱할 때 최소 곱셈 연산의 수
 */

class Solution {
    int[][] dp;         // dp[i][j] = i부터 j번째 행렬의 최소 연산 횟수
    int[][] matrix;

    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        this.dp = new int[n][n];
        this.matrix = matrix_sizes;
        return operate(0, n-1);
    }

    public int operate(int i, int j) {
        /* 왼쪽 행렬의 연산 횟수 + 오른쪽 행렬의 연산 횟수 + 두 행렬의 곱 */
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            min = Math.min(min, getResult(i, k) + getResult(k+1, j) + matrix[i][0] * matrix[k][1] * matrix[j][1]);
        }
        return min;
    }

    /* 불필요한 재귀 방지 */
    public int getResult(int i, int j) {
        if (i == j) return 0;                           // 같은 행렬끼리는 연산 불가
        if (dp[i][j] == 0) dp[i][j] = operate(i, j);    // 연산 값이 없는 경우
        return dp[i][j];
    }
}