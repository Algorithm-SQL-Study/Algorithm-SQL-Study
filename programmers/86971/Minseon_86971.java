/**
 * == 86971. 전력망을 둘로 나누기 ==
 * 입력 : 송전탑의 개수 n, 전선 정보 wires
 * 출력 : 전선 하나를 끊었을 때 두 전력망이 가지고 있는 송전탑 개수의 차이의 최솟값
 */

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 100;

        for (int i = 0; i < wires.length; i++) {
            List<List<Integer>> graph = initGraph(n);
            boolean[] visited = new boolean[n+1];
            int towers1 = 0; int towers2 = 0;

            makeGraph(graph, wires, i);
            towers1 = getTowers(wires[i][0], 0, visited, graph);
            towers2 = getTowers(wires[i][1], 0, visited, graph);

            int diff = Math.abs(towers1 - towers2);
            answer = diff < answer ? diff : answer;
        }

        return answer;
    }

    /* 전력망이 가지고 있는 송전탑의 개수 */
    public int getTowers(int v, int towers, boolean[] visited, List<List<Integer>> graph) {
        visited[v] = true; towers++;

        for (int w : graph.get(v)) {
            if (visited[w]) continue;
            else towers = getTowers(w, towers, visited, graph);
        }

        return towers;
    }

    /* 그래프 초기화 */
    public List<List<Integer>> initGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        return graph;
    }

    /* 끊은 전선을 제외한 전력망 */
    public void makeGraph(List<List<Integer>> graph, int[][] wires, int w) {
        for (int i = 0; i < wires.length; i++) {
            if (i == w) continue;
            graph.get(wires[i][0]).add(wires[i][1]);
            graph.get(wires[i][1]).add(wires[i][0]);
        }
    }
}