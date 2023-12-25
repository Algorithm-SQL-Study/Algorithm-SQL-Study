import java.util.*;

public class Solution {

    public int[] solution(int n, int[] info) {
        int[] answer = new int[info.length];
        int[] noAnswer = {-1};

        //1. 어피치의 점수에 의거해 라이언이 해당 점수를 얻기위해 필요한 화살개수리스트
        int[] neededArrow = new int[info.length];
        for (int i = 0; i < info.length; i++) {
            neededArrow[i] = info[i] + 1;
        }
        boolean[] visitArrow = new boolean[info.length];
        int[] tempArrow = new int[info.length];


        //2. 필요화살개수리스트를 합이 10이될때까지 조합으로 뽑음
        answer = arrowCombination(n, info, answer, neededArrow, visitArrow, tempArrow);

        //3. answer이 전부 0이면 답이 없는것.
        if (Arrays.stream(answer).sum() == 0) {
            return noAnswer;
        }
        return answer;
    }

    public int[] arrowCombination(int n, int[] info, int[] answer,
                                  int[] neededArrow, boolean[] visitArrow, int[] tempArrow) {
        int tempArrowSum = Arrays.stream(tempArrow).sum();
        if (tempArrowSum == n) {
            answer = calculateGame(info, tempArrow, answer);
            return answer;
        } else if (tempArrowSum < n) {
            //남은 필요 화살 0에 몰아넣기해보기
            tempArrow[tempArrow.length-1]=n-tempArrowSum;
            answer=calculateGame(info, tempArrow, answer);
            tempArrow[tempArrow.length-1]=0;

            for (int i = 0; i < neededArrow.length - 1; i++) {
                if (!visitArrow[i]) {
                    tempArrow[i] = neededArrow[i];
                    visitArrow[i] = true;
                    answer = arrowCombination(n, info, answer, neededArrow, visitArrow, tempArrow);
                    tempArrow[i] = 0;
                    visitArrow[i] = false;
                }
            }

        }
        return answer;
    }



    public int[] calculateGame(int[] info, int[] tempArrow, int[] answer) {
        //1. 어피치와 대결해 이기는지 확인
        int tempScoreGap = calculateScroe(info, tempArrow);
        if (tempScoreGap <= 0) {
            return answer;
        }

        //2. 기존최대값과 비교해 점수가 더 큰지확인
        int maxScoreGap = calculateScroe(info, answer);

        if (tempScoreGap > maxScoreGap) {
            answer = tempArrow.clone();
        } else if (tempScoreGap == maxScoreGap) {
            //3. 점수가 동일하면 가장 낮은 점수를 많이 맞춘것으로 answer update
            answer = compareMostLowerScore(answer, tempArrow);

        }

        return answer;

    }

    public int calculateScroe(int[] info, int[] tempArrow) {
        int appeachScore = 0;
        int lionScore = 0;

        for (int i = 0; i < info.length; i++) {
            if (info[i] == 0 && tempArrow[i] == 0) {
                continue;
            }
            if (info[i] >= tempArrow[i]) {
                appeachScore += 10 - i;
            } else {
                lionScore += 10 - i;
            }
        }

        return lionScore - appeachScore;
    }

    public int[] compareMostLowerScore(int[] answer, int[] tempArrow) {
        for (int i = answer.length - 1; i >= 0; i--) {
            if (answer[i] > tempArrow[i]) {
                return answer;
            } else if (answer[i] == tempArrow[i]) {
                continue;
            } else if (answer[i] < tempArrow[i]) {
                return tempArrow.clone();
            }
        }

        return answer;
    }


}
