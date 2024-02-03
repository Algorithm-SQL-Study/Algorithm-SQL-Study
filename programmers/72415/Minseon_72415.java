/**
 * == 72415. 카드 짝 맞추기 ==
 * 입력 : 현재 카드가 놓인 상태 board, 커서의 처음 위치 r, c
 * 출력 : 모든 카드를 제거하기 위한 키 조작 횟수
 */

import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;     // 최소 키 조작 횟수
    int total = 0;                      // 카드 종류 개수
    int[][] graph;
    List<List<int[]>> indexList = new ArrayList<>();

    public int solution(int[][] board, int r, int c) {
        graph = board;

        /* 카드 쌍 별 좌표 저장 */
        for (int i = 0; i <= 6; i++) {
            indexList.add(new ArrayList<>());
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) continue;
                indexList.get(board[i][j]).add(new int[] {i, j});
                total++;
            }
        }

        total /= 2;
        orderCards(0, 0, r, c);
        return answer;
    }

    /* 경우의 수 */
    public void orderCards(int cnt, int moves, int r, int c) {
        if (cnt == total) {
            answer = Math.min(answer, moves);
            return;
        }

        for (int i = 1; i <= 6; i++) {
            if (indexList.get(i).size() == 0) continue;

            int[] start = indexList.get(i).remove(1);
            int[] end = indexList.get(i).remove(0);
            int startToEnd = bfs(r, c, start[0], start[1]) + bfs(start[0], start[1], end[0], end[1]) + 2;
            int endToStart = bfs(r, c, end[0], end[1]) + bfs(end[0], end[1], start[0], start[1]) + 2;

            graph[start[0]][start[1]] = 0;
            graph[end[0]][end[1]] = 0;
            if (startToEnd < endToStart) orderCards(cnt+1, moves+startToEnd, end[0], end[1]);
            else orderCards(cnt+1, moves+endToStart, start[0], start[1]);
            graph[start[0]][start[1]] = i;
            graph[end[0]][end[1]] = i;
            indexList.get(i).add(start);
            indexList.get(i).add(end);
        }
    }

    /* 최단거리 구하기 */
    public int bfs(int r, int c, int targetR, int targetC) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Queue<Card> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][4];
        queue.offer(new Card(r, c, 0));
        while (!queue.isEmpty()) {
            Card cur = queue.poll();
            visited[cur.r][cur.c] = true;

            if (cur.r == targetR && cur.c == targetC) return cur.cnt;

            /* 이동 */
            for (int i = 0; i < 4; i++) {
                // 방향키
                int x = cur.r + dx[i];
                int y = cur.c + dy[i];
                if (x >= 0 && x < 4 && y >= 0 && y < 4 && !visited[x][y]) queue.offer(new Card(x, y, cur.cnt+1));

                // Ctrl + 방향키
                x = cur.r;
                y = cur.c;
                while (true) {
                    x += dx[i];
                    y += dy[i];

                    if (x < 0 || x >= 4 || y < 0 || y >= 4) {
                        x -= dx[i];
                        y -= dy[i];
                        break;
                    }

                    if (graph[x][y] != 0) break;
                }
                if (!visited[x][y]) queue.offer(new Card(x, y, cur.cnt+1));
            }
        }
        return 0;
    }
}

class Card {
    int r;
    int c;
    int cnt;

    public Card(int r, int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}