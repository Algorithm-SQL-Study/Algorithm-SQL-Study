import java.util.Arrays;

class Solution {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String res = "";
            int[] row1 = binary(n, arr1[i]);
            int[] row2 = binary(n, arr2[i]);

            for (int j = 0; j < n; j++) {
                if (row1[j] == 1 || row2[j] == 1) res += "#";
                else res += " ";
            }

            answer[i] = res;
        }

        return answer;
    }

    private int[] binary(int n, int row) {
        int[] bi = new int[n];

        for (int i = n - 1; i > 1; i--) {
            bi[i] = row % 2;
            row = row / 2;
        }

        bi[1] = row % 2;
        bi[0] = row / 2;

        return bi;
    }
}
////////////////////////// Integer.toBinaryString을 이용한 풀이
import java.util.Arrays;

class Solution {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String line = Integer.toBinaryString(arr1[i] | arr2[i]) // 비트합 연산
                    .replace("1", "#").replace("0", " ");           // 1->#, 0->공백
            for (int j = line.length(); j < n; j++) {
                line = " " + line;  // 길이가 부족하면 앞에 공백 추가
            }

            answer[i] = line;
        }

        return answer;
    }
}