package Baekjoon;

import java.io.BufferedReader;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1719_택배 {

    static class Node implements Comparable<Node> {
        int to,w,s;

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", w=" + w +
                    '}';
        }

        public Node(int to, int w, int s){
            this.to = to;
            this.w = w;
            this.s = s;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static int N,M;
    static ArrayList<Node>[] graph;
    static int[] distance;
    static int[][] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        distance = new int[N+1];
        nodes = new int[N+1][N+1];

        for(int i = 1 ; i <= N; i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int i = 0 ; i < M ;i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, w, to));
            graph[to].add(new Node(from, w, from));
        } // input

        for(int i = 1; i <= N;i++){
            dijkstra(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1;  i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j) sb.append("- ");
                else sb.append(nodes[j][i]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dijkstra(int n){
        // 초기화
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[n] = 0;
        pq.offer(new Node(n,0,n));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.w > distance[now.to]) continue;

            for(Node node : graph[now.to]){
                int cost = node.w + distance[now.to];
                if(distance[node.to] > cost){
                    distance[node.to] = cost;
                    pq.offer(new Node(node.to, cost,now.s));
                    nodes[n][node.to] = now.to;
                }
            }
        }

    }

}
