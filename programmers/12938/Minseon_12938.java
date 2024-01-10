/**
 * == 12938. 최고의 집합 ==
 * 입력 : 집합의 원소 수 n, 모든 원소의 합 s
 * 출력 : 각 원소의 합이 s가 되면서 곱이 최대가 되는 집합
 */

class Solution {
    public int[] solution(int n, int s) {
        /* 최고의 집합을 만들 수 없는 경우 */
        if (s/n < 1) {
            int[] answer = {-1};
            return answer;
        };

        /* 최고의 집합 구하기 */
        int[] answer = new int[n];
        int idx = 0;
        while (n > 0) {
            answer[idx++] = s/n;    // 최대한 s/n에 가까운 수를 넣는 것이 최고의 집합을 만들 수 있음
            s -= s/n;
            n--;
        }

        return answer;
    }
}