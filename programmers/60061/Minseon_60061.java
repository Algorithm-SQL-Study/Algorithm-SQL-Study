/**
 * == 60061. 기둥과 보 설치 ==
 * 입력 : 벽면의 크기 n, 작업의 배열 build_frame
 * 출력 : 모든 명령어를 수행한 후 구조물의 상태
 */

class Solution {
    boolean[][] pillars;    // 기둥
    boolean[][] bridges;    // 보
    int cnt = 0;

    public int[][] solution(int n, int[][] build_frame) {
        pillars = new boolean[n+1][n+1];
        bridges = new boolean[n+1][n+1];

        /* 작업 */
        for (int[] build : build_frame) {
            int x = build[0]; int y = build[1];
            if (build[3] == 1) buildPB(x, y, build[2]); // 설치
            else removePB(x, y, build[2]);              // 삭제
        }

        /* 모든 작업이 끝난 결과 */
        int[][] answer = new int[cnt][3];
        int idx = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillars[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx++][2] = 0;
                }
                if (bridges[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx++][2] = 1;
                }
            }
        }
        return answer;
    }

    /* 규칙에 맞는가? */
    public boolean isBuildable(int x, int y, int pOrB) {
        int n = pillars.length;
        if (pOrB == 0){     // 기둥
            if (y+1 > n) return false;
            if (y == 0 ||
                    (x > 0 && bridges[x-1][y]) ||
                    (x+1 <= n && bridges[x][y]) ||
                    (y > 0 && pillars[x][y-1])) return true;
        } else {            // 보
            if (x+1 > n) return false;
            if ((y > 0 && (pillars[x][y-1] || pillars[x+1][y-1])) ||
                    (x > 0 && bridges[x-1][y] && bridges[x+1][y])) return true;
        }
        return false;
    }

    /* 설치 */
    public void buildPB(int x, int y, int pOrB) {
        if (pOrB == 0 && isBuildable(x, y, pOrB)){          // 기둥
            pillars[x][y] = true;
            cnt++;
        } else if (pOrB == 1 && isBuildable(x, y, pOrB)){   // 보
            bridges[x][y] = true;
            cnt++;
        }
    }

    /* 삭제 */
    public void removePB(int x, int y, int pOrB) {
        int n = pillars.length;
        if (pOrB == 0) {                // 기둥
            pillars[x][y] = false;      // 삭제
            if ((x+1 <= n && pillars[x][y+1] && !isBuildable(x, y+1, 0))
                    || (x > 0 && bridges[x-1][y+1] && !isBuildable(x-1, y+1, 1))
                    || (y+1 < n && bridges[x][y+1] && !isBuildable(x, y+1, 1))) {
                pillars[x][y] = true;   // 주변 설치물이 조건에 안 맞으면 복원
                return;
            }
            cnt--;
        } else {                        // 보
            bridges[x][y] = false;      // 삭제
            if ((pillars[x][y] && !isBuildable(x, y, 0) )
                    || (x+1 < n && pillars[x+1][y] && !isBuildable(x+1, y, 0))
                    || (x > 0 && bridges[x-1][y] && !isBuildable(x-1, y, 1))
                    || (x+1 < n && bridges[x+1][y] && !isBuildable(x+1, y, 1))) {
                bridges[x][y] = true;   // 주변 설치물이 조건에 안 맞으면 복원
                return;
            }
            cnt--;
        }
    }
}