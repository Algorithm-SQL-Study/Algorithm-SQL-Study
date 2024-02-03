/**
 * == 72412. 순위 검색 ==
 * 입력 : 지원자의 정보와 코딩테스트 점수 문자열 배열 info, 문의조건 문자열 배열 query
 * 출력 : 각 문의조건에 해당하는 사람들의 숫자 배열
 */

import java.util.*;

class Solution {
    Map<String, List<Integer>> infoMap = new HashMap<>();
    int score;

    public int[] solution(String[] info, String[] query) {
        /* 지원자 정보 해시맵 */
        for (String applicant : info) {
            String[] splitedInfo = applicant.split(" ");
            score = Integer.parseInt(splitedInfo[4]);
            dfs(0, splitedInfo, new String[4]);
        }

        /* 지원자 코딩테스트 점수 내림차순 정렬 */
        for (String key : infoMap.keySet()) {
            Collections.sort(infoMap.get(key), Collections.reverseOrder());
        }

        /* 쿼리 */
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] splitedQuery = query[i].split(" ");
            int score = Integer.parseInt(splitedQuery[7]);

            StringBuilder sb = new StringBuilder();
            sb.append(splitedQuery[0]);
            for (int j = 1; j < 7; j++) {
                if (!splitedQuery[j].equals("and")) {
                    sb.append(" ");
                    sb.append(splitedQuery[j]);
                }
            }
            String key = sb.toString();

            if (infoMap.containsKey(key)) {
                List<Integer> scores = infoMap.get(key);
                answer[i] = binarySearch(score, scores);
            }
        }

        return answer;
    }

    /* dfs */
    public void dfs(int depth, String[] splitedInfo, String[] sb) {
        if (depth == 4) {
            String condition = String.join(" ", sb);
            if (!infoMap.containsKey(condition)) infoMap.put(condition, new ArrayList<>());
            infoMap.get(condition).add(score);
        } else {
            sb[depth] = splitedInfo[depth];
            dfs(depth+1, splitedInfo, sb);
            sb[depth] = "-";    // 각 정보를 포함하지 않은 경우도 고려
            dfs(depth+1, splitedInfo, sb);
        }
    }

    /* 이진 탐색 */
    public int binarySearch(int score, List<Integer> list) {
        int left = 0; int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < score) right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }
}
