/**
 * == 68646. 풍선 터트리기 ==
 * 입력 : 번호가 써있는 풍선 배열 a
 * 출력 : 최후까지 터지지 않고 남을 수 있는 풍선의 개수
 */

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] leftMin = new int[n];   // 해당 인덱스 왼쪽에서의 최솟값
        int[] rightMin = new int[n];  // 해당 인덱스 오른쪽에서의 최솟값

        /* 현재 인덱스 기준 왼쪽만 봤을 때 최솟값 */
        leftMin[0] = a[0];          // 가장 왼쪽 -> 자신이 최솟값
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }

        /* 현재 인덱스 기준 오른쪽만 봤을 때 최솟값*/
        rightMin[n-1] = a[n-1];     // 가장 오른쪽 -> 자신이 최솟값
        for (int i = n-2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] <= leftMin[i] || a[i] <= rightMin[i]) answer++;
        }
        return answer;
    }
}