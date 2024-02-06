// https://school.programmers.co.kr/learn/courses/30/lessons/49994
class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int n = 10;
        int x = 5, y = 5;
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        boolean[][][][] visited = new boolean[n + 1][n + 1][n + 1][n + 1];

        for (char dir : dirs.toCharArray()) {
            int i = "UDRL".indexOf(dir);
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > n || ny < 0 || ny > n) continue;

            if (!visited[x][y][nx][ny] && !visited[nx][ny][x][y]) {
                answer++;
                visited[x][y][nx][ny] = true;
                visited[nx][ny][x][y] = true;
            }

            x = nx;
            y = ny;
        }

        return answer;
    }
}
