/**
 * == 150368. 이모티콘 할인행사 ==
 * 입력 : 카카오톡 사용자 n명의 구매 기준 배열 users, 이모티콘 m개의 정가 배열 emoticons
 * 출력 : 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수, 이모티콘 매출액
 */

import java.util.*;

class Solution {
    int maxMembers = 0; // 최대 서비스 가입자
    int maxRevenue = 0; // 최대 판매액

    public int[] solution(int[][] users, int[] emoticons) {
        /* 할인 비율 범위 구하기 */
        int minDiscount = 40;
        for (int[] user : users) {
            minDiscount = Math.min(minDiscount, user[0]);
        }
        minDiscount = ceilTens(minDiscount);    // 10 20 30 40 올림 연산

        /* 이모티콘 할인행사 결과 */
        makePermutation(minDiscount, emoticons.length, new ArrayList<>(), users, emoticons);
        int[] answer = {maxMembers, maxRevenue};
        return answer;
    }

    public void makePermutation(int minDiscount, int maxDepth, List<Integer> discount, int[][] users, int[] emoticons) {
        if (discount.size() == maxDepth) {
            getCosts(users, emoticons, discount);   // 판매액 & 서비스 가입자 구하기
            return;
        }

        for (int i = minDiscount; i <= 40; i += 10) {
            discount.add(i);
            makePermutation(minDiscount, maxDepth, discount, users, emoticons);
            discount.remove(discount.size() - 1);
        }
    }

    /* 판매액 및 서비스 가입자 계산 */
    public void getCosts(int[][] users, int[] emoticons, List<Integer> discount) {
        int members = 0; int revenue = 0;
        for (int[] user : users) {
            int costs = 0;
            for (int i = 0; i < emoticons.length; i++) {
                double purchase = (double) (100 - discount.get(i)) / 100;
                if (discount.get(i) >= user[0]) costs += emoticons[i] * purchase;
                if (costs >= user[1]) {
                    members++;
                    costs = 0;
                    break;
                }
            }
            revenue += costs;
        }

        // 목표
        if (members > maxMembers) {
            maxMembers = members;
            maxRevenue = revenue;
        } else if (members == maxMembers) {
            maxRevenue = Math.max(maxRevenue, revenue);
        }
    }

    /* 10단위 올림 */
    public int ceilTens(int number) {
        if (number % 10 == 0) {
            return number;
        } else {
            return number + (10 - number % 10);
        }
    }
}