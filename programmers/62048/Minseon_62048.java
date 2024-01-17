/**
 * == 62048. 멀쩡한 사각형 ==
 * 입력 : 가로의 길이 w, 세로의 길이 h
 * 출력 : 사용할 수 있는 정사각형의 개수
 */

class Solution {
    public long solution(int w, int h) {
        long area = (long) w * (long) h;       // 원래 정사각형 개수
        if (w == h) return area - (long) w;    // 정사각형인 경우
        if (w == 1 || h == 1) return 0;        // 너비 또는 높이가 1인 경우

        /* 직각 삼각형에서 사용할 수 있는 정사각형 개수 */
        long answer = 0;
        for (long i = 1; i < w; i++) {
            answer += ((long)h * i) / (long) w;
        }

        return answer * 2L;
    }
}