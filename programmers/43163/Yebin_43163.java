import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target))
            return 0;

        int answer = 0;
        int wordsLength = words.length;
        int wordLength = target.length();

        boolean[] used = new boolean[wordsLength];

        Queue<Item> queue = new ArrayDeque<>();

        queue.add(new Item(begin, 0));
        while (!queue.isEmpty()) {
            Item item = queue.poll();
            String word = item.word;
            int count = item.count;

            if (word.equals(target)) {
                answer = item.count;
                break;
            }

            for (int i = 0; i < wordsLength; i++) {
                if (used[i])
                    continue;
                if (isDifferentOnlyOneChar(item.word, words[i], wordLength)) {
                    used[i] = true;
                    queue.add(new Item(words[i], count + 1));
                }
            }
        }
        return answer;
    }

    private boolean isDifferentOnlyOneChar(String a, String b, int len) {
        int diff = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != (b.charAt(i)))
                diff++;
        }
        return diff == 1;
    }

    private class Item {
        String word;
        int count;
        Item(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}