/**
 * == 12939. 최댓값과 최솟값 ==
 * 입력 : 2개 이상의 정수로 이루어진 문자열 s
 * 출력 : 최솟값과 최댓값
 */

import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] numStrs = s.split(" ");
        int[] nums = new int[numStrs.length];

        for (int i = 0; i < numStrs.length; i++) {
            nums[i] = Integer.parseInt(numStrs[i]);
        }

        Arrays.sort(nums);

        return nums[0] + " " + nums[nums.length-1];
    }
}