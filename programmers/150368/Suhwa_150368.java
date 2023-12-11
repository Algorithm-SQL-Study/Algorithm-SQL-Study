import java.util.*;

class Solution {
    class TempEmoji{
        int value;
        int discount;

        public TempEmoji(int value, int discount){
            this.value=value;
            this.discount = discount;
        }

        public int discountedValue(){
            return value/100*(100-discount);
        }
    }


    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {0,0};

        int[] discounts = {10,20,30,40};

        TempEmoji[] tempEmotions = new TempEmoji[emoticons.length];
        combinationEmoji(emoticons.length, discounts, tempEmotions, emoticons,users,answer);


        // 각 이모지마다 할인율넣어 하나씩 뽑기

        //유저에게 검사


        return answer;
    }

    public void combinationEmoji(int recursion,int[] discouts,
                                 TempEmoji[] tempEmotions, int[] emoticons,
                                 int[][] users,int[] answer){
        if(recursion==0){
            calculateUser(users,tempEmotions,answer);
            return;
        }

        int tempIndex = emoticons.length-recursion;
        for(int discount : discouts){
            tempEmotions[tempIndex]= new TempEmoji(emoticons[tempIndex], discount);
            combinationEmoji(recursion-1,discouts,tempEmotions,emoticons,users,answer);
        }
    }


    public void calculateUser(int[][] users,TempEmoji[] tempEmotions ,int[] answer){
        int emojiPlus=0;
        int soldAmount=0;
        int[] userAmount = new int[users.length];

        for(int i =0 ; i< users.length ; i++){
            for(TempEmoji tempEmotion : tempEmotions){
                if(users[i][0]<=tempEmotion.discount){

                    userAmount[i]+=tempEmotion.discountedValue();
                }

            }
        }

        for(TempEmoji tempEmotion : tempEmotions){
            //System.out.println("이모지의 할인률은 "+tempEmotion.discount+" 할인된 가격은 "+ tempEmotion.discountedValue());
        }
        for(int i =0 ; i< users.length ; i++){
            if(users[i][1]<=userAmount[i]){
                emojiPlus+=1;
                userAmount[i]=0;
            }
            soldAmount+=userAmount[i];
        }

        //System.out.println("현재까지 최대 이플 가입자 : "+answer[0]+" 임티매출액 : "+answer[1]);
        //System.out.println("지금의 이플 가입자 : "+emojiPlus+" 임티매출액 : "+soldAmount+"\n");

        if(answer[0]<emojiPlus){
            answer[0]=emojiPlus;
            answer[1] =soldAmount;
        }
        else if(answer[0]==emojiPlus){
            if(answer[1]<soldAmount){
                answer[1] =soldAmount;
            }
        }



    }

    //최적화는 유저별 할인률 이하이면 선택안하게

}