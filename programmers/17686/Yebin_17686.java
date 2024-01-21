// https://school.programmers.co.kr/learn/courses/30/lessons/17686
// 2018 KAKAO BLIND RECRUITMENT [3차] 파일명 정렬

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] file1 = splitFileName(o1);
                String[] file2 = splitFileName(o2);

                // HEAD 비교 (대소문자 무시)
                String head1 = file1[0].toLowerCase();
                String head2 = file2[0].toLowerCase();
                int result = head1.compareTo(head2);
                if (result != 0) return result;
                
                // NUMBER 비교
                int number1 = Integer.parseInt(file1[1]);
                int number2 = Integer.parseInt(file2[1]);
                result = Integer.compare(number1, number2);
                return result;
            }
        });

        return files;
    }

    private String[] splitFileName(String fileName) {
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        // HEAD 추출
        while (idx < fileName.length() && !Character.isDigit(fileName.charAt(idx))) {
            sb.append(fileName.charAt(idx++));
        }
        String head = sb.toString();
        sb.setLength(0);
        
        // NUMBER 추출
        while (idx < fileName.length() && Character.isDigit(fileName.charAt(idx))) {
            sb.append(fileName.charAt(idx++));
        }
        String number = sb.toString();

        return new String[]{head, number};
    }
}
