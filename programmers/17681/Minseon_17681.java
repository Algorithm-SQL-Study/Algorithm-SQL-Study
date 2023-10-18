/**
 * == 17681. 비밀지도 ==
 * 입력: 지도의 한 변 크기 n, 2개의 정수 배열 arr1, arr2
 * 출력: 비밀지도를 해독하여 '#'와 공백으로 이루어진 문자열 배열 출력
 */

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] map1 = makeMap(n, arr1);
        String[] map2 = makeMap(n, arr2);

        /* 전체 지도 */
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            String temp = "";
            for (int j = 0; j < n; j++) {
                if (map1[i].charAt(j) == '0' && map2[i].charAt(j) == '0') temp += " ";
                else temp += "#";
            }
            answer[i] = temp;
        }

        return answer;
    }

    /* 해독: 정수 -> 이진수 변환 */
    public String[] makeMap(int n, int[] arr) {
        String[] map = new String[n];
        for (int i = 0; i < n; i++) {
            String binary = Long.toBinaryString(arr[i]);
            String formatString = "%0" + n + "d";
            binary = String.format(formatString, Long.parseLong(binary));
            map[i] = binary;
        }
        return map;
    }
}