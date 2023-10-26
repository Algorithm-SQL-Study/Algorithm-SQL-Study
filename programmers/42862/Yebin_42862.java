import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int lcount = lost.length;
        int rcount = reserve.length;
        int answer = n - lcount;

        Arrays.sort(lost);
        Arrays.sort(reserve);

        // 도난 당한 학생이 여벌 체육복을 가지고 있는 경우
        for (int l = 0; l < lcount; l++) {
            for (int r = 0; r < rcount; r++) {
                if (reserve[r] == lost[l]) {
                    reserve[r] = -1;
                    lost[l] = -1;
                    answer++;
                    break;
                }
            }
        }

        // 도난 당한 학생이 체육복을 빌리는 경우
        for (int l = 0; l < lcount; l++) {
            for (int r = 0; r < rcount; r++) {
                // (앞사람 <- 뒷사람) || (앞사람 -> 뒷사람)
                if (lost[l] == reserve[r]-1 || lost[l] == reserve[r]+1) {
                    reserve[r] = -1;
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}