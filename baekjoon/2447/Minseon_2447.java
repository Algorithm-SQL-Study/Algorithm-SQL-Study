/**
 * == 2447. 별 찍기 - 10 ==
 * 입력 : 3의 거듭제곱 N
 * 출력 : N의 패턴대로 찍은 별
 */

import java.io.*;

public class Main {
    static char[][] stars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* input */
        int N = Integer.parseInt(br.readLine());
        stars = new char[N][N];

        /* draw star */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                star(i, j, N);
            }
        }

        /* output */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(stars[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
    }

    private static void star(int i, int j, int N) {
        if ((i / N) % 3 == 1 && (j / N) % 3 == 1) stars[i][j] = ' ';    // 공백
        else {
            if (N / 3 == 0) stars[i][j] = '*';      // 별 찍기
            else star(i, j, N/3);
        }
    }
}
