class Solution {
    private int emoticonPlus;
    private int emoticonPrice;
    
    public int[] solution(int[][] users, int[] emoticons) {
        permutWithRepeat(0, new int[emoticons.length], emoticons, users);
        int[] answer = {emoticonPlus, emoticonPrice};
        return answer;
    }
    
    private void permutWithRepeat(int index, int[] permut
                                  , int[] emoticons, int[][] users) {
        if (index == permut.length) {
            calculateEmoticonSales(permut, emoticons, users);
            return;
        }
        int[] percent = {40, 30, 20, 10};   // 할인율
        for (int p : percent) {
            permut[index] = p;
            permutWithRepeat(index+1, permut, emoticons, users);
        }
    }
    
    private void calculateEmoticonSales(int[] permut
                                        , int[] emoticons
                                        , int[][] users) {
        int totalPlus = 0;
        int totalPrice = 0;
        for (int[] user : users) {
            int basePercent = user[0];
            int basePrice = user[1];
            
            int tempPrice = 0;
            for (int i = 0; i < permut.length; i++) {
                if (permut[i] < basePercent) continue;
                tempPrice += (emoticons[i] * (100 - permut[i])) / 100;
            }
            
            if (tempPrice < basePrice) totalPrice += tempPrice;
            else totalPlus++;
        }
        
        if (totalPlus < emoticonPlus) return;
        if (totalPlus == emoticonPlus && totalPrice < emoticonPrice) return;
        emoticonPlus = totalPlus;
        emoticonPrice = totalPrice;
    }
}
