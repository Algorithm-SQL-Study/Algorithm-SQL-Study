/**
 * == 87377. 교점에 별 만들기 ==
 * 입력 : Ax + By + C = 0으로 표현할 수 있는 n개의 직선
 * 출력 : 직선들의 교점들로 만들 수 있는 모든 별을 포함하는 최소 사각형
 */

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    List<Point> intersections = new ArrayList<>();

    public String[] solution(int[][] line) {
        int n = line.length;    // 직선의 개수

        /* 교점 구하기 */
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                findIntersection(line[i], line[j]);
            }
        }

        /* 중복된 교점 제거 */
        intersections = intersections.stream()
                .distinct()
                .collect(Collectors.toList());

        /* 최소 크기의 좌표평면 그리기 */
        long minX = Long.MAX_VALUE; long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE; long maxY = Long.MIN_VALUE;

        for (Point point : intersections) {
            long x = point.getX();
            long y = point.getY();
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        // 좌표평면 크기
        long width = maxX - minX + 1;
        long height = maxY - minY + 1;

        String[] answer = new String[(int)height];
        for (int i = 0; i < height; i++) {
            answer[i] = "";
            for (int j = 0; j < width; j++) {
                answer[i] += ".";
            }
        }

        /* 별 그리기 */
        StringBuilder sb;
        for (Point point : intersections) {
            long x = point.getX() - minX;
            long y = maxY - point.getY();

            sb = new StringBuilder(answer[(int)y]);
            sb.setCharAt((int)x, '*');

            answer[(int)y] = sb.toString();
        }

        return answer;
    }

    /* 교점 구하기 */
    private void findIntersection(int[] line1, int[] line2) {
        long div = (long)line1[0] * (long)line2[1] - (long)line1[1] * (long)line2[0];
        if (div == 0) return;   // 두 직선이 평행한 경우

        double x = (double)((long)line1[1] * (long)line2[2] - (long)line1[2] * (long)line2[1]) / div;
        double y = (double)((long)line1[2] * (long)line2[0] - (long)line1[0] * (long)line2[2]) / div;

        if (x == (long)x && y == (long)y) {
            Point point = new Point((long)x, (long)y);
            intersections.add(point);
        }
    }
}

class Point {
    private long x;
    private long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}