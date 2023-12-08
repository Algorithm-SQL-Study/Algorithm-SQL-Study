class Solution {
    int maxDiff;    // 라이언이 우승한 경우 중 최대 점수차
    int[] answer;   // 라이언이 최대 점수차로 우승한 경우
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        makeCases(0, new boolean[info.length], info, n);
        if (maxDiff <= 0) { 
            answer = new int[]{-1};
        }
        return answer;
    }
    
    /**
    * 라이언이 이길 판을 고르고 우승할 방법인지 판단하여 필드를 업데이트
    */
    private void makeCases(int index, boolean[] win, int[] apeach, int arrow) {
        if (index == apeach.length - 1) { // 10~1점까지 고려 
            // 주어진 화살로 가능한 경우인지 
            int[] ryan = getRyanWinCase(win, apeach, arrow);
            if (ryan == null) return;
            // 업데이트 
            int diff = getDiff(ryan, apeach);
            if (diff <= 0 || diff < maxDiff) return;
            if (diff == maxDiff && !betterAnswer(ryan, answer)) return;
            maxDiff = diff;
            answer = ryan.clone();
            return;
        }
        win[index] = false;
        makeCases(index + 1, win, apeach, arrow);
        
        win[index] = true;
        makeCases(index + 1, win, apeach, arrow);
    }
    
    /**
    * 라이언이 이길 판을 보고 가능한 경우라면 라이언의 기록을 반환
    */
    private int[] getRyanWinCase(boolean[] win, int[] apeach, int arrow) {
        int[] ryan = new int[11];   // 라이언 기록
        for (int i = 0; i < 10; i++) {  // 길이는 11이지만 0점은 따로 볼 필요 없음
            if (!win[i]) continue; 
            // 라이언이 이겨야하는 과녁
            int used = apeach[i] + 1;
            ryan[i] = used;
            arrow -= used;
            // 화살 개수가 모자라면 불가능한 케이스
            if (arrow < 0) return null; 
        }
        ryan[10] += arrow;
        return ryan;
    }
    
    /**
    * 라이언과 어피치의 점수차 
    */
    private int getDiff(int[] ryan, int[] apeach) {
        int rscore = 0;
        int ascore = 0;
        for (int i = 0; i < 10; i++) {
            if (ryan[i] == 0 && apeach[i] == 0) continue;
            if (ryan[i] > apeach[i]) rscore += 10 - i;
            else ascore += 10 - i;
        }
        return rscore - ascore;
    }
    
    /**
    * 가장 낮은 점수를 더 많이 맞힌 경우가 맞는지 확인 
    */
    private boolean betterAnswer(int[] now, int[] previous) {
        for (int i = 10; i >= 0; i--) {
            if (now[i] == 0 && previous[i] == 0) continue;
            if (now[i] == previous[i]) continue;
            if (now[i] > previous[i]) return true;
            if (now[i] < previous[i]) return false;
        }
        return true;
    }
}
