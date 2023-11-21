/**
 * == 155651. 호텔 대실 ==
 * 입력: 예약 시각이 문자열 형태로 담긴 2차원 배열 book_time
 * 출력: 호텔에 필요한 최소 객실
 */

import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        /* book_time을 시작 시간 순으로 정렬 */
        Arrays.sort(book_time, Comparator.comparing(a -> a[0]));

        /* 방 배정 */
        Map<Integer, String> rooms = new HashMap<>(); // 각 방마다 퇴실 시간을 저장
        for (String[] time : book_time) {
            time = addCleaningTime(time);
            if (rooms.isEmpty()) {
                rooms.put(1, time[1]);
            } else {
                int roomNum = rooms.size() + 1;
                String earliestCheckout = "23:59"; // 방들 중 가장 빠른 퇴실 시간
                for (Map.Entry<Integer, String> room : rooms.entrySet()) {
                    if (time[0].compareTo(room.getValue()) >= 0 && earliestCheckout.compareTo(room.getValue()) >= 0) {
                        // 입실 시간에 비어있는 방 중에 퇴실 시간이 가장 빠른 방
                        earliestCheckout = room.getValue();
                        roomNum = room.getKey();
                    }
                }
                rooms.put(roomNum, time[1]); // 퇴실 시간 업데이트
            }
        }

        return rooms.size();
    }

    public String[] addCleaningTime(String[] time) {
        int hour = Integer.parseInt(time[1].split(":")[0]);
        int minute = Integer.parseInt(time[1].split(":")[1]);

        /* HH:MM formatting */
        if (minute < 50) time[1] = String.format("%02d:%02d", hour, minute + 10);
        else {
            time[1] = String.format("%02d:%02d", hour + 1, minute - 50);
        }

        return time;
    }
}