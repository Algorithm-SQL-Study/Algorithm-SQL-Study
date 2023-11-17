import java.util.Queue;
import java.util.PriorityQueue;

class Solution
{
    public int solution(int []A, int []B)
    {
        int n = A.length;
        // A 에서 가장 작은 수, B 에서 가장 큰 수
        Queue<Integer> qa = new PriorityQueue<>();
        Queue<Integer> qb = new PriorityQueue<>((a,b) -> Integer.compare(b, a));

        for (int i = 0; i < n; i++) {
            qa.add(A[i]);
            qb.add(B[i]);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int a = qa.poll();
            int b = qb.poll();
            answer += a * b;
        }

        return answer;
    }
}