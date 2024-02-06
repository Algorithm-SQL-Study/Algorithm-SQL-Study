// https://school.programmers.co.kr/learn/courses/30/lessons/84512
class Solution {
    public int solution(String word) {
        int answer = 0;
        int[] base = {781, 156, 31, 6, 1}; // 각 자리의 단어 수

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = "AEIOU".indexOf(c); // 현재 문자의 인덱스

            answer += 1 + index * base[i];
        }

        return answer;
    }
}
