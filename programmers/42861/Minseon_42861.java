/**
 * == 42861. 섬 연결하기 ==
 * 입력 : 섬의 개수 n, 간선 정보 costs
 * 출력 : 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용
 */

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int n, int[][] costs) {
        /* 그래프 정렬 */
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2])); // 비용 순

        /* init parent */
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        /* 크루스칼 */
        int answer = 0;
        for (int[] edge : costs) {
            if (find(edge[0], parent) != find(edge[1], parent)) {   // 연결되지 않은 경우
                union(edge[0], edge[1], parent);
                answer += edge[2];
            }
        }
        return answer;
    }

    /* find parent */
    private static int find(int x, int[] parent) {
        if (parent[x] != x) parent[x] = find(parent[x], parent);
        return parent[x];
    }

    /* union parent */
    private static void union(int x, int y, int[] parent) {
        x = find(x, parent);
        y = find(y, parent);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}