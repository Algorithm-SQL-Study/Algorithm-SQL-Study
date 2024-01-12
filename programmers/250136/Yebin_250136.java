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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1) dfs(land, i, j, oilId--);
            }
        }
        
        return calculateMaxOil();
    }
    
    private void dfs(int[][] land, int x, int y, int oilId) {
        land[x][y] = oilId;
        oilList.get(y).add(oilId);
        oilReserves.put(oilId, oilReserves.getOrDefault(oilId, 0) + 1);
        
        for (int i = 0; i < 4; i++) {
            int nx = x + diff[i][0];
            int ny = y + diff[i][1];
            
            if (nx < 0 || nx > n-1 || ny < 0 || ny > m-1 
               || land[nx][ny] == 0 || land[nx][ny] != 1) continue;
            
            dfs(land, nx, ny, oilId);
        }
    }
    
    private int calculateMaxOil() {
        int answer = 0;
        for (Set<Integer> col : oilList) {
            if (col.isEmpty()) continue;
            
            int temp = 0;
            for (int oilId : col) temp += oilReserves.get(oilId);
            
            answer = Math.max(temp, answer);
        }
        
        return answer;
    }
}
