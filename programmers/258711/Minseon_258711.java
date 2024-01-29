/**
 * == 258711. 도넛과 막대 그래프 ==
 * 입력 : a -> b로 향하는 간선 정보 배열 edges
 * 출력 : 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수
 */

class Solution {
    public int[] solution(int[][] edges) {
        /* 정점 개수 구하기 */
        int maxVertex = 0;
        for (int[] edge : edges) {
            maxVertex = Math.max(maxVertex, edge[0]);
            maxVertex = Math.max(maxVertex, edge[1]);
        }

        /* 간선 정보 저장 */
        int[] enterEdges = new int[maxVertex+1];  // 들어오는 간선 개수
        int[] leaveEdges = new int[maxVertex+1];  // 나가는 간선 개수
        for (int[] edge : edges) {
            int a = edge[0]; int b = edge[1];
            enterEdges[b] += 1;
            leaveEdges[a] += 1;
        }

        int[] answer = new int[4];  // 생성한 정점, 도넛, 막대, 8자

        /* 생성한 정점 찾기, 그래프별 개수 구하기 */
        for (int i = 1; i <= maxVertex; i++) {
            if (enterEdges[i] == 0 && leaveEdges[i] >= 2) {
                answer[0] = i;   // 생성한 정점: 들어오는 간선 X, 그래프 2개 이상
            } else if (enterEdges[i] > 1 && leaveEdges[i] > 1) {
                answer[3] += 1;  // 8자: 들어오는 간선과 나가는 간선 모두 2개 이상인 노드 1개 존재
            } else if (leaveEdges[i] == 0) {
                answer[2] += 1;  // 막대: 나가는 간선이 없는 노드 1개 존재
            }
        }

        // 도넛: 전체 그래프 개수 - 막대 모양 개수 - 8자 모양 개수
        answer[1] = leaveEdges[answer[0]] - answer[2] - answer[3];

        return answer;
    }
}