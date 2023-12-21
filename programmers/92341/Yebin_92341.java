import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        Map<String, String> parkingLot = new HashMap<>();
        Map<String, Integer> timeSum = new HashMap<>();
        
        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String number = st.nextToken();
            String inOut = st.nextToken();
            if (inOut.equals("IN")) {
                parkingLot.put(number, time);
            } else {
                String inTime = parkingLot.remove(number);
                int parkingTime = calculateTime(inTime, time);
                timeSum.put(number, timeSum.getOrDefault(number, 0) + parkingTime);
            }
        }

        for (Map.Entry<String, String> entry : parkingLot.entrySet()) {
            String number = entry.getKey();
            String inTime = entry.getValue();
            int parkingTime = calculateTime(inTime, "23:59");
            timeSum.put(number, timeSum.getOrDefault(number, 0) + parkingTime);
        }
        
        List<Integer> result = new ArrayList<>();
        List<String> sortedNumbers = new ArrayList<>(timeSum.keySet());
        Collections.sort(sortedNumbers);

        for (String number : sortedNumbers) {
            int parkedTime = timeSum.get(number);
            if (parkedTime <= basicTime) {
                result.add(basicFee);
            } else {
                int extraTime = parkedTime - basicTime;
                int extraFee = (int) Math.ceil((double) extraTime / unitTime) * unitFee;
                result.add(basicFee + extraFee);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int calculateTime(String inTime, String outTime) {
        String[] inHHmm = inTime.split(":");
        String[] outHHmm = outTime.split(":");
        int inHH = Integer.parseInt(inHHmm[0]);
        int inmm = Integer.parseInt(inHHmm[1]);
        int outHH = Integer.parseInt(outHHmm[0]);
        int outmm = Integer.parseInt(outHHmm[1]);
        return (outHH*60 + outmm) - (inHH*60 + inmm);
    }
}
