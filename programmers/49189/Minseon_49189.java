/**
 * == 49189. 가장 먼 노드 ==
 * 입력: 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 edge
 * 출력: 가장 멀리 떨어진 노드의 개수
 */

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        /* 그래프 만들기 */
        List<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edge) {
            int v1 = e[0];
            int v2 = e[1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        /* 가장 멀리 떨어진 노드의 거리 구하기 */
        int maxDepth = 0;
        int[] depths = bfs(n, graph);

        for (int i = 1; i <= n; i++) {
            maxDepth = Math.max(maxDepth, depths[i]);
        }

        /* 가장 멀리 떨어진 노드 개수 */
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (depths[i] == maxDepth) answer++;
        }

        return answer;
    }

    public int[] bfs(int n, List<Integer>[] graph) {
        boolean[] visited = new boolean[n+1];
        int[] depths = new int[n+1];   // 1에서 각 노드로의 최단 거리 저장
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;
        depths[1] = 0;

        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                int now = queue.poll();

                for (Integer v : graph[now]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.add(v);
                        depths[v] = depths[now] + 1;
                    }
                }
            }
        }

        return depths;
    }
}