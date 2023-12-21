import java.util.Arrays;

class Solution {
    public int solution(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, 1);
        for (int i = 1; i < n; i++) {
            int temp = i;
            for (int j = i + 1; j < n + 1; j++) {
                temp += j;
                if (temp > n) break;
                memo[temp]++;
            }
        }
        
        return memo[n];
    }
}
