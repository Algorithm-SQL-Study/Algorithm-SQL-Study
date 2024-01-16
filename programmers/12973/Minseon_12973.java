/**
 * == 12973. 짝지어 제거하기 ==
 * 입력 : 문자열 s
 * 출력 : 짝지어 제거하기의 성공 여부
 */

import java.util.Stack;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i))
                stack.pop();
            else stack.push(s.charAt(i));
        }

        if (!stack.isEmpty()) return 0;
        return 1;
    }
}