import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int second = 0;
        Queue<String> cache = new LinkedList<>();

        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                second += 1;
            } else {
                if (cache.size() >= cacheSize) {
                    cache.poll();
                }
                cache.add(city);
                second += 5;
            }
        }

        return second;
    }
}