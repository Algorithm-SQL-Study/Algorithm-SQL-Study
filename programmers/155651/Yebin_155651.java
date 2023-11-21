import java.util.Queue;
import java.util.PriorityQueue;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(String[][] book_time) {
        int room = 0;

        // 1. 예약을 시작 시간순으로 정렬
        Comparator<String[]> timeComparator = Comparator
                .comparing((String[] time) -> time[0]);
        Arrays.sort(book_time, timeComparator);

        // 2. 제일 빨리 비는 방을 얻기 위해 우선순위큐를 쓴다
        Queue<LocalTime> hotel = new PriorityQueue<>();

        // 3. 시간순으로 방에 집어넣고 방의 개수를 센다.
        for (int i = 0; i < book_time.length; i++) {
            LocalTime enroom = mapToLocalTime(book_time[i][0]);
            LocalTime deroom = mapToLocalTime(book_time[i][1]);
            // 빈 방이 있음
            if (!hotel.isEmpty() && hotel.peek().plusMinutes(9l).isBefore(enroom)) {
                hotel.poll();
                hotel.add(deroom);
                continue;
            }
            // 아직 아무도 안들어갔거나 빈 방이 없음
            hotel.add(deroom);
            room += 1;
        }

        return room;
    }

    // 시간문자열을 로컬타임으로 바꿔주는 메소드
    private LocalTime mapToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }
}