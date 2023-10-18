/**
 * == 82612. 부족한 금액 계산하기 ==
 * 입력 : 놀이기구 이용료 price, 가지고 있던 금액 money, 놀이기구 이용 횟수 count
 * 출력 : 놀이기구를 count번 탔을 때 자신이 가지고 있는 금액에서 모자라는 금액
 */

class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;

        for (int i = 1; i <= count; i++) {
            answer += price * i;
        }

        if (answer > money) return answer - money;
        else return 0;
    }
}
