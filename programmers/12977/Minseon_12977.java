/**
 * == 12977. 소수 만들기 ==
 * 입력 : 숫자 배열 nums
 * 출력 : nums의 원소 3개를 골라 더했을 때 소수가 되는 경우의 수
 */

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int size = nums.length;

        /* 숫자 3개 조합 구하기 */
        for (int i = 0; i < size; i++) {
            for (int j = i+1; j < size; j++) {
                for (int k = j+1; k < size; k++) {
                    if (isPrimeNumber(nums[i]+nums[j]+nums[k])) answer++;  // 소수 판별
                }
            }
        }

        return answer;
    }

    /* 소수 판별 */
    private boolean isPrimeNumber(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}