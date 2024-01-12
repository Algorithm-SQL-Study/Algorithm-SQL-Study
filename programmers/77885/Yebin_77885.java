class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = findNextNumber(numbers[i]);
        }

        return answer;
    }

    private long findNextNumber(long x) {
        // 짝수일 땐 +1 해주면 1개 다르고 제일 작은 수
        if (x % 2 == 0) {
            return x + 1;
        }

        // 홀수일 때 
        long bit = 1;
        while ((x & bit) != 0) { // 가장 낮은 자리의 0을 찾음
            bit <<= 1;  // left shift
        }

        return x + (bit >> 1); // 찾은 자리에 1을 더해줌 
    }
}
