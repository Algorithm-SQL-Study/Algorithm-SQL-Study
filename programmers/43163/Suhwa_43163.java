///// 10.31 오후 1시에 작성한 코드
//
// 반례를 걸러내지 못함 : "aab", "aba", ["abb","aba"]
//
//import java.util.*;
//
//class Solution {
//    public int solution(String begin, String target, String[] words) {
//        int answer = 0;
//
//        //1. 타겟단어가 words에 없으면 0리턴
//        if(!Arrays.stream(words).anyMatch(target::equals)){
//            return 0;
//        }
//
//
//        //bfs사용해 빠른
//        return this.bfs(begin,target,words);
//
//    }
//
//    public int bfs(String begin, String target, String[] words){
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(new Node(begin, 0));
//
//        while(queue.size()!=0){
//            Node v = queue.poll();
//            if(v.value.equals(target)){
//                return v.count;
//            }
//
//            for(int i=0 ; i<words.length; i++){
//                if(words[i].equals("")){
//                    continue; //이미 방문한경우
//                }
//                if(isDisffrentOneSpell(v.value, words[i])){
//                    queue.add(new Node(words[i],v.count + 1));
//                    words[i]="";
//                }
//            }
//        }
//        return 0;
//    }
//
//    class Node {
//        public String value;
//        public int count;
//
//        Node (String value, int count) {
//            this.value = value;
//            this.count = count;
//        }
//    }
//
//
//
//    public boolean isDisffrentOneSpell(String v, String word){
//        int notMatchCount=0;
//        for(char c : v.toCharArray()){
//            if(!word.contains(Character.toString(c))){
//                ++notMatchCount;
//            }
//            if(notMatchCount>1){
//                return false;
//            }
//        }
//        return true;
//    }
//}


import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        //1. 타겟단어가 words에 없으면 0리턴
        if(!Arrays.stream(words).anyMatch(target::equals)){
            return 0;
        }


        //bfs사용해 빠른
        return this.bfs(begin,target,words);

    }

    public int bfs(String begin, String target, String[] words){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));

        while(queue.size()!=0){
            Node v = queue.poll();
            if(v.value.equals(target)){
                return v.count;
            }

            for(int i=0 ; i<words.length; i++){
                if(words[i].equals("")){
                    continue; //이미 방문한경우
                }
                if(isDisffrentOneSpell(v.value, words[i])){
                    queue.add(new Node(words[i],v.count + 1));
                    words[i]="";
                }
            }
        }
        return 0;
    }

    class Node {
        public String value;
        public int count;

        Node (String value, int count) {
            this.value = value;
            this.count = count;
        }
    }



    public boolean isDisffrentOneSpell(String v, String word){
        int notMatchCount=0;
        char[] vArray = v.toCharArray();
        char[] wordArray = word.toCharArray();
        for(int i=0 ; i< vArray.length ;i++){
            if(vArray[i] != wordArray[i]){
                notMatchCount++;
            }

            if(notMatchCount>1){
                return false;
            }
        }
        return true;
    }
}