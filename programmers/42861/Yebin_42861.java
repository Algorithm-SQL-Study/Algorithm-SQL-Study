import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        if (n == 1) return 0;
        
        // 비용 기준으로 정렬
        Arrays.sort(costs, (o1, o2) -> { return o1[2]-o2[2]; });
        
        // 부모(집합)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // 크루스칼 알고리즘 
        int minimumCost = 0;
        for (int[] cost : costs) {
            int u = cost[0];
            int v = cost[1];
            int c = cost[2];
            
            if (!isUnion(parent, u, v)) { // 같은 집합이 아니어야 사이클을 생성하지 않음
                union(parent, u, v);
                minimumCost += c;
            }
        }

        return minimumCost;
    }
    
    private int find(int[] parent, int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent, parent[a]);
    }
    
    private void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b) parent[b] = a;
        else if (a > b) parent[a] = b;
    }
    
    private boolean isUnion(int[] parent, int a, int b) {
        return find(parent, a) == find(parent, b);
    }
}
