import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dic = new LinkedHashMap<String, Integer>();
        settingMap(dic);

        for(int i =0 ; i< msg.length() ; i++){
            i += searchDicIndex(i,msg,dic,answer);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }


    public Integer searchDicIndex(int start, String msg,
                                  Map<String, Integer> dic,List<Integer> answer ){
        for(int i = msg.length() ; i>start ; i--){
            //뒤에서부터 자르기
            String target = msg.substring(start,i);
            if(dic.containsKey(target)){
                if(i != msg.length()){
                    dic.put(msg.substring(start,i+1), dic.size()+1);
                }
                answer.add(dic.get(target));
                return i-start-1; //건너뛸숫자
            }
        }

        return -99;
    }

    public void settingMap(Map<String, Integer> dic){
        dic.put("A",1);
        dic.put("B",2);
        dic.put("C",3);
        dic.put("D",4);
        dic.put("E",5);
        dic.put("F",6);
        dic.put("G",7);
        dic.put("H",8);
        dic.put("I",9);
        dic.put("J",10);
        dic.put("K",11);
        dic.put("L",12);
        dic.put("M",13);
        dic.put("N",14);
        dic.put("O",15);
        dic.put("P",16);
        dic.put("Q",17);
        dic.put("R",18);
        dic.put("S",19);
        dic.put("T",20);
        dic.put("U",21);
        dic.put("V",22);
        dic.put("W",23);
        dic.put("X",24);
        dic.put("Y",25);
        dic.put("Z",26);
    }
}