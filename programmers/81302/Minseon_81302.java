/**
 * == 81302. 거리두기 확인하기 ==
 * 입력: 대기실 5개의 구조 2차원 배열 places
 * 출력: 각 대기실 별 모든 응시자가 거리두기를 지키고 있는지 여부
 */

import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            answer[i] = isSocialDist(places[i]);
        }

        return answer;
    }

    public int isSocialDist(String[] place) {
        /* 응시자 좌표 구하기 */
        List<Coordinate> persons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') persons.add(new Coordinate(i, j));
            }
        }

        if (persons.isEmpty()) return 1;

        /* 거리두기 여부 구하기 */
        for (int i = 0; i < persons.size()-1; i++) {
            for (int j = i+1; j < persons.size(); j++) {
                Coordinate person1 = persons.get(i);
                Coordinate person2 = persons.get(j);

                int manhattanDist = getManhattanDist(person1, person2);
                if (manhattanDist > 2) continue;         // 맨해튼 거리 > 2면 거리두기 O
                else if (manhattanDist < 2) return 0;    // 두 응시자가 붙어있는 경우 거리두기 X
                else {                                   // 파티션이 없는 경우 거리두기 X
                    if (!isPartition(person1, person2, place)) return 0;
                }

            }
        }

        return 1;
    }

    /* 맨해튼 거리 구하기 */
    public int getManhattanDist(Coordinate person1, Coordinate person2) {
        return Math.abs(person1.r - person2.r) + Math.abs(person1.c - person2.c);
    }

    /* 응시자 사이에 파티션이 있는지 확인 */
    public boolean isPartition(Coordinate person1, Coordinate person2, String[] place) {
        if (person1.r == person2.r || person1.c == person2.c) {     // 일렬로 위치한 경우
            int px = (person1.r + person2.r) / 2;
            int py = (person1.c + person2.c) / 2;
            if (place[px].charAt(py) != 'X') return false;
        } else {                                                    // 대각선으로 위치한 경우
            if (place[person1.r].charAt(person2.c) != 'X'
                    || place[person2.r].charAt(person1.c) != 'X') return false;
        }

        return true;
    }
}

class Coordinate {
    int r;
    int c;

    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }
}