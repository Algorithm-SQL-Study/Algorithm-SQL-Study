class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];// [변환 횟수, 제거된 0의 개수]

        while(!s.equals("1")) {
            // 이진 변환
            long s1 = count1(s);
            long s0 = count0(s);

            s = Long.toBinaryString(s1);

            // 배열 갱신
            answer[0]++;
            answer[1] += s0;
        }

        return answer;
    }

    private long count1(String s) {
        return s.chars()
            .filter(c -> c == '1')
            .count();
    }

    private long count0(String s) {
        return s.chars()
            .filter(c -> c == '0')
            .count();
    }
}