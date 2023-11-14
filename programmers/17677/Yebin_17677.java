import java.lang.StringBuilder;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        // 문자열 -> 다중집합
        Map<String, Integer> set1 = getElementAndCount(str1);
        Map<String, Integer> set2 = getElementAndCount(str2);

        // 교집합 구하기
        int a = intersect(set1, set2);

        // 합집합 구하기
        int b = union(set1, set2);

        // 자카드 유사도 * 65536
        return J(a, b);
    }

    private Map<String, Integer> getElementAndCount(String str) {
        Map<String, Integer> map = new HashMap();
        int len = str.length();
        String upper = str.toUpperCase();
        for (int i = 0; i < len - 1; i++) {
            char first = upper.charAt(i);
            char second = upper.charAt(i+1);

            if (!isAlpabet(first) || !isAlpabet(second)) continue;

            StringBuilder sb = new StringBuilder();
            sb.append(first).append(second);

            String key = sb.toString();
            int value = map.getOrDefault(key, 0) + 1;
            map.put(key, value);
        }
        return map;
    }

    private boolean isAlpabet(char c) {
        int ascii = (int) c;
        return ascii >= 65 && ascii <= 90;
    }

    private int intersect(Map<String, Integer> set1
            , Map<String, Integer> set2) {
        Set<String> keys = new HashSet<>();
        keys.addAll(set1.keySet());
        keys.retainAll(set2.keySet());

        int count = 0;
        for (String key : keys) {
            int val1 = set1.get(key);
            int val2 = set2.get(key);
            count += Math.min(val1, val2);
        }

        return count;
    }

    private int union(Map<String, Integer> set1
            , Map<String, Integer> set2) {
        Set<String> keys = new HashSet<>();
        keys.addAll(set1.keySet());
        keys.addAll(set2.keySet());

        int count = 0;
        for (String key : keys) {
            int val1 = set1.getOrDefault(key, 0);
            int val2 = set2.getOrDefault(key, 0);
            count += Math.max(val1, val2);
        }
        return count;
    }

    private int J(int a, int b) {
        if (a == 0 && b == 0) return 65536;
        return (a * 65536) / b;
    }
}