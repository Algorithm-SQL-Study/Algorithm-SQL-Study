import java.util.*;
import java.time.*;
import java.util.stream.Collectors;


//자바 시간을 사용하는 문제
class Solution {

    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();

        Map<String, LocalTime> idAndStartTime = new HashMap();
        Map<String, Long> idAndMin =  new HashMap<>();

        matchInComeAndOutCome(records,idAndStartTime, idAndMin );


        //요금일괄계산
        List<String> sortedIdList =idAndMin.keySet().stream().collect(Collectors.toList());
        Collections.sort(sortedIdList,Comparator.comparingInt(
                id -> Integer.valueOf(id)));





        for(String id : sortedIdList){
            answer.add(calculateFee(idAndMin.get(id), fees[0],fees[1],fees[2],fees[3]));
        }


        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public void matchInComeAndOutCome(String[] records,Map<String, LocalTime> idAndStartTime, Map<String, Long> idAndMin ){
        for(String record : records){
            String[] a = record.split(" ");
            LocalTime time = makeLocalTime(a[0]);
            String id = a[1];
            String flag = a[2];

            if(flag.equals("IN")){
                idAndStartTime.put(id, time);
            }
            else{//flag.equal("OUT")
                recordUseTime(idAndMin, id, Duration.between(idAndStartTime.remove(id),time));
            }
        }

        //OUT없는 입고내역 일괄계산
        for(String id : idAndStartTime.keySet()){
            recordUseTime(idAndMin, id, Duration.between(idAndStartTime.get(id),LocalTime.of(23,59,0,0)));
        }
    }

    public void recordUseTime(Map<String, Long> idAndMin, String id, Duration duration){
        if(idAndMin.containsKey(id)){
            idAndMin.put(id,idAndMin.get(id)+duration.toMinutes());
        }else{
            idAndMin.put(id,duration.toMinutes());
        }
    }


    public Integer calculateFee(Long min, int basicMin, int basicFee, int unitMin, int unitFee){
        if(min<=basicMin){
            return basicFee;
        }
        long unitBaseMinue = min-basicMin;

        if(unitBaseMinue % unitMin!=0){//올림
            return (int) (long)(basicFee+((unitBaseMinue / unitMin)+1)*unitFee);
        }

        return (int) (long)(basicFee+ unitBaseMinue / unitMin*unitFee);

    }

    public LocalTime makeLocalTime(String str){
        String[] b = str.split(":");
        return LocalTime.of(Integer.parseInt(b[0]),Integer.parseInt(b[1]),0,0);
    }
}