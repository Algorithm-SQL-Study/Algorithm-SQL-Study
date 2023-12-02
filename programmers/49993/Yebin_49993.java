import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        int m = skill.length();
        Map<Character, Integer> skillOrder = new HashMap<>();
        for (int i = 0; i < m; i++) {
            skillOrder.put(skill.charAt(i), i);
        }
        
        for (String course : skill_trees) {
            if (isPossible(skillOrder, course)) answer++;
        }
        
        return answer;
    }
    
    private boolean isPossible(Map<Character, Integer> skillOrder, String course) {
        System.out.println(course);
        int n = course.length();
        
        int temp = 0;
        for (int i = 0; i < n; i++) {
            char c = course.charAt(i);
            if (!skillOrder.containsKey(c)) continue;
            if (skillOrder.get(c) > temp) return false;
            temp++;
        }
        
        return true;
    }
}
