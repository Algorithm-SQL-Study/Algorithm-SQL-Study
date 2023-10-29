import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        //배열을 최소 heap에 넣기

        List<Integer> minHeap = Solution.minHeap();
        for ( int s : scoville){
            Solution.insert(minHeap,s);
        }
        //작은것 끼리 섞기
        while(minHeap.get(1)<K && minHeap.size() >= 3 ){
            answer++;
            int firstNonSpicy = Solution.delete(minHeap);
            int secondNonSpicy = Solution.delete(minHeap);
            int newSchoville = Solution.mix(firstNonSpicy,secondNonSpicy);
            Solution.insert(minHeap, newSchoville);
        }
        if(minHeap.get(1)<K && minHeap.size() < 3 ){
            answer=-1;
        }

        return answer;

    }

    public static Integer mix(int firstNonSpicy, int secondNonSpicy){
        return Integer.valueOf(firstNonSpicy + (secondNonSpicy*2));
    }

    public static List<Integer> minHeap(){
        List<Integer> heap = new ArrayList<Integer>();
        heap.add(0); //0번째 인덱스를 사용안함
        return heap;
    }

    public static void insert(List<Integer> heap, Integer val){
        heap.add(Integer.valueOf(val));
        int p = heap.size()-1;
        while(p >1 && heap.get(p/2) > heap.get(p)){
            int tmp = heap.get(p/2);
            heap.set(p/2,val);
            heap.set(p, tmp);

            p/=2;
        }
    }

    public static Integer delete(List<Integer> heap){
        int lastIndex = heap.size() -1;
        if(lastIndex <1){
            return -99;
        }

        int deleteItem = heap.get(1);

        heap.set(1,heap.get(lastIndex));
        heap.remove(lastIndex);

        int tmp = 1;
        while((tmp*2)<heap.size()) {
            int left = heap.get(tmp*2);
            int leftIndex = tmp*2;

            if((tmp*2+1) < heap.size() && left > heap.get(tmp*2+1)){
                left = heap.get(tmp*2 +1);
                leftIndex = tmp*2+1;
            }

            if(left > heap.get(tmp)){
                break;
            }

            int exchange = heap.get(tmp);
            heap.set(tmp, heap.get(leftIndex));
            heap.set(leftIndex,exchange);
            tmp = leftIndex;

        }

        return Integer.valueOf(deleteItem);
    }
}