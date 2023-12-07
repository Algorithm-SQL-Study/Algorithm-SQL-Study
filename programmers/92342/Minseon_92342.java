/**
 * == 92342. 양궁대회 ==
 * 입력 : 화살의 개수 n, 어피치가 맞힌 과녁 점수 배열 info
 * 출력 : 라이언이 가장 큰 점수 차이로 우승하기 위한 과녁 점수 배열
 */

import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        /* 승패 경우의 수 구하기 */
        List<boolean[]> isWinList = new ArrayList<>(); // 점수별로 win / lose 여부 모든 경우의 수 저장
        getIsWin(new boolean[11], 0, isWinList);   // 승패 경우의 수 순열 만들기

        int[] ryanScore = new int[11];  // 최종 라이언 점수 배열
        int scoreDiff = 0;              // 최대 점수 차

        /* 모든 경우의 수 탐색 */
        for (boolean[] isWin : isWinList) {
            /* 승패의 유효성 확인 */
            int[] ryan = new int[11];
            int arrows = 0; // 라이언이 쏜 화살

            for (int i = 0; i < 10; i++) {
                if (isWin[i]) {
                    ryan[i] = info[i] + 1;
                    arrows += ryan[i];
                }
            }
            ryan[10] = n - arrows;

            if (arrows > n) continue;   // 승패 결과대로 화살을 쐈을 때 화살 개수가 n보다 크면 continue

            /* 점수 비교 */
            int a_score = 0;    // 어피치 점수
            int r_score = 0;    // 라이언 점수
            for (int i = 0; i < 11; i++) {
                if (info[i] == 0 && ryan[i] == 0) continue;

                if (info[i] >= ryan[i]) a_score += 10 - i;
                else r_score += 10 - i;
            }

            if (r_score - a_score > scoreDiff) {
                scoreDiff = r_score - a_score;
                ryanScore = ryan.clone();
            } else if (r_score - a_score == scoreDiff) {
                /* 점수 차가 같은 경우 낮은 점수에 쏜 화살이 많은 경우 리턴 */
                for (int i = 10; i >= 0; i--) {
                    if (ryan[i] > ryanScore[i]) {
                        ryanScore = ryan.clone();
                        break;
                    } else if (ryan[i] < ryanScore[i]) break;
                }
            }
        }

        int r_total = 0;
        for (int score : ryanScore) r_total += score;
        if (r_total > 0 && scoreDiff != 0) return ryanScore;    // scoreDiff == 0이면 어피치와 비긴 경우 -> 어피치 승
        int[] answer = {-1};
        return answer;
    }

    /* 라이언 승패 배열 - true: win, false: lose */
    public void getIsWin(boolean[] arr, int idx, List<boolean[]> isWin) {
        if (idx == arr.length) {
            isWin.add(arr.clone());
            return;
        }

        arr[idx] = false;
        getIsWin(arr, idx + 1, isWin);

        arr[idx] = true;
        getIsWin(arr, idx + 1, isWin);
    }
}