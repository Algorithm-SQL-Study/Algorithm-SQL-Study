/**
 * == 86053. 금과 은 운반하기 ==
 * 입력 : 필요한 금 무게 a, 은 무게 b, 각 도시의 금 보유량 배열 g, 은 보유량 배열 s, 트럭의 최대 적재량 배열 w, 편도 이동 시간 배열 t
 * 출력 : 새로운 도시에 금과 은을 전달할 수 있는 가장 빠른 시간
 */

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = (long) 10e14 * 4L;     // (2*10e9) * (2*10e5)

        while (left < right) {
            long mid = (left + right) / 2L;
            if (isPossible(mid, a, b, g, s, w, t)) right = mid;
            else left = mid + 1;
        }

        return right;
    }

    public boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        /* 새로운 도시에 갖다 놓은 무게 */
        long total = 0;         // 총 무게
        long totalGold = 0;     // 금 무게
        long totalSilver = 0;   // 은 무게

        for (int i = 0; i < g.length; i++) {
            /* 가능한 이동 횟수 */
            long moves = time / (2L * t[i]);
            if (time % (2L * t[i]) >= t[i]) moves++;

            /* 옮길 수 있는 무게 */
            total += Math.min(moves * w[i], g[i]+s[i]);
            totalGold += Math.min(moves * w[i], g[i]);
            totalSilver += Math.min(moves * w[i], s[i]);
        }

        if (total >= a+b && totalGold >= a && totalSilver >= b) return true;
        else return false;
    }
}