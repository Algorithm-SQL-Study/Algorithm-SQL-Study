import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//7
//        1 0 2 0 1 0 1
//        0 2 0 0 0 0 0
//        0 0 1 0 0 1 0
//        0 0 0 0 1 2 2
//        0 0 0 0 0 1 0
//        0 0 2 1 0 2 1
//        0 0 1 2 2 0 2
class Solution {
    private static int RED = 1; // ~>S
    private static int BLUE = 2;// ~>N

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();

        for (int t = 1; t <= 10; t++) {
            int n = sc.nextInt();

            List<Deque<Integer>> magnetic = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                magnetic.add(new LinkedList<>());
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int temp = sc.nextInt();
                    if (temp == 0) {
                        continue;
                    }
                    magnetic.get(j).offer(temp);
                }
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                boolean drop = true;
                Deque<Integer> row = magnetic.get(i);// N<-->S
                while (drop) {
                    drop = false;
                    if (row.isEmpty()) {
                        break;
                    }
                    if (row.peekFirst() == BLUE) {
                        row.pollFirst();
                        drop = true;
                    }
                    if (row.isEmpty()) {
                        break;
                    }
                    if (row.peekLast() == RED) {
                        row.pollLast();
                        drop = true;
                    }
                }

                boolean red = false;
                for (Integer integer : row) {
                    if (integer == RED) {
                        red = true;
                    } else if (red) {
                        count++;
                        red = false;
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(count).append("\n");
        }

        System.out.println(sb);
    }
}
