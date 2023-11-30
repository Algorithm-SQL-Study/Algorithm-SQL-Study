import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

class Solution {
    public int[] solution(String msg) {
        Map<String,Integer> dictionary = new HashMap<>();
        for (int i = 1; i < 27; i++) {
            char a = (char) ('A' + i - 1);
            dictionary.put(String.valueOf(a), i);
        }
        System.out.println(dictionary);
        
        List<Integer> answer = new ArrayList<>();
        int start = 0;
        int end = 0;
        int index = 27;

        while (start < msg.length()) {
            end = start + 1;
            while(end <= msg.length() && dictionary.containsKey(msg.substring(start, end))) {
                end++;
            }
            
            String w = msg.substring(start, end - 1);
            answer.add(dictionary.get(w));
            
            if (end <= msg.length()) {
                String wc = msg.substring(start, end);
                dictionary.put(wc, index++);
            }
            
            start = end - 1;
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
