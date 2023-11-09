/**
 * == 118667. 두 큐의 합 같게 만들기 ==
 * 입력 : 길이가 같은 두 개의 큐
 * 출력 : 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수
 */

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> deque1 = new ArrayDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();

        /* 각 큐의 원소 합 구하기 */
        long targetSum = 0;     // 목표합

        for (int num : queue1) {
            targetSum += (long) num;
            deque1.add(num);
        }
        for (int num : queue2) {
            targetSum += (long) num;
            deque2.add(num);
        }

        if (targetSum % 2 == 1) return -1;  // 모든 원소의 합이 홀수이면 같게 할 수 없음
        targetSum /= 2;

        /* 각 큐의 원소 합 같게 만들기 */
        int count = 0;  // 작업 횟수
        long queueSum = getSum(deque1);
        while (queueSum != targetSum) {
            if (count > (queue1.length) * 4) return -1; // 최대 횟수 이상 옮기면 같게 할 수 없음
            if (deque1.size() == 0 || deque2.size() == 0) return -1;    // 한 큐가 빈 큐가 되면 같게 할 수 없음
            if (queueSum > targetSum) {
                queueSum -= (long) deque1.peekFirst();
                count = popAndInsert(deque1, deque2, count, targetSum);
            }
            else {
                queueSum += (long) deque2.peekFirst();
                count = popAndInsert(deque2, deque1, count, targetSum);
            }

            if (count == -1) break;
        }

        return count;
    }

    private long getSum(Deque<Integer> queue) {
        long queueSum = 0;
        for (int num : queue) {
            queueSum += num;
        }
        return queueSum;
    }

    private int popAndInsert(Deque<Integer> queue1, Deque<Integer> queue2, int count, long targetSum) {
        int popNum = queue1.removeFirst();  // pop
        if (popNum > targetSum) return -1;  // 원소가 목표합보다 크면 같게 할 수 없음
        queue2.add(popNum);                 // insert
        return ++count;
    }
}