import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

class Solution {
    String ENTER = "Enter";
    String LEAVE = "Leave";
    String CHANGE = "Change";
    String IN = "님이 들어왔습니다.";
    String OUT = "님이 나갔습니다.";
    
    class Inout {
        boolean in;
        String uid;
        
        Inout(boolean in, String uid) {
            this.in = in;
            this.uid = uid;
        }
    }
  
    public String[] solution(String[] record) {
        
        Map<String, String> dict = new HashMap<>();
        List<Inout> inout = new LinkedList<>();
        for (String r : record) {
            String[] temp = r.split(" ");
            String task = temp[0];
            String uid = temp[1];
            
            if (task.equals(LEAVE)) {
                inout.add(new Inout(false, uid));
                continue;
            }
            
            String nickname = temp[2];
            
            if (task.equals(ENTER)) {
                inout.add(new Inout(true, uid));
                dict.put(uid, nickname);
                continue;
            }
            
            dict.put(uid, nickname);
        }
                
        String[] answer = new String[inout.size()];
        int i = 0;
        for (Inout io : inout) {
            String nickname = dict.get(io.uid);
            if (io.in) {
                answer[i] = nickname + IN;
            } else {
                answer[i] = nickname + OUT;
            }
            i++;
        }
        
        return answer;
    }
}
