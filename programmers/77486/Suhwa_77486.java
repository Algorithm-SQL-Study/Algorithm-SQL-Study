import java.util.*;


class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        //searchPerson을 위한 시간복잡도 줄이기용
        HashMap<String,Integer> searchEnroll = new HashMap<String,Integer>();
        for(Integer i=0 ; i<enroll.length ; i++){
            searchEnroll.put(enroll[i],i);
        }



        for(int i =0 ; i<seller.length ; i++){
            distributeSellingAmount(amount[i]*100, searchPerson(seller[i], searchEnroll),
                    searchEnroll,referral,answer);

        }

        return answer;
    }


    public int searchPerson(String searchPerson, HashMap<String,Integer> searchEnroll){

        Integer result = searchEnroll.get(searchPerson);
        if(result ==null){
            return -1;
        }
        return result;

    }

    public void distributeSellingAmount(int sellingAmount, int startIndex,
                                        HashMap<String,Integer> searchEnroll,
                                        String[] referral,int[] answer){
        int distributedSellingAmount;
        int introducerIndex=-1;
        while(startIndex!=-1){

            //현재판매자에게 돈급여
            distributedSellingAmount = calculate10Percent(sellingAmount);
            answer[startIndex] += sellingAmount-distributedSellingAmount;

            /*
            / 힌트로 추가한것
             */
            if(distributedSellingAmount==0){
                break;
            }


            //소개인 인덱스
            startIndex = searchPerson(referral[startIndex], searchEnroll);
            sellingAmount = distributedSellingAmount;
        }



    }

    public int calculate10Percent(int sellingAmount){
        return sellingAmount/10;
    }
}

