/**
 * == 118669. 등산코스 정하기 ==
 * 입력: XX산의 지점 수 n, 각 등산로의 정보 2차원 배열 paths, 출입구 번호 배열 gates, 산봉우리 번호 배열 summits
 * 출력: intensity가 최소가 되는 등산코스에 포함된 산봉우리 번호, intensity의 최솟값
 */

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        /* 출입구, 산봉우리 해시로 만들기 */
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap = arrToHash(hashMap, gates, "gate");
        hashMap = arrToHash(hashMap, summits, "summit");

        /* 인접 리스트 생성 */
        List<List<Edge>> adjList = makeAdjList(n, paths, hashMap);

        /* 다익스트라 */
        int ans_summit = Integer.MAX_VALUE;     // 산봉우리 번호
        int ans_intensity = Integer.MAX_VALUE;  // intensity 최솟값
        for (int gate : gates) {
            int[] intensities = new int[n+1];       // 최단 intensity 테이블
            boolean[] visited = new boolean[n+1];   // 방문 여부 체크

            Arrays.fill(intensities, Integer.MAX_VALUE);  // INF로 초기화
            intensities[gate] = 0; visited[gate] = true;

            int[] ans = dijkstra(gate, adjList, intensities, visited, summits, hashMap); // 다익스트라 : [summit, intensity] return

            /* intensity 최솟값, 산봉우리 번호 구하기 */
            if (ans[1] < ans_intensity) {
                ans_intensity = ans[1];
                ans_summit = ans[0];
            } else if (ans[1] == ans_intensity && ans[0] < ans_summit) {
                ans_summit = ans[0];
            }
        }

        int[] answer = {ans_summit, ans_intensity};
        return answer;
    }

    /* array to hash */
    public Map<Integer, String> arrToHash(Map<Integer, String> hashMap, int[] arr, String value) {
        for (int point : arr) {
            hashMap.put(point, value);
        }

        return hashMap;
    }

    public List<List<Edge>> makeAdjList(int n, int[][] paths, Map<Integer, String> hashMap) {
        List<List<Edge>> adjList = new ArrayList<>();

        /* 초기화 */
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        /* 간선 추가 */
        // - 출입구 또는 산봉우리가 포함된 경우 단방향 그래프
        // - start와 end가 모두 출입구 또는 산봉우리인 경우 간선 추가 X
        for (int[] path : paths) {
            int start = path[0]; int end = path[1]; int weight = path[2];
            if (hashMap.get(start) == "gate" && hashMap.get(end) == "gate") continue;
            if (hashMap.get(start) == "summit" && hashMap.get(end) == "summit") continue;

            if (hashMap.get(start) == "gate" || hashMap.get(end) == "summit") adjList.get(start).add(new Edge(end, weight));
            else if (hashMap.get(start) == "summit" || hashMap.get(end) == "gate") adjList.get(end).add(new Edge(start, weight));
            else {
                adjList.get(start).add(new Edge(end, weight));
                adjList.get(end).add(new Edge(start, weight));
            }
        }

        return adjList;
    }

    public int[] dijkstra(int start, List<List<Edge>> adjList, int[] intensities, boolean[] visited, int[] summits, Map<Integer, String> hashMap) {
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};  // [summit, intensity]
        int[] points = new int[visited.length]; // 현재 지점까지 도달하는 최대 intensities
        PriorityQueue<Edge> heapq = new PriorityQueue<>();  // weight > summit 번호를 기준으로 한 우선순위 큐
        for (Edge edge : adjList.get(start)) { // 출발 지점의 인접 노드 추가
            heapq.add(edge);
            points[edge.end] = Math.max(points[start], edge.weight);
        }

        while (!heapq.isEmpty()) {
            Edge now = heapq.poll();
            visited[now.end] = true;
            intensities[now.end] = Math.min(intensities[now.end], points[now.end]);
            if (hashMap.get(now.end) == "summit") break;    // 산봉우리면 break

            for (Edge edge : adjList.get(now.end)) {
                if (!visited[edge.end]) {
                    heapq.add(edge);
                    points[edge.end] = Math.max(points[now.end], edge.weight);
                }
            }
        }

        /* 현재 출입구에서 최소 intensity 값을 가지는 산봉우리 구하기 */
        for (int summit : summits) {
            if (intensities[summit] < answer[1]) {
                answer[1] = intensities[summit];
                answer[0] = summit;
            } else if (intensities[summit] == answer[1]) {
                answer[0] = summit < answer[0] ? summit : answer[0];
            }
        }

        return answer;
    }

    /* 가중치가 있는 간선 */
    static class Edge implements Comparable<Edge>{
        int end;        // 도착
        int weight;     // 가중치

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        // 가중치, 지점 번호 순으로 비교 (낮은 것이 우선)
        @Override
        public int compareTo(Edge edge) {
            int result = Integer.compare(this.weight, edge.weight);
            if (result == 0) result = Integer.compare(this.end, edge.end);
            return result;
        }
    }
}