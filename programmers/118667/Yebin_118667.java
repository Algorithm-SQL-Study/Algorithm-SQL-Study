import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length * 2;  // 총 원소 개수

        Queue<Integer> q1 = arrayToQ(queue1);
        Queue<Integer> q2 = arrayToQ(queue2);
        long sum1 = sumQ(q1);
        long sum2 = sumQ(q2);

        // 합이 홀수이면 두 큐를 같게 만들 수 없음
        if (((sum1 + sum2) % 2) != 0) return -1;

        // 각각의 합이 같아질 때까지
        int count = 0;
        while (sum1 != sum2) {
            if (sum1 > sum2) {
                int ele = q1.poll();
                q2.add(ele);
                sum1 -= ele;
                sum2 += ele;
            } else {
                int ele = q2.poll();
                q1.add(ele);
                sum2 -= ele;
                sum1 += ele;
            }
            count++;
            // 모든 원소가 다른 큐에 갔다가 원래 큐로 돌아올 만큼 움직였는데 같아지지 않음
            if (count > 2 * n) return -1;
        }

        return count;
    }

    private Queue<Integer> arrayToQ(int[] queue) {
        Queue<Integer> q = new LinkedList<>();
        for (int element : queue) q.add(element);
        return q;
    }

    private long sumQ(Queue<Integer> q) {
        long result = 0;
        for (Integer element : q) result += element;
        return result;
    }
}