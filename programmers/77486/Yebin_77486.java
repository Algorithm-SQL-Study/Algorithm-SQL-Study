import java.util.Map;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] enroll, String[] referral
            , String[] seller, int[] amount) {

        Map<String, String> pyramid = new HashMap<>();
        Map<String, Integer> profit = new HashMap<>();

        initPyramid(enroll, referral, pyramid);
        initProfit(enroll, profit);
        carculateProfit(seller, amount, pyramid, profit);

        return printProfit(enroll, profit);
    }

    private void initPyramid(String[] enroll, String[] referral
            , Map<String, String> pyramid) {
        for (int i = 0; i < enroll.length; i++) {
            String lower = enroll[i];
            String higher = referral[i];

            if (higher.equals("-")) continue;

            pyramid.put(lower, higher);
        }
    }

    private void initProfit(String[] enroll
            , Map<String, Integer> profit) {
        for (String person : enroll) {
            profit.put(person, 0);
        }
    }

    private void carculateProfit(String[] seller, int[] amount
            , Map<String, String> pyramid
            , Map<String, Integer> profit) {
        for (int i = 0; i < seller.length; i++) {
            String person = seller[i];
            int money = amount[i] * 100;
            giveCredit(person, money, pyramid, profit);
        }
    }

    private void giveCredit(String person, int money
            , Map<String, String> pyramid
            , Map<String, Integer> profit) {
        String higher = pyramid.get(person);
        int credit = credit10percent(money);
        profit.put(person, profit.get(person) + money - credit);

        if (higher == null) return;
        if (money == 0) return; // <- 이게 없으면 tc 11,12,13 시간초과

        giveCredit(higher, credit, pyramid, profit);
    }

    private int credit10percent(int money) {
        int credit = money / 10;
        if (credit < 1) return 0;
        return credit;
    }

    private int[] printProfit(String[] enroll
            , Map<String, Integer> profit) {

        int[] answer = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profit.get(enroll[i]);
        }

        return answer;
    }
}