import java.util.Set;
import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];  // 탈락자 번호, 라운드

        int num = 1;
        int round = 1;

        Set<String> dictionary = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            if ((dictionary.contains(words[i]))
                    || (i > 0 && !isLastCharMatch(words[i-1], words[i]))) {
                answer[0] = num;
                answer[1] = round;
                break;
            }

            dictionary.add(words[i]);

            num += 1;
            if (num > n) {
                num -= n;
                round += 1;
            }
        }

        return answer;
    }

    private boolean isLastCharMatch(String before, String after) {
        char last = before.charAt(before.length()-1);
        char first = after.charAt(0);

        return last == first;
    }
}