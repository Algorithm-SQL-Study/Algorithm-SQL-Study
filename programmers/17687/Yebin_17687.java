import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public String solution(int n, int t, int m, int p) {
        List<String> answer = new ArrayList<>();
        
        Queue<Character> queue = new LinkedList<>();
        int loop = m * t;
        int number = 0;
        for (int i = 1; i <= loop; i++) {
            if (queue.isEmpty()) {
                for (Character c : Integer.toString(number, n).toCharArray()) {
                    queue.offer(
                        Character.isLowerCase(c) ? Character.toUpperCase(c) : c
                    );
                }
                number++;
            }
            
            char temp = queue.poll();
            
            if (i % m == p % m) {   // i & m == p 로 하면 p == m일 때 답이 나오지 않음 
                answer.add(String.valueOf(temp));
            }
        }
        
        return String.join("", answer.toArray(String[]::new));
    }
}
