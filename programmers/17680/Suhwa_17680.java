import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();

        for ( String city : cities){
            answer += cacheSearch(city.toLowerCase(), cache, cacheSize);
        }


        return answer;
    }

    public int cacheSearch(String city,LinkedList<String> cache, int cacheSize ){

        int hitIndex = cache.indexOf(city);
        if(hitIndex == -1){
            // cache miss -> return 5. cache 젤앞에 시티, 젤뒤 하나없애기
            cache.addFirst(city);
            if(cache.size() > cacheSize){
                cache.removeLast();
            }
            return 5;


        }
        else{
            //만약 cachehit -> return 1. cache젤앞에 hit한거 옮기기
            cache.remove(hitIndex);
            cache.addFirst(city);
            return 1;

        }


    }
}