import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        // 0 ~ 9까지의 단어와 index가 연결된 ArrayList를 생성
        List<String> numberAndString = new ArrayList<>();
        numberAndString.add("zero");
        numberAndString.add("one");
        numberAndString.add("two");
        numberAndString.add("three");
        numberAndString.add("four");
        numberAndString.add("five");
        numberAndString.add("six");
        numberAndString.add("seven");
        numberAndString.add( "eight");
        numberAndString.add( "nine");
        // 문자열이 숫자인 경우 우선 return
        try{
            return Integer.parseInt(s);
        }catch(Exception e){
            //문자열에 문자가 포함되어있을때
            for (int i=0 ; i<10;i++){
                if(s.contains(numberAndString.get(i))){
                    s = s.replaceAll(numberAndString.get(i),Integer.toString(i));
                }
            }

            answer = Integer.parseInt(s);
        }




        return answer;
    }

//     public static void main(String[] args) {
//         Solution solution = new Solution();
//         System.out.println(solution.solution("one4seveneight"));
//         System.out.println(solution.solution("23four5six7"));

//         System.out.println(solution.solution("2three45sixseven"));
//         System.out.println(solution.solution("123"));

//     }
}