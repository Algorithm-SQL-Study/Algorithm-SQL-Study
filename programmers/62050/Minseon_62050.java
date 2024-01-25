/**
 * == 62050. 지형 이동 ==
 * 입력 : 각 격자칸의 높이가 담긴 배열 land, 이동 가능한 최대 높이차 height
 * 출력 : 모든 칸을 방문하기 위해 필요한 사다리 설치 비용의 최솟값
 */

import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    int n;
    boolean[][] visited;
    PriorityQueue<Land> heapq = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.cost));
    int[][] landList;

    public int solution(int[][] land, int height) {
        n = land.length;    landList = land;
        visited = new boolean[n][n];

        int totalCost = 0;              // 사다리 설치 비용
        addLand(0, 0, height);     // (0,0)에서 갈 수 있는 칸
        visited[0][0] = true;
        while(!heapq.isEmpty()) {
            Land now = heapq.poll();
            if (visited[now.x][now.y]) continue; // 이미 방문한 곳이면 continue

            /* 비용 계산 및 이동 */
            totalCost += now.cost;
            visited[now.x][now.y] = true;
            addLand(now.x, now.y, height);
        }

        return totalCost;
    }

    /* 현재 갈 수 있는 칸 */
    public void addLand(int i, int j, int height) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int d = 0; d < 4; d++) {
            int x = i + dx[d];
            int y = j + dy[d];
            if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
                int diff = Math.abs(landList[x][y] - landList[i][j]);
                if (diff <= height) heapq.add(new Land(x, y, 0));
                else heapq.add(new Land(x, y, diff));   // 사다리 설치
            }
        }
    }
}

class Land {
    int x;
    int y;
    int cost;

    public Land(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}