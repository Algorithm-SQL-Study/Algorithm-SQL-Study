/**
 * == 43164. 여행경로 ==
 * 입력 : 항공권 정보 배열 tickets
 * 출력 : 방문하는 공항 경로
 */

import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> airportMap = new HashMap<>();

    public String[] solution(String[][] tickets) {
        for (String[] ticket : tickets) {
            if (!airportMap.containsKey(ticket[0]))
                airportMap.put(ticket[0], new PriorityQueue<>());
            if (!airportMap.containsKey(ticket[1]))
                airportMap.put(ticket[1], new PriorityQueue<>());
            airportMap.get(ticket[0]).add(ticket[1]);
        }

        Deque<String> answer = dfs("ICN", new ArrayList<>());
        return answer.toArray(new String[0]);
    }

    public List<String> dfs(String key, List<String> airports) {
        if (airportMap.get(key).isEmpty()) {    // 이용 가능한 항공권이 없는 경우 현재 공항 리턴
            return new ArrayList<>(List.of(key));
        }

        airports.add(key);          // 현재 공항 경로에 추가
        List<String> routes = dfs(airportMap.get(key).poll(), new ArrayList<>()); // 현재 공항에서 갈 수 있는 경로
        if (!airportMap.get(key).isEmpty()) {   // 다른 경로가 있다는 것은 곧 순환 경로가 있다는 의미
            List<String> cycleRoute = dfs(airportMap.get(key).poll(), new ArrayList<>());  // 현재 공항에서 갈 수 있는 사이클을 이루는 다른 경로
            airports.addAll(cycleRoute);
        }

        airports.addAll(routes);    // 경로 합치기
        return airports;
    }
}