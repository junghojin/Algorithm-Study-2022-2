import java.util.*;
import java.io.*; 

// 22.09.14.수 - 백준 2211 - 네트워크 복구 - 다익스트라

/**
 * MST가 아닌 다익스트라로 푸는 이유?
 *  MST의 경우 모든 노드를 연결하기 위한 최소 비용을 구하는 것으로 두 노드 간의 최소 비용을 보장하지 않는다.
 *  다익스트라의 경우 한 정점에서 다른 정점으로의 최소 비용을 구하는 것이고,
 *  이 문제의 경우 슈퍼노드라는 정점에서 다른 정점 사이의 최소 비용을 구하면서도
 *  슈퍼노드를 제외한 다른 모든 정점으로의 노선을 선택하는 것이기에 다익스트라를 적용하여
 *  슈퍼노드에서 다른 정점을 연결하기 위한 간선 정보(겹치는 경우 한 번만)를 추가하는 식으로 문제를 해결해나가면 된다.
 */

public class Main_2211 {

    static int N, M, map[][], dist[];
    static final int INF = 9876543;
    static Set<String> result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new int[N+1][N+1];
        dist = new int[N+1];
        result = new HashSet<>();

        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());
            // 양방향 그래프
            map[from][to] = cost;
            map[to][from] = cost;
        }
        // ================= input end ====================

        // 네트워크에서 슈퍼노드(1)과 다른 정점들 사이의 최소 비용 찾기
        Arrays.fill(dist, INF);
        dijkstra(1);

        // 결과 출력
        System.out.println(result.size());
        for(String each : result) {
            System.out.println(each);
        }
    }

    private static void dijkstra(int start) {
        boolean[] visited = new boolean[N+1];
        int[] path = new int[N+1];
        Queue<Edge> pq = new PriorityQueue<>();
        dist[start] = 0; // start에서 start 까지의 거리는 0
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int currentNode = edge.node;
            visited[currentNode] = true;

            for(int i = 1; i <= N; i++) {
                // 이미 방문한 노드가 아니거나, 정점이 연결되어있을 때만 최소 비용을 찾을 수 있다.
                if(map[currentNode][i] == 0 || visited[i]) continue;

                if(dist[i] > dist[currentNode] + map[currentNode][i]) {
                    dist[i] = dist[currentNode] + map[currentNode][i];
                    pq.offer(new Edge(i, dist[i]));

                    // 최소 연결된 간선 정보를 구하기 위함
                    // i 정점에 마지막으로 연결된 노드
                    path[i] = currentNode; 
                }
            }
        }

        // 최소 개수의 회선만을 복구해야하므로 최소 N-1개의 간선은 연결되어야 한다.
        for(int i = 2; i <= N; i++) {
            result.add(i + " " + path[i]);
        }
    }

    private static class Edge implements Comparable<Edge> {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }
}
