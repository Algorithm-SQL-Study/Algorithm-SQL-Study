/**
 * == 68936. 쿼드압축 후 개수 세기 ==
 * 입력 : 2^n * 2^n 크기의 정수 배열 arr
 * 출력 : 쿼드압축 결과 최종적으로 남는 0의 개수와 1의 개수
 */

class Solution {
    int zeros = 0; int ones = 0;
    public int[] solution(int[][] arr) {
        compressSquare(arr);
        int[] answer = { zeros, ones };
        return answer;
    }

    public void compressSquare(int[][] arr) {
        int n = arr.length;
        if (isSame(arr)) {      // 현재 사각형의 모든 수가 같은 값인 경우
            if (arr[0][0] == 0) zeros++;
            else ones++;
            return;
        } 
        /* 사각형으로 쪼개기 */
        for (int i = 0; i < n; i += n/2) {
            for (int j = 0; j < n; j += n/2) {      // (i, j) : 시작 인덱스
                int[][] square = new int[n/2][n/2];
                for (int x = 0; x < n/2; x++) {
                    for (int y = 0; y < n/2; y++) {
                        square[x][y] = arr[i + x][j + y];
                    }
                }
                compressSquare(square);
            }
        }
    }

    public boolean isSame(int[][] arr) {
        int num = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (num != arr[i][j]) return false;
            }
        }
        return true;
    }
}
