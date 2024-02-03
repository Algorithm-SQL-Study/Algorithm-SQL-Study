// https://school.programmers.co.kr/learn/courses/30/lessons/68645
class Solution {
    public int[] solution(int n) {
        int[][] triangle = new int[n][n]; // 삼각형 담을 배열
        int[] answer = new int[n * (n + 1) / 2]; // 답 배열

        int value = 1; // 시작값
        int x = 0, y = 0; // 달팽이 시작점

        // 삼각형 만들기
        while (n > 0) {
            // 아래로 n번 이동
            for (int i = 0; i < n; i++) {
                triangle[x++][y] = value++;
            }
            x--;
            y++;

            // 오른쪽으로 n-1번 이동
            for (int i = 0; i < n - 1; i++) {
                triangle[x][y++] = value++;
            }
            x--;
            y -= 2;

            // 대각선 위로 n-2번 이동
            for (int i = 0; i < n - 2; i++) {
                triangle[x--][y--] = value++;
            }
            x += 2;
            y++;
            
            // 한 레이어 끝
            n -= 3;
        }

        // 배열에 담기
        int index = 0;
        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                answer[index++] = triangle[i][j];
            }
        }
        
        return answer;
    }
}
