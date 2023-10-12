------------- 10.12 오후 1시에 푼 버전(틀림) -------------

import java.util.stream.Collectors;
import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {

        int answer = 0;
        List<Integer> lostList = Arrays.stream(lost)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> reserveList = Arrays.stream(reserve)
                .boxed()
                .collect(Collectors.toList());


        //1. 여별 체육복가진 사람= 도난당한사람 인지 확인
        for(Integer i=1;i<=n;i++){
            if(lostList.contains(i) && reserveList.contains(i)){
                reserveList.remove(Integer.valueOf(i));
                lostList.remove(Integer.valueOf(i));
            }
        }


        for(Integer i=0;i<n;i++){ //첫번째 틀린이유 : index시작을 0 으로 둬서....
            if(lostList.contains(i)&&reserveList.contains(i)){
                // 두번째 틀린이유 : 여벌체육복가진사람=도난당한사람 은 젤 처음에 찾아야함.
                // 반례 : n=8, lost=[4,5], reserve=[5,6]
                reserveList.remove(Integer.valueOf(i));
                lostList.remove(Integer.valueOf(i));
                answer
            }
            else if(lostList.contains(i)){

                if(reserveList.contains(i-1)){
                    reserveList.remove(Integer.valueOf(i-1));
                    answer++;
                }
                else if(reserveList.contains(i+1)){
                    reserveList.remove(Integer.valueOf(i+1));
                    answer++;
                }
                //앞뒤 아무도 없으면 그사람은 수업참여 불가
            }
            else{
                answer++;
            }
        }
        return answer;
    }
}

------------- 10.12 오후 틀린부분 보완해서 맞음 -------------

import java.util.stream.Collectors;
import java.util.*;


class Solution {
    public int solution(int n, int[] lost, int[] reserve) {

        int answer = 0;
        List<Integer> lostList = Arrays.stream(lost)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> reserveList = Arrays.stream(reserve)
                .boxed()
                .collect(Collectors.toList());


        //1. 여별 체육복가진 사람= 도난당한사람 인지 확인
        for(Integer i=1;i<=n;i++){
            if(lostList.contains(i) && reserveList.contains(i)){
                reserveList.remove(Integer.valueOf(i));
                lostList.remove(Integer.valueOf(i));
            }
        }


        for(Integer i=1;i<=n;i++){ //아 미친 1부터 시작인걸 까먹고 0으로 했다가 계속 오류났네 미친
            if(lostList.contains(i)){

                if(reserveList.contains(i-1)){
                    reserveList.remove(Integer.valueOf(i-1));
                    answer++;
                }
                else if(reserveList.contains(i+1)){
                    reserveList.remove(Integer.valueOf(i+1));
                    answer++;
                }
                //앞뒤 아무도 없으면 그사람은 수업참여 불가
            }
            else{
                answer++;
            }
        }
        return answer;
    }
}