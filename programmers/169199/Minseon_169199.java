/**
 * == 169199. 리코쳇 로봇 ==
 * 입력 : 게임판의 상태 배열 board
 * 출력 : 말이 목표 위치에 도달하는 최소 이동 횟수
 */

import java.util.*;

class Solution {
    public int solution(String[] board) {
        /* 시작 위치 구하기 */
        int startX = 0; int startY = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    startX = i; startY = j; // 시작 좌표
                }
            }
        }
        
        int answer = -1;
        
        /* 말 이동 */
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        ArrayDeque<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[board.length][board[0].length()];
        
        queue.add(new Point(startX, startY, 0));
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            visited[now.x][now.y] = true;
            if (board[now.x].charAt(now.y) == 'G') {
                answer = now.cnt;
                break;
            }
                
            /* 방향 설정 */
            for (int i = 0; i < 4; i++) {
                int x = now.x; int y = now.y;
                
                if (x + dx[i] < 0 || x + dx[i] >= board.length ||
                   y + dy[i] < 0 || y + dy[i] >= board[0].length()) continue;
                
                if (board[x + dx[i]].charAt(y + dy[i]) == 'D') continue;
                
                while(x + dx[i] >= 0 && 
                      x + dx[i] < board.length && 
                      y + dy[i] >= 0 && 
                      y + dy[i] < board[0].length() &&
                     board[x + dx[i]].charAt(y + dy[i]) != 'D') {
                    x += dx[i]; y += dy[i];     // 장애물을 만날 때까지 직진
                }
                
                if (!visited[x][y]) queue.add(new Point(x, y, now.cnt+1));
            }
        }
        
        return answer;
    }
    
    public boolean isImpossible(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                if (!visited[i][j]) return false;
            }
        }
        return true;
    }
}

class Point {
    int x;
    int y;
    int cnt;
    
    public Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
