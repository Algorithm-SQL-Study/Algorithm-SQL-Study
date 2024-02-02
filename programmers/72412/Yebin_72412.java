// https://school.programmers.co.kr/learn/courses/30/lessons/72412
import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Map<String, List<Integer>> infoMap = new HashMap<>();

        for (String applicant : info) {
            // 지원자 정보 파싱
            String[] infoArray = applicant.split(" ");
            int score = Integer.parseInt(infoArray[4]);
            
            // 0000, 0001, ... , 1111 해당 요소를 사용하거나(1) 사용하지 않는(0) 키를 만듦
            for (int i = 0; i < (1 << 4); i++) {
                StringBuilder keyBuilder = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    if ((i & (1 << j)) > 0) {   // 1인 부분은 사용
                        keyBuilder.append(infoArray[j]);
                    } else {    // 0인 부분은 -으로 처리
                        keyBuilder.append("-");
                    }
                    keyBuilder.append(" ");
                }
                String key = keyBuilder.toString().trim();
                
                infoMap.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            }
        }
        
        // 모든 카테고리에 대해 점수 오름차순 정렬
        for (Map.Entry<String, List<Integer>> entry : infoMap.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        // 쿼리 실행
        for (int i = 0; i < query.length; i++) {
            // 쿼리 파싱
            String[] queryArray = query[i].replaceAll(" and ", " ").split(" ");
            int score = Integer.parseInt(queryArray[queryArray.length - 1]);
            
            // 조건들로 키 만들기
            queryArray[queryArray.length - 1] = "";
            String key = String.join(" ", queryArray).trim();
            
            // 점수 개수 계산
            if (infoMap.containsKey(key)) {
                List<Integer> scores = infoMap.get(key); // 쿼리에 해당하는 점수들 목록
                answer[i] = scores.size() - binarySearch(scores, score); 
            }
        }

        return answer;
    }

    // 정렬된 리스트에서 목표 점수 이상인 위치 반환
    private int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else { // 같거나 큰 곳에 멈춤
                right = mid;
            }
        }

        return left;
    }
}
