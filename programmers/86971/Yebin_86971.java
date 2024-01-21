// https://school.programmers.co.kr/learn/courses/30/lessons/86971
// 전력망을 둘로 나누기

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;


class Solution {
    public int solution(int n, int[][] wires) {
        // 인접 리스트 그래프
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] wire : wires) {
            int v1 = wire[0] - 1;
            int v2 = wire[1] - 1;
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        
        // 하나씩 끊어보고 차이의 최솟값 구하기
        int minDiff = 101;
        for (int[] wire : wires) {
            // 끊어진 전선
            int v1 = wire[0] - 1;
            int v2 = wire[1] - 1;
            
            boolean[] visited = new boolean[n];
            List<Integer> nodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                int count = dfs(i, v1, v2, visited, graph);
                nodes.add(count);
            }
            
            // 차이
            if (nodes.size() != 2) continue;
            
            int diff = Math.abs(nodes.get(0) - nodes.get(1));
            
            minDiff = Math.min(diff, minDiff);
        }
        
        return minDiff;
    }
    
    private int dfs(int start, int v1, int v2, boolean[] visited, List<List<Integer>> graph) {
        int count = 0;
        
        Stack<Integer> stack = new Stack<>();
        visited[start] = true;
        stack.push(start);
        
        while (!stack.empty()) {
            int node = stack.pop();
            for (Integer neighbor : graph.get(node)) {
                if (visited[neighbor]) continue;
                if ((node == v1 && neighbor == v2) || (node == v2 && neighbor == v1)) continue;
                visited[neighbor] = true;
                stack.push(neighbor);
                count++;
            }
        }
        
        return count;
    }
}
