/**
 * == 42862. 체육복 ==
 * 입력: 전체 학생 수 n, 도난당한 학생들 lost, 여분이 있는 학생들 reserve
 * 출력: 체육수업을 들을 수 있는 학생의 최댓값
 */

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {

        int[] clothes = new int[n];

        for (int i = 0; i < n; i++) {
            clothes[i] = 1;
        }

        for (int i = 0; i < lost.length; i++) {
            int idx = lost[i] - 1;
            clothes[idx] -= 1;
        }

        for (int i = 0; i < reserve.length; i++) {
            int idx = reserve[i] - 1;
            clothes[idx] += 1;
        }

        for (int i = 0; i < n; i++) {
            if (clothes[i] == 0) {
                if (i-1 >= 0 && clothes[i-1] == 2) {
                    clothes[i-1] -= 1;
                    clothes[i] += 1;
                } else if (i+1 < n && clothes[i+1] == 2) {
                    clothes[i+1] -= 1;
                    clothes[i] += 1;
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (clothes[i] > 0) answer += 1;
        }

        return answer;
    }
}