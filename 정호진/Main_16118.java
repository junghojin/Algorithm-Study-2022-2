
// 22.07.16.토 - 백준 16118 - 골드 1 - 달빛 여우 - 다익스트라

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16118 {

    // 각 노드 별 가장 짧은 거리를 담는 배열: distance_fox, distance_wolf
    static int  N, M, distance_fox[], distance_wolf[][];
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 그래프 만들기
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());

        distance_fox = new int[N + 1];
        distance_wolf = new int[N+1][3];
        graph = new ArrayList<>();

        // distance 배열 안의 값 초기화
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            Arrays.fill(distance_wolf[i], Integer.MAX_VALUE);
        }
        Arrays.fill(distance_fox, Integer.MAX_VALUE);

        for (int m = 0; m < M; m++) {
            str = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(str.nextToken());
            int to = Integer.parseInt(str.nextToken());
            int dis = Integer.parseInt(str.nextToken());

            graph.get(from).add(new Node(to, dis * 2));
            graph.get(to).add(new Node(from, dis * 2));
        }
        // =================== input end =====================

        // 2. 달빛 여우가 모든 정점(그루터기)에 도착하는데 걸리는 최소 비용
        search_fox(1);

        // 3. 달빛 늑대가 모든 정점(그루터기)에 도착하는데 걸리는 최소 비용
        search_wolf(1);

        // 4. 각 정점 최소 비용 비교 후 달빛 여우가 달빛 늑대보다 그루터기에 먼저 도착하는 경우 세기
        int cnt = 0;
        for (int n = 1; n <= N; n++) {
            if (distance_fox[n] < Math.min(distance_wolf[n][1], distance_wolf[n][2])) cnt++;
        }
        System.out.println(cnt);
    }

    // 달빛 여우 - 다익스트라 (일정 속도)
    private static void search_fox(int start) {

        distance_fox[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int vertex = node.vertex;
            int dis = node.distance;

            if (dis > distance_fox[vertex]) continue;

            for (Node linkedNode : graph.get(vertex)) {
                if (dis + linkedNode.distance < distance_fox[linkedNode.vertex]) {
                    distance_fox[linkedNode.vertex] = dis + linkedNode.distance;
                    pq.offer(new Node(linkedNode.vertex, distance_fox[linkedNode.vertex]));
                }
            }
        }
    }

    // 달빛 늑대 - 다익스트라 (홀수: 2배 빠른 속도, 짝수 1/2 느린 속도)
    private static void search_wolf(int start) {

        // 첫 시작은 0번째 방문 = 짝수 번째 방문 : 자기자신을 방문하므로 거리는 0
        distance_wolf[start][2] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 2));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int vertex = node.vertex; 
            int dis = node.distance;

            if (dis > distance_wolf[vertex][node.cnt]) continue;

            // 나를 거쳐 다른 노드로 갈 때의 최소 거리 - 다익스트라 
            // 현 노드를 매개로 다음 노드로의 거리를 구할 때는 짝수 -> 홀수, 홀수 -> 짝수가 된다.
            for (Node next : graph.get(vertex)) {
                int added_distance = 0;
                int next_Cnt= -1;

                // 현 노드가 짝수 번째 방문이면 다음 노드는 홀수 번째 방문 -> 속도 빨라짐
                if (node.cnt == 2) {
                    added_distance = dis + next.distance / 2;
                    next_Cnt = 1;
                } else {
                    // 현 노드가 홀수 번째 방문이면 다음 노드는 짝수 번째 방문 -> 속도 빨라짐
                    added_distance = dis + next.distance * 2;
                    next_Cnt = 2;
                }
                if (added_distance < distance_wolf[next.vertex][next_Cnt]) {
                    distance_wolf[next.vertex][next_Cnt] = added_distance;
                    pq.offer(new Node(next.vertex, added_distance, next_Cnt));
                }
            }
        }
    }


    private static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        int cnt; // 짝수번 방문이면 2, 홀수번 방문이면 1

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Node(int vertex, int distance, int cnt) {
            this.vertex = vertex;
            this.distance = distance;
            this.cnt= cnt;
        }

        @Override
        public int compareTo(Node o) { // 오름차순
            return distance - o.distance;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + vertex +
                    ", distance=" + distance +
                    ", visit_cnt=" + cnt +
                    '}';
        }
    }
}
