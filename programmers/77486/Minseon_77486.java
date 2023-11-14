/**
 * == 77486. 다단계 칫솔 판매 ==
 * 입력 : 조직 구성원 enroll, 추천인 referral, 판매원 seller, 판매 수량 amount
 * 출력 : 모든 조직 구성원들의 이익금 총합 배열
 */

import java.util.HashMap;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, String> parent = new HashMap();     // 추천인과 조직원 연결
        HashMap<String, Integer> member = new HashMap();    // 조직원과 이익금

        /* 트리 생성 */
        member.put("-", 0); // center 추가
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
            member.put(enroll[i], 0);
        }

        /* 이익금 계산 */
        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;    // 총 이익금
            String ref = seller[i];          // 부모
            int fee = profit / 10;           // 수수료
            member.put(ref, member.get(ref) + profit - fee);

            while (!parent.get(ref).equals("-")) {
                if (profit == 0) break;
                profit /= 10;
                fee = profit / 10;

                ref = parent.get(ref);
                member.put(ref, member.get(ref) + profit - fee);
            }
        }

        /* 이익금 배열 만들기 */
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = member.get(enroll[i]);
        }

        return answer;
    }
}