/**
 * == 42839. 소수 찾기 ==
 * 입력 : 숫자가 적힌 문자열 numbers
 * 출력 : 종이 조각으로 만들 수 있는 소수의 개수
 */

import java.util.*;
import java.util.HashSet;

class Solution {
    Set<Integer> permutation = new HashSet<Integer>();
    public int solution(String numbers) {
        int answer = 0;

        /* 문자열 조합 */
        getPermutation(numbers, new StringBuilder(), numbers.length());

        /* 소수 판별 */
        for (int num : permutation) {
            System.out.println(num);
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    private void getPermutation(String input, StringBuilder current, int length) {
        if (current.length() > 0 && current.length() <= length) {
            int num = Integer.parseInt(current.toString());
            permutation.add(num);
        }

        for (int i = 0; i < input.length(); i++) {
            current.append(input.charAt(i));
            getPermutation(input.substring(0, i) + input.substring(i + 1), current, length);
            current.deleteCharAt(current.length() - 1);
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}