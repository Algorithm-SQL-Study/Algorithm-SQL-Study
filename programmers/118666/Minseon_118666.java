/**
 * == 118666. 성격 유형 검사하기 ==
 * 입력: 질문의 판단 지표 배열 survey, 검사자가 선택한 선택지 배열 choices
 * 출력: 검사자의 성격 유형 검사 결과
 */

import java.util.*;

class Solution {
    Map<Character, Integer> totalScore = new HashMap<>();

    public String solution(String[] survey, int[] choices) {
        /* 해시 초기화 */
        totalScore.put('R', 0);     // 라이언
        totalScore.put('T', 0);     // 튜브
        totalScore.put('C', 0);     // 콘
        totalScore.put('F', 0);     // 프로도
        totalScore.put('J', 0);     // 제이지
        totalScore.put('M', 0);     // 무지
        totalScore.put('A', 0);     // 어피치
        totalScore.put('N', 0);     // 네오

        /* 점수 계산 */
        for (int i = 0; i < survey.length; i++) {
            getScore(survey[i], choices[i]);
        }

        /* 검사 결과 */
        StringBuilder sb = new StringBuilder();
        sb = getResult('R', 'T', sb);
        sb = getResult('C', 'F', sb);
        sb = getResult('J', 'M', sb);
        sb = getResult('A', 'N', sb);

        return sb.toString();
    }

    public void getScore(String question, int choice) {
        int[] score = {3, 2, 1, 0, 1, 2, 3};

        char type1 = question.charAt(0);
        char type2 = question.charAt(1);

        if (choice < 4) totalScore.put(type1, totalScore.get(type1) + score[choice-1]);
        else totalScore.put(type2, totalScore.get(type2) + score[choice-1]);
    }

    public StringBuilder getResult(char type1, char type2, StringBuilder sb) {
        int score1 = totalScore.get(type1);
        int score2 = totalScore.get(type2);

        if (score1 >= score2) sb.append(type1);
        else sb.append(type2);

        return sb;
    }
}