/**
 * == 12926. 시저 암호 ==
 * 입력 : 알파벳 소문자, 대문자, 공백으로 이루어진 문자열 s
 * 출력 : s를 n만큼 민 암호문
 */

class Solution {
    public String solution(String s, int n) {
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') answer += ' ';
            else {
                int ascii = (int) c;
                if (ascii <= 90) answer += (char) ((ascii + n - 65) % 26 + 65);
                else if (ascii <= 122) answer += (char) ((ascii + n - 97) % 26 + 97);
            }
        }

        return answer;
    }
}