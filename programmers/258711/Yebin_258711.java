import java.util.*;
// https://school.programmers.co.kr/learn/courses/30/lessons/258711
class Solution {
    public int[] solution(int[][] edges) {
        int maxValue = 0;
        
        for (int[] edge : edges) {
            maxValue = Math.max(Math.max(edge[0], edge[1]), maxValue);
        }
        
        int[] inEdges = new int[maxValue + 1];
        int[] outEdges = new int[maxValue + 1];
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            inEdges[v] += 1;
            outEdges[u] += 1;
        }
        
        int startVertax = 0;
        int donutGraphs = 0;
        int stickGraphs = 0;
        int eightGraphs = 0;
        
        for (int i = 1; i <= maxValue; i++) {
            if (inEdges[i] == 0 && outEdges[i] >= 2) {
                startVertax = i;   
            } else if (inEdges[i] > 1 && outEdges[i] > 1) {
                eightGraphs += 1;  
            } else if (outEdges[i] == 0) {
                stickGraphs += 1;  
            }
        }
        
        donutGraphs = outEdges[startVertax] - stickGraphs - eightGraphs;
        
        return new int[]{startVertax, donutGraphs, stickGraphs, eightGraphs};
    }
}
