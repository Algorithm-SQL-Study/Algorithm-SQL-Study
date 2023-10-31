import java.util.*;

class Solution {
    List<List<Integer>> graph;
    List<Integer> counts = new ArrayList<>();

    public int solution(String begin, String target, String[] words) {
        int V = words.length;

        /* init graph */
        graph = new ArrayList<>(V+1);
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        /* 인접 리스트 만들기 */
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (isDifferent(words[i], words[j])) graph.get(i).add(j);
            }
        }

        /* dfs */
        int answer = V + 1;
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (isDifferent(begin, words[i])) {
                if (words[i].equals(target)) return 1;
                answer = Math.min(answer, dfs(i, target, 1, words, visited));
            }
        }

        if (answer > V) answer = 0;
        return answer;
    }

    private boolean isDifferent(String word1, String word2) {
        int charDiff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) charDiff++;
        }
        if (charDiff == 1) return true;
        else return false;
    }

    private int dfs(int v, String target, int depth, String[] words, boolean[] visited) {
        if (words[v].equals(target) || visited[v]) {
            return depth;
        }

        visited[v] = true;

        int cnt = 0;
        for (int w : graph.get(v)) {
            if (!visited[w]) cnt = dfs(w, target, depth+1, words, visited);
        }

        return cnt;
    }
}