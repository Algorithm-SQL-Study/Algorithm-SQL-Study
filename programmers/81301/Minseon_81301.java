/**
 * == 81301. 숫자 문자열과 영단어 ==
 * 입력 : 문자열 s
 * 출력 : s가 의미하는 원래 숫자
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String s) {
        int answer = 0;

        /* 영단어-숫자 해시맵 */
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("zero", "0");
        hashMap.put("one", "1");
        hashMap.put("two", "2");
        hashMap.put("three", "3");
        hashMap.put("four", "4");
        hashMap.put("five", "5");
        hashMap.put("six", "6");
        hashMap.put("seven", "7");
        hashMap.put("eight", "8");
        hashMap.put("nine", "9");

        /* 영단어 -> 숫자 */
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            s = s.replace(key, value);
        }

        answer = Integer.parseInt(s);

        return answer;
    }
}