import java.util.*;


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> anwerList = new ArrayList<>();

        Integer allPubCount=0;
        while(allPubCount<progresses.length){
            //더하기
            for(int i=0; i<progresses.length ;i++){
                if(progresses[i]>=100 || progresses[i]==-1){
                    continue;
                }
                progresses[i]+=speeds[i];
            }

            //배포하기
            Integer tempPubCount=0;
            for(int i=0; i<progresses.length ;i++){
                if(progresses[i]==-1){
                    continue;
                }
                if(progresses[i]>=100){
                    tempPubCount+=1;
                    progresses[i]=-1;
                }
                else{
                    break;
                }
            }
            if(tempPubCount!=0){
                allPubCount+=tempPubCount;
                anwerList.add(tempPubCount);
            }
        }

        int[] answer = anwerList.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}