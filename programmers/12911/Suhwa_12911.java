//1. try1
// import java.util.*;

// class Solution {
//     public int solution(int n) {
//         int answer = n;

//         String nbinary = Integer.toBinaryString(n); // 10진수 -> 2진수
//         int numOf1 = nbinary.length() - nbinary.replace("1","").length();

//         int numOf11 =0;
//         while(numOf1!=numOf11){
//             answer+=1;
//             String binary = Integer.toBinaryString(answer);
//             numOf11 = binary.length() - binary.replace("1","").length();
//         }

//         return answer;
//     }
// }


//2. try2
// import java.util.*;

// class Solution {
//     public int solution(int n) {
//         int answer = n;

//         String nbinary = Integer.toBinaryString(n); // 10진수 -> 2진수
//         int numOf1 = getNumOf1(nbinary);

//         int numOf11 =0;
//         while(numOf1!=numOf11){
//             answer+=1;
//             String binary = Integer.toBinaryString(answer);
//             numOf11 = getNumOf1(binary);
//         }

//         return answer;
//     }

//     public int getNumOf1(String binary){
//         int result = 0;
//         for(int i =0 ;i<binary.length() ; i++){
//             result+=Integer.valueOf(binary.charAt(i));
//         }
//         return result;
//     }
// }

// //3. try3
// import java.util.*;

// class Solution {
//     public int solution(int n) {
//         int answer = n;

//         String nbinary = Integer.toBinaryString(n); // 10진수 -> 2진수
//         int numOf1 = getNumOf1(nbinary);

//         int numOf11 =0;
//         while(numOf1!=numOf11){
//             answer+=1;
//             String binary = Integer.toBinaryString(answer);
//             numOf11 = getNumOf1(binary);
//         }

//         return answer;
//     }

//     public int getNumOf1(String binary){
//         int result = 0;
//         for(int i =0 ;i<binary.length() ; i++){
//             result+=Integer.valueOf(binary.charAt(i))-48;
//         }
//         return result;
//     }
// }

//4. try4
import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = n;

        String nbinary = Integer.toBinaryString(n); // 10진수 -> 2진수
        //int numOf1 = getNumOf1(nbinary);
        int numOf1 = nbinary.length() - nbinary.replace("1","").length();

        int numOf11 =0;
        while(numOf1!=numOf11){
            answer+=1;
            String binary = Integer.toBinaryString(answer);
            numOf11 = getNumOf1(binary);
        }

        return answer;
    }

    public int getNumOf1(String binary){
        int result = 0;
        for(int i =0 ;i<binary.length() ; i++){
            result+=Integer.valueOf(binary.charAt(i))-48;
        }
        return result;
    }
}

