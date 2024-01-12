class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String kNumber = Integer.toString(n, k);
        String[] numbers = kNumber.split("0");
        for (String num : numbers) {
            if (isPrime(num)) answer++;
        }
        
        return answer;
    }
    
    private boolean isPrime(String num) {
        try {
            long n = Long.parseLong(num);
            
            if (n == 1) return false; // 1은 소수가 아니다
            
            for (int i = 2; i <= (int) Math.sqrt(n); i++) {
              if (n % i == 0) {
                  return false;
              }
            }
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
}
