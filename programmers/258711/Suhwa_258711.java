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