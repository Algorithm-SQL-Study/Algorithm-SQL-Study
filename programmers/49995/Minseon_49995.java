/**
 * == 49995. 쿠키 구입 ==
 * 입력 : 각 바구니 안에 들은 과자 수가 차례로 들은 배열 cookie
 * 출력 : 각 아들에게 줄 수 있는 가장 많은 과자 수
 */

class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;

        /* 전체 과자 수 */
        int cookies = 0;
        for (int i = 0; i < n; i++) {
            cookies += cookie[i];
        }
        cookies /= 2;   // 한 명이 최대로 가질 수 있는 과자 수

        /* 바구니 나누기 */
        int maxSum = 0;                     // 과자 수의 최댓값
        for (int m = 0; m < n-1; m++) {
            int l = m; int r = m+1;         // 첫째: l ~ m, 둘째: m+1 ~ r
            int leftSum = cookie[l]; int rightSum = cookie[r];  // 첫째, 둘째 과자 수 누적합
            while (l >= 0 && r < n &&
                    leftSum <= cookies && rightSum <= cookies) {
                if (leftSum == rightSum) {
                    maxSum = Math.max(maxSum, leftSum);
                    // 누적합이 같은 경우가 또 나올 수 있음
                    if (l-1 >= 0) leftSum += cookie[l-1]; l--;
                    if (r+1 < n) rightSum += cookie[r+1]; r++;
                } else if (leftSum < rightSum) {
                    if (l-1 >= 0) leftSum += cookie[l-1]; l--;
                } else {
                    if (r+1 < n) rightSum += cookie[r+1]; r++;
                }
            }
        }

        return maxSum;
    }
}