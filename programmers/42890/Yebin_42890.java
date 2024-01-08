import java.util.*;

class Solution {
    int rCount; // 튜플 개수
    int cCount; // 속성 개수
    List<List<Integer>> candidateKeys;
    
    public int solution(String[][] relation) {
        rCount = relation.length;    
        cCount = relation[0].length; 
        candidateKeys = new ArrayList<>();
        
        for (int i = 1; i <= cCount; i++) {
            List<Integer> temp = new ArrayList<>();
            generateCandidateKeys(0, i, temp, relation);
        }
        
        return candidateKeys.size();
    }
    
    private void generateCandidateKeys(int temp, int count
                                      , List<Integer> currentKey
                                      , String[][] relation) {
        if (count == 0) {   // 부분 집합 생성 완료
            if (isUnique(currentKey, relation) && isMinimal(currentKey)) {
                candidateKeys.add(new ArrayList<>(currentKey));
            }
            return;
        }
        for (int i = temp; i < cCount; i++) {   // 부분 집합 생성 재귀
            currentKey.add(i);
            generateCandidateKeys(i + 1, count - 1, currentKey, relation);
            currentKey.remove(currentKey.size() - 1);
        }
    }
    
    private boolean isUnique(List<Integer> currentKey, String[][] relation) {
        Set<String> tuple = new HashSet<>();
        for (int i = 0; i < rCount; i++) {
            StringBuilder sb = new StringBuilder();
            for (int key : currentKey) {
                sb.append(relation[i][key]);
            }
            tuple.add(sb.toString());
        }
        
        return tuple.size() == rCount;  // 중복된 정보가 없을 때 true
    }
    
    private boolean isMinimal(List<Integer> currentKey) {
        for (List<Integer> validKey : candidateKeys) {
            if (currentKey.containsAll(validKey)) return false;   
        }
        return true;
    }
}
