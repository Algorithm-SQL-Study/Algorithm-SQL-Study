import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int day = discount.length;
        
        Map<String, Integer> sales = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String thing = discount[i];
            sales.put(thing, sales.getOrDefault(thing, 0) + 1);
        }
        
        int start = 0;
        int end = 9;
        while (end < day) {
            if (saleEverything(want, number, sales)) answer++;
            
            sales.put(discount[start], sales.get(discount[start]) - 1);
            start++;
            end++;
            if (end == day) break;
            sales.put(discount[end], sales.getOrDefault(discount[end], 0) + 1);
        }
        
        return answer;
    }
    
    private boolean saleEverything(String[] want
                                   , int[] number
                                   , Map<String, Integer> sales) {
        int m = want.length;
        for (int i = 0; i < m; i++) {
            int count = sales.getOrDefault(want[i], 0);
            if (count < number[i]) return false;
        }
        return true;
    }
}
