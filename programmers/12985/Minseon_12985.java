/**
 * == 12985. 예상 대진표 ==
 * 입력 : 게임 참가자 수 N, 참가자 번호 A, 경쟁자 번호 B
 * 출력 : A번 참가자와 B번 참가자가 만나는 라운드
 */

class Solution {
    public int solution(int n, int a, int b) {
        int round = 0;  // 처음 만나는 라운드

        while (a != b) {
            a = (a + 1) / 2;    // 이겼을 때 받는 번호
            b = (b + 1) / 2;
            round++;
        }

        return round;
    }
}