// https://school.programmers.co.kr/learn/courses/30/lessons/76502
import java.util.Stack;

class Solution {
    char SMALL_OPEN = '(';
    char SMALL_CLOSE = ')';
    char MID_OPEN = '{';
    char MID_CLOSE = '}';
    char BIG_OPEN = '[';
    char BIG_CLOSE = ']';
    
    public int solution(String s) {
        int answer = 0;
        int n = s.length();
        
        for (int start = 0; start < n; start++) {
            Stack<Character> stack = new Stack<>();
            boolean correct = true;
            for (int i = start; i < start + n; i++) {
                int adj = i;
                if (i >= n) adj -= n;
                
                char c = s.charAt(adj);
                char open = SMALL_OPEN;
                if (c == MID_OPEN || c == MID_CLOSE) {
                    open = MID_OPEN;
                } else if (c == BIG_OPEN || c == BIG_CLOSE) {
                    open = BIG_OPEN;
                }

                if (!isCorrect(c, open, stack)) {
                    correct = false;
                    break;
                }
            }
            if (stack.isEmpty() && correct) answer++;
        }
        
        return answer;
    }
    
    private boolean isCorrect(char now, char open
                              , Stack<Character> stack) {
        if (now == open) {
            stack.push(now);
            return true;
        } 
        if (!stack.isEmpty() && stack.pop() == open) {
            return true;
        }
        return false;
    }
}
