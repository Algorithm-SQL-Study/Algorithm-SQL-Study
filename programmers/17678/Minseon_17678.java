/**
 * == 17678. 셔틀버스 ==
 * 입력 : 셔틀 운행 횟수 n, 운행 간격 t, 정원 m, 크루의 도착 시각 배열 timetable
 * 출력 : 콘이 무사히 셔틀을 타고 사무실로 갈 수 있는 제일 늦은 도착 시각
 */

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        /* timetable 정렬 -> 덱에 삽입 */
        Deque<String> crews = new ArrayDeque<>();
        Arrays.sort(timetable);
        for (String time: timetable) {
            crews.add(time);
        }

        /* 셔틀버스 시간표 만들기 */
        String[] buses = new String[n];
        for (int i = 0; i < n; i++) {
            buses[i] = getBusTime(t*i);
        }

        /* 버스 탑승 */
        String lastCrew = "";   // 버스의 마지막 탑승객
        int passengers = 0;     // 탑승객
        for (int i = 0; i < n; i++) {
            passengers = 0;
            while (!crews.isEmpty() && passengers < m) {
                String crew = crews.poll();
                if (crew.compareTo(buses[i]) <= 0) {
                    passengers++;
                    lastCrew = crew;
                } else {
                    crews.addFirst(crew);   // 다시 덱에 추가
                    break;
                }
            }
        }

        /* 콘 도착시간 */
        String answer = "";
        if (passengers < m) answer = buses[n-1];    // 정원이 다 차지 않았으면 버스 출발 시간에 도착
        else {                                      // 정원이 다 찼으면 마지막 탑승객보다 1분 빠르게 도착
            int hour = Integer.parseInt(lastCrew.split(":")[0]);
            int minute = Integer.parseInt(lastCrew.split(":")[1]);
            if (minute == 0) {
                hour--; minute = 59;
            } else minute--;

            answer = String.format("%02d:%02d", hour, minute);
        }

        return answer;
    }

    public String getBusTime(int interval) {
        int hour = 9 + (interval / 60);
        int minute = interval % 60;

        return String.format("%02d:%02d", hour, minute);
    }
}