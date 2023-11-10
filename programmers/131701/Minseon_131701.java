/**
 * == 131701. 연속 부분 수열 합의 개수 ==
 * 입력 : 원형 수열의 모든 원소 elements
 * 출력 : 연속 부분 수열 합으로 만들 수 있는 수의 개수
 */

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        /* 원형 리스트 생성 */
        CircularLinkedList list = new CircularLinkedList();

        for (int num : elements) {
            list.addNode(num);
        }

        /* 개수 별로 부분합 구하기 */
        Set<Integer> hashSet = new HashSet<>();     // 연속합 집합
        for (int i = 0; i < elements.length; i++) {
            Node start = list.head;         // 연속합을 구하는 시작 지점
            while (true) {
                Node node = start;
                int subArraySum = 0;
                /* 길이가 i인 연속 부분 수열 합 */
                for (int j = 0; j < i; j++) {
                    subArraySum += node.data;
                    node = node.next;
                }
                hashSet.add(subArraySum);
                if (start.next == list.head) break; // 한 바퀴 돌았을 때
                else start = start.next;
            }
        }

        return hashSet.size();
    }
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    Node head;
    Node tail;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addNode(int data) {
        Node node = new Node(data);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        tail.next = head;
    }
}