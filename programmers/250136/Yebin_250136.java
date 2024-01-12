// https://school.programmers.co.kr/learn/courses/30/lessons/250136
// [PCCP 기출문제] 2번 / 석유 시추
import java.util.*;

class Solution {
    private int[][] diff = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private List<Set<Integer>> oilList;
    private Map<Integer, Integer> oilReserves;
    private int n;
    private int m;

    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;

        oilList = new ArrayList<>();
        for (int j = 0; j < m; j++) oilList.add(new HashSet<>());
        oilReserves = new HashMap<>();

        int oilId = -1;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (land[i][j] == 1) {
                    dfs(land, i, j, oilId);
                    oilId--;
                }
            }
        }

        return calculateMaxOil();
    }

    private void dfs(int[][] land, int startX, int startY, int oilId) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];

            if (land[x][y] != 1) continue;

            land[x][y] = oilId;
            oilList.get(y).add(oilId);
            oilReserves.put(oilId, oilReserves.getOrDefault(oilId, 0) + 1);

            for (int i = 0; i < 4; i++) {
                int nx = x + diff[i][0];
                int ny = y + diff[i][1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && land[nx][ny] == 1) {
                    stack.push(new int[]{nx, ny});
                }
            }
        }
    }

    private int calculateMaxOil() {
        int answer = 0;
        for (Set<Integer> col : oilList) {
            if (!col.isEmpty()) {
                int temp = 0;
                for (int oilId : col) {
                    temp += oilReserves.get(oilId);
                }
                answer = Math.max(temp, answer);
            }
        }

        return answer;
    }
}
