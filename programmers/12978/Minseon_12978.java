/**
 * == 12978. 배달 ==
 * 입력 : 마을의 개수 N, 도로의 정보 road, 음식 배달이 가능한 시간 K
 * 출력 : 음식 주문을 받을 수 있는 마을의 개수
 */

import java.util.Arrays;

class Solution {
    public int solution(int N, int[][] road, int K) {
        /* 인접행렬 */
        int[][] graph = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }

        for (int[] r : road) {
            int a = r[0]; int b = r[1]; int c = r[2];
            graph[a][b] = Math.min(graph[a][b], c);
            graph[b][a] = Math.min(graph[b][a], c);
        }

        /* 플로이드-워셜 */
        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    if (graph[i][k] != Integer.MAX_VALUE
                            && graph[k][j] != Integer.MAX_VALUE
                            && graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int answer = 0; // K시간 이하에 배달 가능한 마을 개수
        for (int i = 1; i < N+1; i++) {
            if (graph[1][i] <= K) answer++;
        }

        return answer;
    }
}