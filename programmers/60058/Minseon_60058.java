/**
 * 60058. 괄호 변환
 * 입력 : 균형잡힌 괄호 문자열 p
 * 출력 : p를 올바른 괄호 문자열로 변환한 결과
 */

import java.util.Stack;

class Solution {
    public String solution(String p) {
        return makeRightString(p);
    }

    public String makeRightString(String w) {
        String answer = "";
        if (isRightBracket(w)) return w;    // w가 올바른 괄호 문자열인 경우
        else {                              // 균형잡힌 괄호 문자열 2개로 분리
            int idx = splitStringIdx(w);    // 문자열을 자를 위치 + 1
            String u = w.substring(0, idx);
            String v = w.substring(idx, w.length());
            if (isRightBracket(u)) {    // u가 올바른 괄호 문자열인 경우
                answer += u;
                answer += makeRightString(v);
            } else {                // u가 올바른 괄호 문자열이 아닌 경우
                answer += "(";
                answer += makeRightString(v);
                answer += ")";
                String uu = u.substring(1, u.length()-1);   // u의 첫번째, 마지막 문자 제거한 나머지 문자열
                for (char c : uu.toCharArray()) {   // 나머지 문자열의 괄호 방향 뒤집기
                    if (c == '(') answer += ')';
                    else answer += '(';
                }
            }
        }

        return answer;
    }

    /* 문자열을 자를 위치 구하기 */
    public int splitStringIdx(String w) {
        int left = 0; int right = 0;    // (, )의 개수
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') left++;
            else if (w.charAt(i) == ')') right++;

            if (left == right) {    // 균형잡힌 문자열 판별
                return i+1;         // 문자열을 자를 인덱스 + 1
            }
        }

        return 0;
    }

    /* 올바른 괄호 문자열 판별 */
    public boolean isRightBracket(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(c);
            else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') stack.pop();
            else return false; // 괄호가 올바르지 않은 경우
        }

        return stack.isEmpty();
    }
}