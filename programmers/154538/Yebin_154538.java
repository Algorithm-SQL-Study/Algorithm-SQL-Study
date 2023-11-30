import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        int[] memo = new int[y + 1];
        Arrays.fill(memo, -1);
        memo[x] = 0;
        for (int i = x; i <= y; i++) {
            if (memo[i] == -1) continue;
            
            int temp = i + n;
            if (temp <= y) {
                if (memo[temp] == -1) memo[temp] = memo[i] + 1;
                else memo[temp] = Math.min(memo[temp], memo[i] + 1);
            }
            temp = i * 2;
            if (temp <= y) {
                if (memo[temp] == -1) memo[temp] = memo[i] + 1;
                else memo[temp] = Math.min(memo[temp], memo[i] + 1);
            }
            temp = i * 3;
            if (temp <= y) {
                if (memo[temp] == -1) memo[temp] = memo[i] + 1;
                else memo[temp] = Math.min(memo[temp], memo[i] + 1);
            }
        }
        
        return memo[y];
    }
}
