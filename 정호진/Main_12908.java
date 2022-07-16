
// 22. 07. 17 - 백준 12908 - 텔레포트 3 - 다익스트라

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12908 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] graph = new long[8][8];
        ArrayList<Node> node_info = new ArrayList<>();


        StringTokenizer stk = null;
        for (int i = 0; i < 2; i++) {
            stk = new StringTokenizer(br.readLine(), " ");
            node_info.add(new Node(Long.parseLong(stk.nextToken()), Long.parseLong(stk.nextToken())));
        }

        for (int i = 0; i < 3; i++) {
            stk = new StringTokenizer(br.readLine(), " ");
            node_info.add(new Node(Long.parseLong(stk.nextToken()), Long.parseLong(stk.nextToken())));
            node_info.add(new Node(Long.parseLong(stk.nextToken()), Long.parseLong(stk.nextToken())));
        }
        // ============================ input end ===================================

        // 1. 그래프 거리 초기화
        for (int i = 0; i < 8; i++) {
            Node n1 = node_info.get(i);
            for (int j = 0; j < 8; j++) {
                Node n2 = node_info.get(j);
                long dis = Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y);
                graph[i][j] = graph[j][i] = dis;
            }
        }

        for (int i = 7; i >= 2; i -= 2) {
            Node n1 = node_info.get(i);
            Node n2 = node_info.get(i - 1);
            long dis = 10;

            graph[i][i - 1] = graph[i - 1][i] = Math.min(graph[i][i - 1], dis);

        }

        // 2. 다익스트라 - 첫 정점부터 각 정점까지의 최소 거리 구하기
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] min_dis = new long[8];
        Arrays.fill(min_dis, Long.MAX_VALUE);
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int index = current.index;
            long distance = current.distance;

            if (min_dis[index] < distance) continue;

            for (int j = 0; j < 8; j++) {
                if (index != j && graph[index][j] + distance < min_dis[j]) {
                    min_dis[j] = graph[index][j] + distance;
                    pq.add(new Node(j, min_dis[j]));
                }
            }
        }

        System.out.println(min_dis[1]);
    }


    private static class Node implements Comparable<Node> {
        long x;
        long y;
        int index;
        long distance;

        public Node(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public Node(int index, long distance) {
            this.index = index;
            this.distance = distance;
        }


        @Override
        public int compareTo(Node o) {
            if (distance < o.distance) return -1;
            else return 1;
        }
    }
}
