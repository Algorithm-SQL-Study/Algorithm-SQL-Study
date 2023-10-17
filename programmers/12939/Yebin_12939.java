import java.util.Arrays;

class Solution {
    public String solution(String s) {
        String answer = "";

        String[] tokens = s.split(" ");

        int tlength = tokens.length;
        int[] nums = new int[tlength];
        for (int i=0; i<tlength; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(nums);

        return nums[0] + " " + nums[tlength-1];
    }


}