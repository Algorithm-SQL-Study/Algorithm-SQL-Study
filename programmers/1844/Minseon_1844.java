/**
 * == 1844. 게임 맵 최단거리 ==
 * 입력 : 게임 맵의 상태 maps
 * 출력 : 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        int n = maps.length;                // target n
        int m = maps[0].length;             // target m
        Node start = new Node(0, 0, 1);     // start
        boolean[][] visited = new boolean[n][m];

        /* bfs */
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(start);
        visited[0][0] = true;

        while (!deque.isEmpty()) {
            Node now = deque.poll();
            if (now.x == n-1 && now.y == m-1) return now.depth;
            for (int i = 0; i < 4; i++) {
                int xx = now.x + dx[i];
                int yy = now.y + dy[i];
                if (xx >= 0 && xx < n && yy >= 0 && yy < m
                        && maps[xx][yy] == 1 && !visited[xx][yy]) {
                    Node next = new Node(xx, yy, now.depth+1);
                    deque.add(next);
                    visited[xx][yy] = true;
                }
            }
        }

        return -1;
    }
}

class Node {
    int x;
    int y;
    int depth;

    public Node(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}