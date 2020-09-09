package com.company.Programmers.Graph;

/**
 * 가장 먼 노드  Num49189
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
class Node implements Comparable<Node> {
    int index;
    int dist;
    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(dist, o.dist);
    }
}
class LongNode {
    public int solution(int n, int[][] edge) {
        int [] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        List<List<Integer>> adj_lists = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Integer> adj_list = new ArrayList<>();
            for (int[] e: edge) {
                if (e[0] == i) {
                    adj_list.add(e[1]);
                } else if (e[1] == i) {
                    adj_list.add(e[0]);
                }
            }
            adj_lists.add(adj_list);
        }
        PriorityQueue<Node> heap = new PriorityQueue<>();
        dists[1] = 0;
        heap.add(new Node(1, dists[1]));
        while (!heap.isEmpty()) {
            Node node = heap.remove();
            for (int indVisit: adj_lists.get(node.index)) {
                if (node.dist + 1 < dists[indVisit]) {
                    dists[indVisit] = node.dist + 1;
                    heap.add(new Node(indVisit, dists[indVisit]));
                }
            }
        }
        int max = 0;
        int answer = 0;
        dists[0] = 0;
        for (int el: dists) {
            if (el == max) {
                answer++;
            }
            if (el > max) {
                max = el;
                answer = 1;
            }
        }
        return answer;
    }
}
class longNodeTest{

    public static void main(String[] args) {

        int [][]edge ={{3, 6}, {4, 3}, {3, 2}, {1, 3},
                {1, 2}, {2, 4}, {5, 2}};
        System.out.println(new LongNode().solution(6,edge));
    }

}
