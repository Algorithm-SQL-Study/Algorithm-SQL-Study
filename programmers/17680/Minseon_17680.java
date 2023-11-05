/**
 * == 17680. 캐시 ==
 * 입력 : 캐시 크기 cacheSize, 도시이름 배열 cities
 * 출력 : 총 실행 시간
 */

import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        /* linked list */
        LinkedList<String> cache = new LinkedList<>();

        /* LRU */
        if (cacheSize == 0) answer = 5 * cities.length; // cache miss
        else {
            for (int i = 0; i < cities.length; i++) {
                String city = cities[i].toUpperCase();

                if (cache.size() < cacheSize) {
                    if (cache.contains(city)) {   // cache hit
                        cache.remove(city);
                        cache.add(city);
                        answer++;
                    } else {                      // cache miss
                        cache.add(city);
                        answer += 5;
                    }
                } else {
                    if (cache.contains(city)) {   // cache hit
                        cache.remove(city);
                        cache.add(city);
                        answer++;
                    } else {                      // cache miss
                        cache.remove(0);
                        cache.add(city);
                        answer += 5;
                    }
                }
            }
        }

        return answer;
    }
}