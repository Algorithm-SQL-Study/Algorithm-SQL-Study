import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        boolean[] shoted = new boolean[targets.length]; //정렬된 target 인덱스 위치

        //1. 길이가 짧은 순으로 미사일 정렬

        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] s1, int[] s2) {
                int s1Length = s1[1]-s1[0];
                int s2Length = s2[1]-s2[0];
                return s1Length - s2Length;
            }
        });

        // for(int[] target : targets){
        //     System.out.println("미사일 출발 "+target[0]+"->"+target[1]);
        // }

        //2. 요격당하지 않은 미사일 순회
        for(int i=0;i<targets.length;i++){
            if(shoted[i]) continue;

            //3. 미사일의 구간마다 요각하지 않은 미사일을 가장 많이 맞추는구간정함
            shoted[i]=true;
            List<Integer> shotedMax = determineArea(targets[i], shoted, targets);
            //4. 요격당한 미사일 표시
            answer+=1;
            // for(int j =0 ; j<shotedMax.size(); j++){
            //     System.out.println(i+"번째 미사일 쏘는김에 몇번 미사일 맞춤?"+shotedMax.get(j));
            // }
            shotedMax.forEach(a -> shoted[a]=true);

        }



        return answer;
    }

    public List<Integer> determineArea(int[] target,boolean[] shoted, int[][]targets){
        List<Integer> tempMax = new ArrayList<>(); //size는 요격한 미사일 개수, 각. 원소는 요각당한 미사일 인덱스

        //(a,b) 에서 s==a, 는 a~a+1 의 구간중 실수 하나를 의미한다.
        for(int s = target[0] ; s<target[1]; s++){
            List<Integer> tempList = new ArrayList<>();
            for(int i=0;i<targets.length;i++){
                if(shoted[i]) continue;
                if(targets[i][0]<=s && targets[i][1]>=s+1){
                    tempList.add(i);
                }
            }

            if(tempMax.size()<tempList.size()) tempMax=tempList;
        }

        return tempMax;
    }
}