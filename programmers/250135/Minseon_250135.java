/**
 * == 250135. 아날로그 시계 ==
 * 입력 : 시작 시간 h1시 m1분 s1초, 종료 시간 h2시 m2분 s2초
 * 출력 : 시작 시간부터 종료 시간까지 알람이 울리는 횟수
 */

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int time1 = timeToSecond(h1, m1, s1);
        int time2 = timeToSecond(h2, m2, s2);
        int count = time1 == 12 * 3600 ? 1 : 0;
        return countAlarm(time2) - countAlarm(time1) + count;
    }

    public int timeToSecond(int h, int m, int s) {
        return h*60*60 + m*60 + s;
    }

    public int countAlarm(int time) {
        int hourAlarm = time * 719 / 43200;     // 시침 알람 횟수
        int minuteAlarm = time * 59 / 3600;     // 분침 알람 횟수

        // 자정 및 정각
        int mid = 0;
        if (time == 0 || time >= 12 * 3600) mid++;
        return hourAlarm + minuteAlarm - mid;
    }
}