class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int mn = brown + yellow;    // 카펫 크기 m(가로) * n(세로)

        for (int n = 3; n <= mn / n; n++) {
            if (mn % n != 0) continue;

            // 카펫 크기 맞음
            int m = mn / n;

            // 테두리 개수 맞음
            if (2 * (m + n - 2) == brown) {
                answer[0] = m;
                answer[1] = n;
                break;
            }
        }

        return answer;
    }
}