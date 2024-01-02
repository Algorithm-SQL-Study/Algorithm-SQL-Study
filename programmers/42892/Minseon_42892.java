/**
 * == 42892. 길 찾기 게임 ==
 * 입력 : 이진트리를 구성하는 노드들의 좌표가 담긴 배열 nodeinfo
 * 출력 : 이진트리를 전위 순회, 후위 순회한 결과
 */

import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        /* 우선순위 큐 */
        PriorityQueue<Node> nodes = new PriorityQueue(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                int result = Integer.compare(node2.y, node1.y);
                if (result == 0) result = Integer.compare(node1.x, node2.x);
                return result;
            }
        });

        /* 노드 정렬 */
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            nodes.add(new Node(x, y, i+1));
        }

        /* 이진트리 만들기 */
        BinaryTree binaryTree = new BinaryTree();
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            binaryTree.insertNode(node);
        }

        /* 이진트리 순회 */
        int[][] answer = new int[2][nodeinfo.length];
        Node root = binaryTree.root;
        binaryTree.preorder(root);
        binaryTree.postorder(root);

        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = binaryTree.preorderResult.get(i);
            answer[1][i] = binaryTree.postorderResult.get(i);
        }

        return answer;
    }
}

class Node {
    int x;
    int y;
    int value;
    Node left;
    Node right;

    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    Node root;
    List<Integer> preorderResult;
    List<Integer> postorderResult;

    public BinaryTree() {
        this.root = null;
        this.preorderResult = new ArrayList<>();
        this.postorderResult = new ArrayList<>();
    }

    /* 노드 삽입 */
    public Node insert(Node node, Node newNode) {
        if (node == null) return newNode;
        if (newNode.x < node.x) node.left = insert(node.left, newNode);
        else if (newNode.x > node.x) node.right = insert(node.right, newNode);

        return node;
    }

    public void insertNode(Node node) {
        this.root = insert(this.root, node);
    }

    /* 전위 순회 */
    public void preorder(Node node) {
        if (node != null) {
            preorderResult.add(node.value);
            preorder(node.left);
            preorder(node.right);
        }
    }

    /* 후위 순회 */
    public void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            postorderResult.add(node.value);
        }
    }
}