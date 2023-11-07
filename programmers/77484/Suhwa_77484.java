import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {0,0};

        //lottos중 확실하게 아는 숫자들
        int[] definiteNums = Arrays.stream(lottos).filter(l -> l!=0).toArray();
        int possibleNum = lottos.length - definiteNums.length;

        //lottos중 0이아닌 숫자와 win_nums사이의 맞는 숫자 개수가 중요
        int matchNum=0;
        for(int i =0 ; i < win_nums.length;i++){
            if(matchNum == definiteNums.length){
                break;
            }
            for(int j =0 ; j < definiteNums.length; j++){
                if(win_nums[i] == definiteNums[j]){
                    matchNum++;
                }
            }
        }


        answer[0]=(goodExpectLotto(matchNum,possibleNum));
        answer[1]=(badExpectLotto(matchNum));

        return answer;
    }

    public int goodExpectLotto(int matchNum, int possibleNum){
        switch(matchNum + possibleNum){
            case 6 : return 1;
            case 5 : return 2;
            case 4 : return 3;
            case 3 : return 4;
            case 2 : return 5;
            default : return 6;
        }
    }

    public int badExpectLotto(int matchNum){
        switch(matchNum){
            case 6 : return 1;
            case 5 : return 2;
            case 4 : return 3;
            case 3 : return 4;
            case 2 : return 5;
            default : return 6;
        }
    }
}