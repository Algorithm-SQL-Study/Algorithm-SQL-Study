import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        /* 다중집합 */
        List<String> str1_multiset = makeMultiset(str1.toUpperCase(), new ArrayList<>());
        List<String> str2_multiset = makeMultiset(str2.toUpperCase(), new ArrayList<>());

        if (str1_multiset.isEmpty() && str2_multiset.isEmpty()) return 1 * 65536;   // 둘 다 공집합인 경우 자카드 유사도는 1

        /* 교집합 */
        List<String> intersection = new ArrayList<>();
        List<String> temp = new ArrayList<>(str2_multiset);
        for (String str : str1_multiset) {
            if (temp.contains(str)) {
                temp.remove(str);
                intersection.add(str);
            }
        }

        /* 합집합 */
        List<String> union = new ArrayList<>(str1_multiset);
        union.addAll(str2_multiset);    // A + B

        for (String str : intersection) {   // A ∪ B = (A + B) - (A ∩ B)
            union.remove(str);
        }

        /* 자카드 유사도 */
        double jaccard = (double) intersection.size() / union.size();
        return (int) (jaccard * 65536);
    }

    public List<String> makeMultiset(String str, List<String> multiset) {
        for (int i = 0; i < str.length()-1; i++) {
            if ((int) str.charAt(i) < 65 || (int) str.charAt(i) > 90 ||
                    (int) str.charAt(i+1) < 65 || (int) str.charAt(i+1) > 90) continue;     // 대문자 알파벳이 아니면 원소로 만들지 않음
            String strElement = str.substring(i, i+2);
            multiset.add(strElement);
        }

        return multiset;
    }
}