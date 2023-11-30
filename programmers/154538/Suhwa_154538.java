import java.util.*;

class Solution {
    class Node{
        int value;
        int count;
        public Node(int value, int count){
            this.value=value;
            this.count = count;
        }
    }

    public int solution(int x, int y, int n) {
        int answer = 0;

        //BFS
        Queue<Node> queue = new LinkedList<Node>();
        boolean[] visited = new boolean[10000000];

        queue.add(new Node(x,0));
        visited[x]=true;

        while(queue.size()!=0){
            Node nowNode = queue.poll();
            if(nowNode.value==y){
                return nowNode.count;
            }

            checkAndAdd(visited, nowNode.value + n,nowNode.count,y,queue);
            checkAndAdd(visited,nowNode.value *2,nowNode.count,y,queue);
            checkAndAdd(visited,nowNode.value *3,nowNode.count,y,queue);

        }
        //변환할수없는 케이스 찾기어려운듯?
        return -1;
    }

    public void checkAndAdd(boolean[] visited, int newValue,
                            int count, int y ,Queue<Node> queue){
        if(!visited[newValue] && newValue <= y){
            queue.add(new Node(newValue, count +1));
            visited[newValue] = true;
        }
    }

}