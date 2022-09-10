import java.util.*;
import java.io.*;

// 22.09.09.금 - 백준 1774 - 우주신과의 교감 - MST(Kruskal) - 골드 3

public class Main_1774 {

    static int N, M, parents[], points[][];
    static Queue<Edge> edgeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        points = new int[N+1][2]; // 1번 노드부터 시작하기 때문에 N+1, 각 좌표 값을 가지고 있음
        edgeList = new PriorityQueue<>();

        parents = new int[N+1];

        // 자기자신으로 부모 노드 세팅하기
        for(int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        // N개 노드의 좌표 값 담기
        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());

            points[i][0] = x;
            points[i][1] = y;
        }

        // 이미 연결된 노드의 에지를 0으로 세팅하여 넣기
        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(stk.nextToken());
            int node2 = Integer.parseInt(stk.nextToken());

            edgeList.add(new Edge(node1, node2, 0));
        }

        // 모든 노드들이 서로 연결되었을 때의 거리 구하여 에지리스트에 넣기
        for(int i = 1; i < N; i++) {
            for(int j = i + 1; j <= N; j++) {
                double distance = getDistance(i, j);
                edgeList.add(new Edge(i, j, distance));
            }
        }

        // Kruskal Algorithm - 최소 비용 탐색
        double result = 0;
        int cnt = 0;

        while(!edgeList.isEmpty()){
            Edge edge = edgeList.poll();
            if(union(edge.node1, edge.node2)) {
                result += edge.distance;
                if(++cnt == N-1)
                    break;
            }
        }

        System.out.printf("%.2f", result);
    }

    private static double getDistance(int a, int b) {
        double squareX = Math.pow(points[a][0] - points[b][0], 2);
        double squareY = Math.pow(points[a][1] - points[b][1], 2);

        return Math.sqrt(squareX + squareY);
    }

    private static boolean union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);

        // 이미 한 집합에 속해있으면 추가해줄 필요가 없음
        if(aParent == bParent) return false;

        // 한 집합에 속한 경우가 아니라면, 속하게 만들어줄 것
        if(aParent < bParent)
            parents[bParent] = aParent;
        else
            parents[aParent] = bParent;
        return true;
    }

    private static int findParent(int node) {
        if(node == parents[node]) return node;
        else return parents[node] = findParent(parents[node]);
    }

    private static class Edge implements Comparable<Edge> {
        int node1; // 시작하는 노드
        int node2; // 연결된 노드
        double distance;

        public Edge(int node1, int node2, double distance) {
            this.node1 = node1;
            this.node2 = node2;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge e) {
            return distance < e.distance ? -1 : +1;
        }
    }
}
 
