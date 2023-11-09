/**
 * == 12911. 다음 큰 숫자 ==
 * 입력 : 자연수 n
 * 출력 : 조건에 맞는 n 다음 큰 숫자
 */

class Solution {
    public int solution(int n) {
        int answer = n+1;

        while (true) {
            String binaryN = Integer.toBinaryString(n);
            String binaryNextN = Integer.toBinaryString(answer);

            if (getCount(binaryN) == getCount(binaryNextN)) return answer;
            else answer++;
        }
    }

    private int getCount(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') cnt++;
        }
        return cnt;
    }
}