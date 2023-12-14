/**
 * == 134239. 우박수열 정적분 ==
 * 입력 : 우박수의 초항 k, 정적분을 구하는 구간들의 목록 ranges
 * 출력 : 정적분의 결과 목록
 */

import java.util.*;

class Solution {
    List<Integer> hailSequence = new ArrayList<>();     // 우박 수열

    public double[] solution(int k, int[][] ranges) {
        /* 우박 수열 만들기 */
        int n = 0;  // 우박 수열이 1이 될 때까지의 횟수
        while (k >= 1) {
            hailSequence.add(k);
            if (k == 1) break;

            if (k % 2 == 0) k /= 2;
            else k = k * 3 + 1;
            n++;
        }

        /* 정적분 */
        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0]; int b = ranges[i][1];
            if (b <= 0) b = n + b;

            double area = 0.0;
            if (a > b) area = -1.0;     // 구간이 유효하지 않으면 -1
            else {
                for (int j = a; j < b; j++) {
                    area += integral(j, j+1);
                }
            }

            answer[i] = area;
        }
        return answer;
    }

    /* 정적분 */
    public double integral(int a, int b) {
        int hailA = hailSequence.get(a);
        int hailB = hailSequence.get(b);
        return 0.5 * (hailA + hailB);
    }
}