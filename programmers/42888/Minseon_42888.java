/**
 * == 42888. 오픈채팅방 ==
 * 입력 : 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record
 * 출력 : 최종적으로 방을 개설한 사람이 보게 되는 메시지
 */

import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> users = new HashMap<>();    // id, 닉네임

        /* 유저별 최종 닉네임 */
        for (String rec: record) {
            String[] info = rec.split(" ");
            if (info[0].equals("Enter") ||
                    info[0].equals("Change"))   // 들어오거나 변경한 경우만 닉네임 설정
                users.put(info[1], info[2]);
        }

        /* 채팅창 메시지 변환 */
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {   // 변경 내역은 변환하지 않음
            String[] info = record[i].split(" ");
            if (info[0].equals("Enter"))
                answer.add(users.get(info[1]) + "님이 들어왔습니다.");
            else if (info[0].equals("Leave"))
                answer.add(users.get(info[1]) + "님이 나갔습니다.");
        }

        return answer.stream().toArray(String[]::new);
    }
}