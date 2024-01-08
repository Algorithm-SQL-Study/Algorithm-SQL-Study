// https://school.programmers.co.kr/learn/courses/30/lessons/42892
// 출력 : 전위 순회, 후위 순회 결과 2차원 배열
// 입력 : 노드들로 구성된 이진트리 
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        // 정렬
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        Collections.sort(nodes, (a, b) -> b.y - a.y);

        // 그래프 생성
        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            insert(root, nodes.get(i));
        }

        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();

        // 순회
        preorder(root, preorderList);
        postorder(root, postorderList);

        // 결과
        int[][] answer = {
            preorderList.stream().mapToInt(Integer::intValue).toArray(),
            postorderList.stream().mapToInt(Integer::intValue).toArray()
        };

        return answer;
    }

    private void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }

    private void preorder(Node node, List<Integer> list) {
        if (node != null) {
            list.add(node.number);
            preorder(node.left, list);
            preorder(node.right, list);
        }
    }

    private void postorder(Node node, List<Integer> list) {
        if (node != null) {
            postorder(node.left, list);
            postorder(node.right, list);
            list.add(node.number);
        }
    }

    private class Node {
        int number;
        int x;
        int y;
        Node left;
        Node right;

        Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }
}
