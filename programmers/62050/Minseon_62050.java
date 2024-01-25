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
            Land now = heapq.poll();    // start -> end, 비용
            int[] start = now.start; int[] end = now.end;
            if (visited[end[0]][end[1]]) continue;  // 이미 방문한 곳이면 continue

            /* 비용 계산 및 이동 */
            totalCost += now.cost;
            visited[end[0]][end[1]] = true;
            addLand(end[0], end[1], height);
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
                int[] s = {i, j};   // start
                int[] e = {x, y};   // end

                if (diff <= height) heapq.add(new Land(s, e, 0));
                else heapq.add(new Land(s, e, diff));   // 사다리 설치
            }
        }
    }
}

class Land {
    int[] start;    // 출발 지점
    int[] end;      // 도착 지점
    int cost;       // 비용

    public Land(int[] start, int[] end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}