import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Integer[] convert1 = Arrays.stream( queue1 ).boxed().toArray( Integer[]::new );
        Integer[] convert2 = Arrays.stream( queue2 ).boxed().toArray( Integer[]::new );

        Queue<Integer> Queue1 = new LinkedList<>();
        Queue<Integer> Queue2 = new LinkedList<>();
        Collections.addAll(Queue1, convert1);
        Collections.addAll(Queue2, convert2);

        long Queue1Sum = Queue1.stream().mapToLong(w -> w).sum();
        long Queue2Sum = Queue2.stream().mapToLong(w -> w).sum();
        long QueueSum = Queue1Sum+Queue2Sum;


        if(QueueSum%2 != 0){
            return -1;
        }


        while(Queue1Sum != Queue2Sum){
            if(Queue1Sum > Queue2Sum){
                Node result = operation(Queue1,Queue2);
                answer += result.answer;
                Queue1Sum-=result.gap;
                Queue2Sum+=result.gap;
            }
            else{
                Node result =operation(Queue2,Queue1);
                answer += result.answer;
                Queue1Sum+=result.gap;
                Queue2Sum-=result.gap;
            }
            //System.out.println(Queue1Sum +",   "+Queue2Sum);
            if(answer > Queue1.size()+Queue2.size()+10){
                return -1;
            }

        }


        return answer;
    }

    public Node operation(Queue<Integer> pollQueue, Queue<Integer> addQueue){
        Integer temp1 = pollQueue.poll();
        addQueue.add(temp1);
        return new Node(1,temp1);
    }

    class Node {
        public int answer;
        public int gap;

        Node (int answer, int gap) {
            this.answer = answer;
            this.gap = gap;
        }
    }
}