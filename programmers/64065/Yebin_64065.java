import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        String[] sets = s.split("\\},\\{");

        Arrays.sort(sets, (s1, s2) ->
                    Integer.compare(s1.length(), s2.length()));

        List<Integer> result = new ArrayList<>();

        for (String set : sets) {
            String[] elements = set.split(",");
            for (String element : elements) {
                int num = Integer.parseInt(element);
                if (!result.contains(num)) {
                    result.add(num);
                    break;
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
