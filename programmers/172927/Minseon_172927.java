/**
 * == 172927. 광물 캐기 ==
 * 입력: 마인이 갖고 있는 종류별 곡괭이 개수 배열 picks, 캐야하는 광물들의 순서 배열 minerals
 * 출력: 마인이 작업을 끝내기까지 필요한 최소한의 피로도
 */

import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        /* 한 번에 캘 수 있는 광물 집합 구하기 */
        int cnt = 0;
        List<int[]> mineralList = new ArrayList<>();
        int[] m_cnt = new int[3];
        int pick = Arrays.stream(picks).sum();  // 곡괭이 개수
        for (int i = 1; i <= minerals.length; i++) {
            if (pick == 0) break;   // 곡괭이 개수가 광물 수보다 적을 경우 캘 수 있을 만큼 자르기

            if (minerals[i-1].equals("diamond")) m_cnt[0] += 1;
            else if (minerals[i-1].equals("iron")) m_cnt[1] += 1;
            else m_cnt[2] += 1;

            if (i % 5 == 0 || i == minerals.length) {   // 앞에서부터 5개씩 끊어서 광물 개수 저장
                mineralList.add(m_cnt);
                m_cnt = new int[3];
                pick--;
            }
        }

        /* 정렬: 다이아 > 철 > 돌 */
        Collections.sort(mineralList, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                int compareResult = Integer.compare(arr2[0], arr1[0]);  // diamond
                if (compareResult != 0) return compareResult;

                compareResult = Integer.compare(arr2[1], arr1[1]);  // iron
                if (compareResult != 0) return compareResult;

                return Integer.compare(arr2[2], arr1[2]);   // stone
            }
        });

        /* 광물 캐기 */
        int fatigue = 0;    // 피로도
        System.out.println(mineralList.size());
        for (int i = 0; i < mineralList.size(); i++) {
            int[] mineral = mineralList.get(i);
            if (picks[0] > 0) {             // 다이아몬드 곡괭이
                fatigue += mineral[0] + mineral[1] + mineral[2];
                picks[0]--;
            } else if (picks[1] > 0) {      // 철 곡괭이
                fatigue += mineral[0] * 5 + mineral[1] + mineral[2];
                picks[1]--;
            } else if (picks[2] > 0) {      // 돌 곡괭이
                fatigue += mineral[0] * 25 + mineral[1] * 5 + mineral[2];
                picks[2]--;
            } else break;   // 모든 곡괭이를 다 쓴 경우
        }

        return fatigue;
    }
}