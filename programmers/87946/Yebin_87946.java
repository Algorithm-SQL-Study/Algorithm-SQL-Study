class Solution {
    public int solution(int k, int[][] dungeons) {
        int d = dungeons.length;
        boolean[] visited = new boolean[d];
        
        return bruteforce(k, 0, visited, dungeons);
    }
    
    private int bruteforce(int hp, int count
                   , boolean[] visited
                   , int[][] dungeons) {
        int temp = count;
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            int minhp = dungeons[i][0];
            int usehp = dungeons[i][1];
            if (hp < minhp) continue;
            visited[i] = true;
            temp = Math.max(bruteforce(hp - usehp, count + 1, visited, dungeons)
                            , temp);
            visited[i] = false;
        }
        return Math.max(count, temp);
    }
}
