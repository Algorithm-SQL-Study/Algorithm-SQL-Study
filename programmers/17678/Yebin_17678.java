import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        Queue<Integer> waitList = new LinkedList<>();   // 대기열
        
        for (String time : timetable) {
            int minutes = timeToMinutes(time);
            waitList.offer(minutes);
        }
        
        int suttleArrival = timeToMinutes("09:00");
        int lastTime = suttleArrival;
        
        for (int i = 1; i <= n; i++) { // 셔틀 n대 운행
            // 셔틀 출발 시간까지 온 사람들을 태움
            int seats = m;
            while (!waitList.isEmpty() && waitList.peek() <= suttleArrival) {
                if (seats <= 0) break;
                lastTime = waitList.poll() - 1; // 마지막 사람보다 1분 일찍 오면 차에 탈 수 있음
                seats--;
            }
            
            // 자리가 남았다면 출발 시간까지 오면 탈 수 있음
            if (seats > 0) lastTime = suttleArrival;
            
            suttleArrival += t;
        }
        
        return minutesToTime(lastTime);
    }
    
    private int timeToMinutes(String time) {
        String[] temp = time.split(":");
        int hh = Integer.parseInt(temp[0]);
        int mm = Integer.parseInt(temp[1]);
        return hh * 60 + mm;
    }
    
    private String minutesToTime(int minutes) {
        int hh = minutes / 60;
        int mm = minutes % 60;
        return String.format("%02d:%02d", hh, mm);
    }
}
