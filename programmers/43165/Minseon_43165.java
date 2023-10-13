/**
 * == 43165. 타겟넘버 ==
 *  * 입력: 숫자가 담긴 배열 numbers, 타겟 넘버 target
 *  * 출력: 타겟 넘버를 만드는 방법의 수
 */

class Solution {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    public void dfs(int[] numbers, int target, int depth, int sum) {
        if (depth == numbers.length) {
            if (sum == target) answer++;
            return;
        }
        dfs(numbers, target, depth+1, sum+numbers[depth]);
        dfs(numbers, target, depth+1, sum-numbers[depth]);
    }
}