/**
 * == 16401. 과자 나눠주기 ==
 * 입력 : 조카의 수 M, 과자의 수 N, 과자 N개의 길이
 * 출력 : 조카 1명에게 줄 수 있는 막대 과자의 최대 길이
 */

import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[] snacks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        snacks = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snacks);

        int maxLength = 0;

        int mid = 0; int left = 1;
        int right = snacks[N-1];
        while (left <= right) {
            mid = (left + right) / 2;
            int count = 0;
            for (int i = 0; i < N; i++) {
                count += snacks[i] / mid;
            }

            if (count >= M) {
                left = mid + 1;
                maxLength = mid;
            } else right = mid - 1;
        }

        System.out.println(maxLength);
    }
}