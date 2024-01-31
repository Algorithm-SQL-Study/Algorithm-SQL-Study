// https://school.programmers.co.kr/learn/courses/30/lessons/181188

import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int answer = 0;
        int before = 0;
        for(int[] target: targets) {
        	if(target[0] >= before) {
        		before = target[1];
        		answer++;
        	}
        }
        return answer;
    }
}
