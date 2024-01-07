/**
 * == 42890. 후보키 ==
 * 입력 : 릴레이션을 나타내는 문자열 배열 relation
 * 출력 : 주어진 릴레이션에서 후보 키의 개수
 */

import java.util.*;

class Solution {
    List<List<Integer>> keys = new ArrayList<>();   // 만들 수 있는 모든 키 조합
    List<List<Integer>> candidateKeys = new ArrayList<>();  // 후보키

    public int solution(String[][] relation) {
        int attributes = relation[0].length;  // 컬럼 개수
        /* 가능한 키 조합 만들기 */
        for (int i = 1; i <= attributes; i++) {  // i개를 선택해서 만든 키 조합
            getKeyCombination(attributes, i, 0, new ArrayList<>());
        }

        /* 후보키 조건 */
        for (List<Integer> key : keys) {
            if (isUnique(key, relation) && isMinimal(key))
                candidateKeys.add(key);
        }

        return candidateKeys.size();
    }

    /* 키 조합 구하기 */
    public void getKeyCombination(int n, int r, int now, List<Integer> keyList) {
        if (keyList.size() == r) {
            keys.add(new ArrayList<>(keyList));
            return;
        }

        for (int i = now; i < n; i++) {
            keyList.add(i);
            getKeyCombination(n, r, i+1, keyList);
            keyList.remove(keyList.size() - 1);
        }
    }

    /* 유일성 */
    public boolean isUnique(List<Integer> key, String[][] relation) {
        /* 해당 키로 새로 리스트 만들기*/
        Set<List<String>> relationNotDuplicate = new HashSet<>();   // 중복을 제거한 relation
        for (String[] tuple : relation) {
            List<String> row = new ArrayList<>();
            for (Integer k : key) {
                row.add(tuple[k]);
            }
            relationNotDuplicate.add(new ArrayList<>(row));
        }

        if (relationNotDuplicate.size() == relation.length) return true;
        else return false;    // 중복을 제거한 relation과 원래 relation의 길이가 다르면 중복된 요소가 있다는 의미
    }

    /* 최소성 */
    public boolean isMinimal(List<Integer> key) {
        for (List<Integer> c_key : candidateKeys) {
            if (key.containsAll(c_key)) return false;   // key가 후보키에 있는 키를 포함하면 최소성을 만족할 수 없음
        }
        return true;
    }
}