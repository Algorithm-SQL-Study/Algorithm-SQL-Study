import java.util.ArrayList;
import java.util.List;

class Solution {
    List<int[]> answer = new ArrayList<>();

    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        int asize = answer.size();
        int[][] result = new int[asize][2];

        for (int i = 0; i < asize; i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private void hanoi(int n, int from, int mid, int to) {
        if (n == 1) {   // 재귀 멈춤 조건
            answer.add(new int[] {from, to});
        } else {
            hanoi(n - 1, from, to, mid);        // (n-1)을 [from -> mid]로 옮김
            answer.add(new int[] {from, to});   // (n)을 [from -> to]로 옮김
            hanoi(n - 1, mid, from, to);        // (n-1)을 [mid -> to]로 옮김
        }
    }
}
