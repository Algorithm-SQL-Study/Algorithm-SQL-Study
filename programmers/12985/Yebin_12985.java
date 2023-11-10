class Solution
{
    public int solution(int n, int a, int b)
    {
        int round = 0;

        while (a != b) {
            a = next(a);
            b = next(b);
            round++;
        }

        return round;
    }

    private int next(int now) {
        return (now / 2) + (now % 2);
    }
}