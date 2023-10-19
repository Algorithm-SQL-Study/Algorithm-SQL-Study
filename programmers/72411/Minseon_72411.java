/**
 * == 72411. 메뉴 리뉴얼 ==
 * 입력 : 손님별 주문한 단품 메뉴 조합 배열 orders, 코스요리를 구성하는 메뉴 개수 배열 course
 * 출력 : 새로 추가하게 될 코스요리의 메뉴 구성 배열
 */

import java.util.*;
import java.util.HashMap;
import java.util.Map;

class Solution {
    ArrayList<String>[] courses = new ArrayList[11];  // 메뉴 개수 별 코스 요리
    ArrayList<String> answer = new ArrayList<>();     // 최종 메뉴 구성

    public String[] solution(String[] orders, int[] course) {
        /* 메뉴 개수 별 만들 수 있는 코스 요리 */
        for (int depth : course) {
            List<String> menus = new ArrayList<>();

            // 메뉴 개수 별 가능한 모든 코스 요리 조합
            for (String order : orders) {
                boolean[] visited = new boolean[order.length()];
                combination(order, depth, 0, "", visited, menus);
            }

            // 빈도수 세기
            findMostFrequencyMenu(menus);
        }

        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }

    private void combination(String order, int depth, int idx, String menu, boolean[] visited, List<String> menus) {
        if (depth == 0) {
            // 문자열 정렬
            char[] chars = menu.toCharArray();
            Arrays.sort(chars);
            menus.add(new String(chars));
            return;
        }

        for (int i = idx; i < order.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(order, depth-1, i+1, menu + order.charAt(i), visited, menus);
                visited[i] = false;
            }
        }
    }

    private void findMostFrequencyMenu(List<String> menus) {
        Map<String, Integer> menuMap = new HashMap<>();
        int maxFrequency = 0;

        // 빈도수 저장
        for (String menu : menus) {
            int frequency = menuMap.getOrDefault(menu, 0) + 1;
            menuMap.put(menu, frequency);

            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        // 가장 높은 빈도수 최종 메뉴에 넣기
        for (Map.Entry<String, Integer> entry : menuMap.entrySet()) {
            if (entry.getValue() == maxFrequency && maxFrequency >= 2) {
                answer.add(entry.getKey());
            }
        }
    }
}