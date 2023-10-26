/**
 * == 1912. 연속합 ==
 * 입력 : 정수 n, n개의 정수로 이루어진 수열
 * 출력 : 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합
 */

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] numbers;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n];
        numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = numbers[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(numbers[i], dp[i-1] + numbers[i]);
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}