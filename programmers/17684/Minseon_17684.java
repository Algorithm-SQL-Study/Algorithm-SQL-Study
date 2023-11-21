/**
 * == 17684. 압축 ==
 * 입력: 대문자 알파벳으로 이루어진 문자열 msg
 * 출력: 문자열을 압축한 후의 사전 색인 번호 배열
 */

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        /* STEP 1. 사전 초기화 */
        Map<String, Integer> dictionary = new HashMap<>();

        for (int i = 1; i <= 26; i++) {
            char ascii = (char) (i + 64);   // A to Z
            dictionary.put(Character.toString(ascii), i);
        }

        int lastIdx = 26;   // 사전의 마지막 색인 번호
        List<Integer> compression = new ArrayList<>();  // 압축 결과
        StringBuilder sb = new StringBuilder(msg);

        while (sb.length() > 0) {
            int idx = 0;        // 마지막으로 처리한 문자열의 인덱스

            String w = "" + sb.charAt(idx); // 현재 문자열
            if (sb.length() == 1) {
                compression.add(dictionary.get(w));
                break;
            }
            char c = sb.charAt(idx+1);  // 처리하지 않은 다음 문자

            /* STEP 2. 사전에 있는 가장 긴 문자열 찾기 */
            while (dictionary.get(w) != null && idx < sb.length() - 1) {
                c = sb.charAt(idx+1);
                if (dictionary.get(w+c) != null) {
                    w += c;
                    idx++;
                }
                else break;
            }

            /* STEP 3. 사전의 색인 번호 출력 */
            compression.add(dictionary.get(w));
            sb.delete(0, idx+1);  // 문자열 처리


            /* STEP 4. 처리하지 않은 문자열 사전에 등록 */
            dictionary.put(w+c, ++lastIdx);
        }

        int[] answer = compression.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}