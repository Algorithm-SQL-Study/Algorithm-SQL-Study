import java.util.*;


class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, List<List<Integer>>> graph = new HashMap<>();;
        // 노드이름 [[이 노드가 end인 간선의 start],[이 노드가 start인 간선의 end]]


        //1. 그래프생성
        for(int[] edge :edges){
            int start = edge[0];
            int end = edge[1];


            if(graph.containsKey(start)){
                List<Integer> endEdgeList = graph.get(start).get(1);
                endEdgeList.add(end);
            } else{
                List<List<Integer>> nodeList = new ArrayList<>();
                List<Integer> startEdgeList = new ArrayList<>();
                List<Integer> endEdgeList = new ArrayList<>();
                nodeList.add(startEdgeList);
                nodeList.add(endEdgeList);
                endEdgeList.add(end);
                graph.put(start,nodeList);


            }


            if(graph.containsKey(end)){
                List<Integer> startEdgeList = graph.get(end).get(0);
                startEdgeList.add(start);
            }else{
                List<List<Integer>> nodeList = new ArrayList<>();
                List<Integer> startEdgeList = new ArrayList<>();
                List<Integer> endEdgeList = new ArrayList<>();
                nodeList.add(startEdgeList);
                nodeList.add(endEdgeList);
                startEdgeList.add(start);
                graph.put(end,nodeList);
            }


        }




        //2. 생성노드 구하기
        answer[0] = findGenerateNode(graph);

        //3. 생성노드 제외한 그래프 bfs로 탐색
        int[] result = bfsGraph(answer[0], graph);
        answer[1]=result[0];
        answer[2]=result[1];
        answer[3]=result[2];


        return answer;
    }

    public int findGenerateNode(Map<Integer, List<List<Integer>>> graph){
        // 하나의 정점 나오는 endList는 존재하나 다른 정점에서 해당 정점으로 오는 startList는 없음
        for(int key : graph.keySet()) {
            List<List<Integer>> value = graph.get(key);
            if(value.get(0).size()==0){
                return key;
            }
        }
        return -9;
    }


    public int[] bfsGraph(int generatedNode, Map<Integer, List<List<Integer>>> graph){

        // 첫번째 풀이 방법의 패착
        // donut, bar, 8의 차이점을 노드개수와 간선의 개수 비율로 두려고했으나
        // bfs로 탐색시 일방향 그래프이기에 bar의 경우 하나의 그래프로 인식이 안되버림...
        int[] result = new int[3];
        int donut =0;
        int bar =0;
        int e=0;

        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visit = new boolean[1000002];

        for(int key : graph.keySet()){
            int nodeCount=0;
            int edgeCount=0;

            if(!visit[key]&&key!=generatedNode){
                q.offer(key);
                visit[key]=true;
            }

            while(!q.isEmpty()){
                int now = q.poll();
                nodeCount+=1;
                edgeCount+=graph.get(now).get(1).size();
                if(graph.get(now).get(1).contains(generatedNode)){
                    edgeCount-=1;
                }


                System.out.println("노드 : "+now);
                System.out.println("노드 개수: "+nodeCount);
                System.out.println("간선 개수 : "+edgeCount);


                for(int i : graph.get(now).get(1)){
                    if(!visit[i]&&key!=generatedNode){
                        q.offer(i);
                        visit[i]=true;
                    }
                }
            }
            if(checkDonutGraph(nodeCount, edgeCount)){
                donut+=1;
            }else{
                if(checkBarGraphCountOR8GraphCount(key, nodeCount,edgeCount)){
                    bar+=1;
                }
                e+=1;
            }

        }

        result[0]=donut;
        result[1]=bar;
        result[2]=e;


        return result;
    }

    public boolean checkDonutGraph(int nodeCount, int edgeCount){
        //도넛은 node 개수 = edge 개수
        return nodeCount == edgeCount;
    }

    public boolean checkBarGraphCountOR8GraphCount(int start, int nodeCount, int edgeCount){
        //true -> barGraph, false -> 8Graph

        if(nodeCount%2==0){
            return true;
        }


        //홀수면, 모든 앞,뒤가 1:1이면 8그래프

        return true;


    }


}

//해설을 봤습니다.... 발상은 비슷한데 구체화가 다르군아.....
//    1. 들어오는 간선이 존재하지 않고, 나가는 간선이 2개 이상인 정점을 찾습니다. 이러한 정점은 하나만 존재하며, 해당 정점이 생성된 정점입니다.
//    2. 생성된 정점과 연결된 간선의 개수를 셉니다. 해당 간선들의 개수는 모양 그래프의 총개수와 동일합니다.
//     3.   생성된 정점과 연결된 간선을 모두 삭제합니다.
//       4. 들어오는 간선이 없는 정점의 개수 혹은 나가는 간선이 없는 정점의 개수를 셉니다. 해당 정점들은 각 막대 모양 그래프마다 하나씩만 존재하기 때문에 해당 정점들의 개수는 막대 모양 그래프의 개수와 동일합니다.
//        들어오는 간선이 2개이면서 나가는 간선이 2개인 정점의 개수를 셉니다. 해당 정점들은 각 8자 모양 그래프마다 하나씩만 존재하기 때문에, 해당 정점들의 개수는 8자 모양 그래프의 개수와 동일합니다.
//        모양 그래프의 총 개수에서 4번과 5번에서 센 정점들의 개수를 뺍니다. 구한 값은 도넛 모양 그래프의 개수와 동일합니다.
//        1번에서 생성된 정점의 번호를 찾을 수 있고, 4, 5, 6번에서 각각 막대 모양 그래프의 개수, 8자 모양 그래프의 개수, 도넛 모양 그래프의 개수를 구할 수 있습니다. 이 경우 시간 복잡도 O(V+E)에 문제 해결이 가능합니다.
//
//        또한, 위 방법 외에도 각 그래프의 사이클 수로 모양 판단하기, 정점과 간선의 수로 모양 판단하기 등 BFS와 DFS를 이용한 여러 가지 풀이가 존재합니다. 이때, 정점과 간선의 개수가 많기 때문에 O(V^2) 혹은 O(E^2)과 같은 큰 시간 복잡도로 코드를 구현하지 않도록 유의해야 합니다.