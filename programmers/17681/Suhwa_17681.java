import java.util.Arrays;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {


        String[] answer = new String[n];
        int[] arr3 = new int[n];

        // arr3에 비트 곱의 결과를 저장
        for(int i = 0;i<n;i++){
            arr3[i]=arr1[i]|arr2[i];
        }
        //System.out.println(Arrays.toString(arr3));

        // 비트곱의 결과를 이진수(문자열 형태)로 저장
        for(int i=0;i<n;i++){
            answer[i]=Integer.toBinaryString(arr3[i]);
        }


        for(int i = 0;i<n;i++){
            // 각 이진수 n의 자리로 맞추기
            if(answer[i].length()>n){
                int gap= answer[i].length()-n;
                answer[i]=answer[i].substring(gap);
            }
            else if(answer[i].length()<n) {
                int gap= n-answer[i].length();
                String gapedString="0";
                for(int j =1;j<gap;j++){
                    gapedString=gapedString+"0";
                }
                answer[i]=gapedString+answer[i];
            }

            //System.out.println(answer[i]);

            //System.out.println(answer[i].replace("1", "#").replace("0"," "));

            // 각 이진수 #, 공백으로 대치
            answer[i]=answer[i].replace("1", "#").replace("0"," ");
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }
}