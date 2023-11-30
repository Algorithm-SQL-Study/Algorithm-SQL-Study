import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[] scoville, int K) {
        int count = 0;
        Queue<Long> heap = new PriorityQueue<>();
        
        for (int s : scoville) {
            heap.add((long) s);
        }
        
        while (heap.peek() < K) {
            if (heap.size() < 2) return -1;
            long first = heap.poll();
            long second = heap.poll();
            long mixed = first + (second * 2);
            heap.add(mixed);
            count++;
        }
        
        return count;
    }
}
