/**
 * 92341. 주차 요금 계산
 * 입력 : 주차 요금 배열 fees, 자동차의 입/출차 내역 배열 records
 * 출력 : 차량 번호가 작은 자동차부터 청구할 주차 요금 배열
 */

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int n = records.length;
        Map<String, Integer> parkingTimeMap = new HashMap<>();

        /* 입출차 기록 정렬 */
        String[][] sortedRecord = new String[n][3];
        for (int i = 0; i < n; i++) {
            String[] splitedRecord = records[i].split(" ");
            sortedRecord[i][0] = splitedRecord[0];
            sortedRecord[i][1] = splitedRecord[1];
            sortedRecord[i][2] = splitedRecord[2];
        }
        Arrays.sort(sortedRecord, Comparator.comparing((String[] arr) -> arr[1])   // 차량 번호
                .thenComparing(arr -> arr[0])); // 입출차 시각

        /* 누적 주차 시간 계산 */
        for (int i = 0; i < n; i++) {
            String carNum = sortedRecord[i][1];
            if (!parkingTimeMap.containsKey(carNum)) {
                parkingTimeMap.put(carNum, 0); // 차량 번호가 해시맵에 존재하지 않는 경우
            }
            if (i < n - 1 && carNum.equals(sortedRecord[i + 1][1])) {
                parkingTimeMap.put(carNum, parkingTimeMap.get(carNum) + getTime(sortedRecord[i][0], sortedRecord[i + 1][0])); // 차량 번호가 같으면 누적시간 구하기
                i += 1; // IN-OUT 짝이 맞으므로 +1
            } else {
                parkingTimeMap.put(carNum, parkingTimeMap.get(carNum) + getTime(sortedRecord[i][0], "23:59")); // 차량 번호가 같지 않으면 앞차의 누적시간 구하기
            }
        }

        /* 주차 요금 정산 */
        List<String> carList = new ArrayList<>(parkingTimeMap.keySet());
        Collections.sort(carList);  // 차량 번호 순
        int[] answer = new int[carList.size()];

        for (int i = 0; i < carList.size(); i++) {
            int fee = getFee(parkingTimeMap.get(carList.get(i)), fees);
            answer[i] = fee;
        }

        return answer;
    }

    public int getTime (String in_record, String out_record) {
        int in_time = Integer.parseInt(in_record.split(":")[0]) * 60
                + Integer.parseInt(in_record.split(":")[1]);
        int out_time = Integer.parseInt(out_record.split(":")[0]) * 60
                + Integer.parseInt(out_record.split(":")[1]);

        return out_time - in_time;
    }

    public int getFee (int time, int[] fees) {
        int base = time / fees[0];
        int excess = 0;
        if (base > 0) excess = (int) Math.ceil((double) (time - fees[0]) / fees[2]);    // 기본 시간보다 적게 주차했으면 계산 X

        return fees[1] + excess * fees[3];  // 기본 요금 + 추가 요금
    }
}