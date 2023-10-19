import java.util.*;


class Solution {
    static Map<Integer, Map<String, Integer>> setMenu;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        setMenu = new HashMap<>();
        for (int c : course) {
            setMenu.put(c, new HashMap<>());
            // 메뉴 조합별 개수 세기
            for (String o : orders) {
                char[] order = o.toCharArray();
                Arrays.sort(order);
                combination(order, 0, c, new StringBuilder());
            }

            // 가장 많이 주문 된 세트
            int maxCount = 0;
            List<String> candidate = new ArrayList<>();
            for (Map.Entry<String, Integer> entry
                    : setMenu.get(c).entrySet()) {

                if (entry.getValue() < 2) continue;

                if (entry.getValue() < maxCount) continue;

                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    candidate.clear();
                }

                candidate.add(entry.getKey());
            }

            for (String s : candidate) {
                answer.add(s);
            }
        }

        // 사전 오름차순
        Collections.sort(answer);

        return answer.toArray(new String[answer.size()]);
    }

    private void combination(char[] order
            , int temp
            , int count
            , StringBuilder sb) {
        if (sb.length() == count) {
            String combi = sb.toString();
            int saved = setMenu.get(count)
                    .getOrDefault(combi, 0);
            setMenu.get(count)
                    .put(combi, saved + 1);
            return;
        }

        for (int i = temp; i < order.length; i++) {
            sb.append(order[i]);
            combination(order, i + 1, count, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}