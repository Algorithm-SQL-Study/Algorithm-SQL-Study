import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        for (int t = 1; t <= 10; t++) {
            int n = sc.nextInt();
            int[] buildings = IntStream.range(0, n).map(i -> sc.nextInt()).toArray();

            int view = 0;
            for (int i = 0; i < n; i++) {
                int l2 = i > 1 ? buildings[i - 2] : 0;
                int l1 = i > 0 ? buildings[i - 1] : 0;
                int r1 = i < n - 1 ? buildings[i + 1] : 0;
                int r2 = i < n - 2 ? buildings[i + 2] : 0;

                int temp = buildings[i] - Stream.of(l2, l1, r1, r2).mapToInt(x -> x).max().getAsInt();
                if (temp <= 0) {
                    continue;
                }
                view += temp;
            }

            System.out.printf("#%d %d\n", t, view);
        }
    }
}
