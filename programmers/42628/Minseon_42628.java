/**
 * == 42628. 이중우선순위큐 ==
 * 입력 : 이중 우선순위 큐가 할 연산 operations
 * 출력 : 모든 연산을 처리한 후 최댓값, 최솟값
 */

import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    public int[] solution(String[] operations) {
        /* 이중우선순위큐 */
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // min heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());    // max heap

        for (String operation : operations) {
            String command = operation.split(" ")[0];
            int number = Integer.parseInt(operation.split(" ")[1]);

            if (command.equals("I")) minHeap.add(number);   // 삽입
            else if (!minHeap.isEmpty()) {
                if (number == -1) minHeap.poll();  // 최솟값 삭제
                else {  // 최댓값 삭제
                    maxHeap.addAll(minHeap);
                    maxHeap.poll();
                    minHeap.clear();
                    minHeap.addAll(maxHeap);
                }
            }
        }
        maxHeap.addAll(minHeap);

        int[] answer = new int[2];
        answer[0] = maxHeap.isEmpty() ? 0 : maxHeap.poll(); // 최댓값
        answer[1] = minHeap.isEmpty() ? 0 : minHeap.poll(); // 최솟값

        return answer;
    }
}