import java.util.Deque;
import java.util.ArrayDeque;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.add(c);
            } else {
                if (c == stack.getLast()) {
                    stack.removeLast();
                } else {
                    stack.add(c);
                }
            }
        }

        return stack.isEmpty()? 1: 0;
    }
}