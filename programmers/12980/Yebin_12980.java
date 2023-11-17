// 효율성 테스트 전부 실패

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        Queue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(Point::getBattery));
        pq.add(new Point(0, 0));

        while (!pq.isEmpty()) {
            Point point = pq.poll();
            int distance = point.getDistance();
            int battery = point.getBattery();

            if (distance == n) {
                ans = battery;
                break;
            }

            if (distance > n) {
                continue;
            }
            pq.add(new Point(distance + 1, battery + 1));   // 점프
            if (distance > 0) {
                pq.add(new Point(distance * 2, battery)); // 순간이동
            }
        }

        return ans;
    }

    private class Point {
        private int distance;
        private int battery;

        public Point(int distance, int battery) {
            this.distance = distance;
            this.battery = battery;
        }

        public int getDistance() {
            return this.distance;
        }

        public int getBattery() {
            return this.battery;
        }
    }
}

// --------------------------------
// 정답
public class Solution {
    public int solution(int n) {
        int battery = 0;

        while (n > 0) {
            if ((n % 2) == 0) {
                n /= 2;
                continue;
            }
            n--;
            battery++;
        }

        return battery;
    }
}