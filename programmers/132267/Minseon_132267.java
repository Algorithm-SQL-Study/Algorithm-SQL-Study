/**
 * == 132267. 콜라 문제 ==
 * 입력 : 마트에 줘야하는 병 개수 a, 마트가 주는 콜라 병 수 b, 상빈이가 가지고 있는 빈 병 개수 n
 * 출력 : 상빈이가 받을 수 있는 콜라의 병 수
 */

class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;

        while (n >= a) {
            answer += b * (n / a);
            n = n % a + b * (n / a);
        }

        return answer;
    }
}