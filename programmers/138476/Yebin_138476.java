import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int tan : tangerine) {
            int c = count.getOrDefault(tan, 0);
            count.put(tan, c + 1);
        }
        
        List<Integer> keySet = new ArrayList<>(count.keySet());
        keySet.sort((o1, o2) -> count.get(o2).compareTo(count.get(o1)));

        for (int key : keySet) {
            k -= count.get(key);
            answer++;
            if (k <= 0) break;
        }
        
        return answer;
    }
}
