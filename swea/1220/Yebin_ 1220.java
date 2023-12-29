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

            int[][] magnetic = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    magnetic[i][j] = sc.nextInt();
                }
            }

            int count = 0;
            for (int c = 0; c < n; c++) {
                boolean red = false;
                for (int r = 0; r < n; r++) {
                    if (magnetic[r][c] == 0) {
                        continue;
                    }
                    if (magnetic[r][c] == RED) {
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
