import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        for (int test_case = 1; test_case <= 10; test_case++) {
            int k = sc.nextInt();
            List<Integer> data = IntStream.range(0, 8).mapToObj(i -> sc.nextInt())
                    .collect(Collectors.toCollection(LinkedList::new));

            makePassword(data);

            String password = data.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" "));
            System.out.printf("#%d %s\n", k, password);
        }
    }

    private static void makePassword(List<Integer> password) {
        while (true) {
            for (int i = 1; i <= 5; i++) {
                int temp = password.remove(0) - i;
                if (temp <= 0) {
                    password.add(0);
                    return;
                }
                password.add(temp);
            }
        }
    }
}
