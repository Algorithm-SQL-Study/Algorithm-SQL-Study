/**
 * == 17687. N진수 게임 ==
 * 입력 : 진법 n, 미리 구할 숫자의 개수 t, 게임에 참가하는 인원 m, 튜브의 순서 p
 * 출력 : 튜브가 본인에 차례 때 말해야 하는 숫자
 */

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        /* n진수 변환 */
        StringBuilder conversed = new StringBuilder("");  // 변환 결과
        for (int i = 0; i <= t * m; i++) {
            conversed.append(converseNumber(n, i));
        }

        /* 튜브가 말해야 하는 숫자 구하기 */
        StringBuilder answer = new StringBuilder("");
        for (int i = p-1; i < conversed.length(); i += m) {
            answer.append(conversed.charAt(i));
            if (answer.length() == t) break;
        }
        return answer.toString();
    }

    public String converseNumber(int n, int num) {
        StringBuilder sb = new StringBuilder("");
        do {
            int remainder = num % n;
            char alpha = (char) (55 + remainder);
            if (remainder >= 10) sb.insert(0, alpha);   // A~F 처리
            else sb.insert(0, remainder);
            num /= n;
        } while (num > 0);
        return sb.toString();
    }
}