class Solution {
    // 1. 응시자가 보이는 대로 맨해튼거리2 안에 있는지 확인 
    // 2. 거리두기 미준수 발견 시 바로 그 대기실 탐색 멈춤 
    // 2.1 거리가 1 (상하좌우)
    // 2.2 거리가 2 (대각선, 상하좌우+2)
    // 2.2.1 단 사이에 벽이 있는 경우 괜찮음
    
    private int N = 5;
    private int DIR = 4;
    private int[][] m1 = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};  // 직선
    private int[][] m2 = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};// 대각선
    
    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        
        // place 마다 안전한지 확인 
        for (int i = 0; i < N; i++) {
            if (isSafe(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }
    
    private boolean isSafe(String[] place) {
        // 사람이 있는 곳으로부터 맨허튼 거리 2 이내에 사람이 있는지 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (place[i].charAt(j) != 'P') continue;
                if (!checkDistance(place, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean checkDistance(String[] place, int x, int y) {
        for (int i = 0; i < DIR; i++) {
            // 직선 1인 곳이 P이면 안됨
            int nx = x + m1[i][0];
            int ny = y + m1[i][1];
            if (!isValid(nx, ny)) continue;
            if (place[nx].charAt(ny) == 'P') return false;
            // 직선 1인 곳이 X가 아닌데 직선 2인 곳이 P이면 안됨
            int nx2 = x + 2 * m1[i][0];
            int ny2 = y + 2 * m1[i][1];
            if (!isValid(nx2, ny2)) continue;
            if (place[nx].charAt(ny) != 'X' && place[nx2].charAt(ny2) == 'P') return false;
            // 대각선이 P면 옆이 X여야 함
            int dx = x + m2[i][0];
            int dy = y + m2[i][1];
            if (!isValid(dx, dy)) continue;
            if (place[dx].charAt(dy) == 'P') {
                if (place[dx].charAt(y) != 'X' || place[x].charAt(dy) != 'X') return false;
            }
        }
        return true;
    }
    
    private boolean isValid(int x, int y) {
        return !(x < 0 || x > N - 1 || y < 0 || y > N - 1);
    }
}
