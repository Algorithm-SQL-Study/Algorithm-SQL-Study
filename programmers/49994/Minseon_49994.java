/**
 * == 49994. 방문 길이 ==
 * 입력 : 캐릭터에게 주어진 명령어 dirs
 * 출력 : 캐릭터가 처음 걸어본 길의 길이
 */

import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(String dirs) {
        List<String> visited = new ArrayList<>();
        int x = 0; int y = 0; int xx = 0; int yy = 0;

        for (int i = 0; i < dirs.length(); i++) {
            switch (dirs.charAt(i)) {
                case 'U':
                    if (x < 5) {
                        xx = x + 1; yy = y;
                    }
                    break;
                case 'D':
                    if (x > -5) {
                        xx = x - 1; yy = y;
                    } break;
                case 'R':
                    if (y < 5) {
                        xx = x; yy = y + 1;
                    } break;
                case 'L':
                    if (y > -5) {
                        xx = x; yy = y - 1;
                    } break;
            }

            if (x == xx && y == yy) continue;   // 이동하지 못한 경우

            String path = String.format("%d %d %d %d", x, y, xx, yy);
            String reversePath = String.format("%d %d %d %d", xx, yy, x, y);

            // 역방향으로도 지나간 적이 없으면 추가
            if (!visited.contains(path)
                    && !visited.contains(reversePath)) visited.add(path);

            x = xx; y = yy;
        }

        return visited.size();
    }
}