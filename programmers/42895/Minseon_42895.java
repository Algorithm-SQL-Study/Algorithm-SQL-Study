/**
 * == 42895. N으로 표현 ==
 * 입력 : 숫자 N, number
 * 출력 : N 사용횟수의 최솟값
 */

import java.util.*;

class Solution {
    public int solution(int N, int number) {
        /* N의 개수에 따라 만들 수 있는 수 */
        List<Set<Integer>> nList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            nList.add(new HashSet<>());
        }

        if (N == number) return 1;

        int n = N;
        nList.get(1).add(N);
        for (int i = 2; i < 9; i++) {   // N 2개~8개
            for (int j = 1; j < i; j++) {
                for (int a : nList.get(j)) {
                    for (int b : nList.get(i-j)) {  // 사칙연산
                        nList.get(i).add(a+b);
                        nList.get(i).add(a-b);
                        nList.get(i).add(a*b);
                        if (b != 0) nList.get(i).add(a/b);
                    }
                }
            }

            n = n*10 + N;   // 연속된 수
            nList.get(i).add(n);

            // N i개로 만들 수 있으면 i 리턴
            if (nList.get(i).contains(number)) return i;
        }

        return -1;
    }
}