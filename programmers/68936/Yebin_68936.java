import java.util.*;

class Solution {
    int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2]; // [0의 개수, 1의 개수]
        int n = arr.length;
        compression(arr, 0, 0, n);
        return answer;
    }
    
    private void compression(int[][] arr, int r, int c, int size) {
        boolean canCompress = true;
        int quad = arr[r][c];
        for (int x = r; x < r + size; x++) {
            for (int y = c; y < c + size; y++) {
                if (arr[x][y] != quad) {
                    canCompress = false;
                    break;
                }
            }
            if (!canCompress) break;
        }
        
        if (canCompress) {
            answer[quad]++;
            return;
        }
        
        int newSize = size / 2;
        compression(arr, r, c, newSize);
        compression(arr, r, c + newSize, newSize);
        compression(arr, r + newSize, c, newSize);
        compression(arr, r + newSize, c + newSize, newSize);
    }
}
