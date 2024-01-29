/**
 * == 1843. 사칙연산 ==
 * 입력 : 연산자와 숫자가 들어있는 문자열 배열 arr
 * 출력 : 괄호를 적절히 넣어 계산했을 때 최댓값
 */

class Solution {
    public int solution(String arr[]) {
        /* 숫자, 연산자 분리 */
        int n = arr.length / 2 + 1;     // 숫자의 개수
        int[] numbers = new int[n];             // 숫자
        String[] operators = new String[n-1];   // 연산자
        int num = 0; int ops = 0;       // 인덱스
        for (String s : arr) {
            if (s.equals("-") || s.equals("+")) {
                operators[ops] = s;
                ops++;
            } else {
                numbers[num] = Integer.parseInt(s);
                num++;
            }
        }

        /* 최대, 최소 배열 만들기 */
        int[][] max_results = new int[n][n];    // i ~ j번째까지 연산의 최댓값
        int[][] min_results = new int[n][n];    // i ~ j번째까지 연산의 최솟값
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {                   // 자기 자신이 연산의 최댓값이자 최솟값
                    max_results[i][i] = numbers[i];
                    min_results[i][i] = numbers[i];
                } else {                        // 초기화
                    max_results[i][j] = Integer.MIN_VALUE;
                    min_results[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        /* 연산 */
        for (int step = 1; step < n; step++) {              // 계산 간격
            for (int start = 0; start < n-step; start++) {  // 연산 범위
                int end = start + step;
                for (int i = start; i < end; i++) {         // 연산자 위치
                    if (operators[i].equals("+")) {         // 연산자: +
                        max_results[start][end] = Math.max(max_results[start][end],
                                max_results[start][i] + max_results[i+1][end]);  // 최댓값: 최대 + 최대
                        min_results[start][end] = Math.min(min_results[start][end],
                                min_results[start][i] + min_results[i+1][end]);  // 최솟값: 최소 + 최소
                    } else {                                // 연산자: -
                        max_results[start][end] = Math.max(max_results[start][end],
                                max_results[start][i] - min_results[i+1][end]);  // 최댓값: 최대 - 최소
                        min_results[start][end] = Math.min(min_results[start][end],
                                min_results[start][i] - max_results[i+1][end]);  // 최솟값: 최소 - 최대
                    }
                }
            }
        }

        return max_results[0][n-1];
    }
}