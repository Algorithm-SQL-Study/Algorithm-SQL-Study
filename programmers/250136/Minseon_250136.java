/**
 * == 250136. 석유 시추 ==
 * 입력 : 석유가 묻힌 땅과 석유 덩어리를 나타내는 배열 land
 * 출력 : 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량
 */

import java.util.*;

class Solution {
    Map<Integer, Integer> oilMap = new HashMap<>(); // 열 별로 뽑을 수 있는 석유량
    int n, m;

    public int solution(int[][] land) {
        m = land.length; n = land[0].length;

        /* bfs */
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j, land, visited);
            }
        }

        /* 열 별로 시추 */
        int maxOil = 0;
        for (int i = 0; i < n; i++) {
            maxOil = Math.max(maxOil, oilMap.getOrDefault(i, 0));
        }

        return maxOil;
    }

    /* bfs */
    public void bfs(int x, int y, int[][] land, boolean[][] visited) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y));
        visited[x][y] = true;

        Set<Integer> cols = new HashSet<>();    // 덩어리에 포함된 열
        int oil = 0;                            // 덩어리 크기

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            oil++; cols.add(now.y);

            for (int d = 0; d < 4; d++) {
                int xx = now.x + dx[d];
                int yy = now.y + dy[d];
                if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
                    if (visited[xx][yy] || land[xx][yy] == 0) continue;
                    queue.offer(new Node(xx, yy));
                    visited[xx][yy] = true;
                }
            }
        }

        /* 덩어리에 포함된 열마다 석유량 더하기 */
        for (int col : cols) {
            oilMap.put(col, oilMap.getOrDefault(col, 0) + oil);
        }
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}