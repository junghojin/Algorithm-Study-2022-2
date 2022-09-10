// BOJ - 우주신과의 교감(1774번)
// MST

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1774 {
    public static int[] parent;
    public static Node[] node;

    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int a;
        int b;
        double dist;
        public Edge(int a, int b, double dist){
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    public static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        node = new Node[n+1];
        parent = new int[n+1];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            node[i+1] = new Node(x, y);
            parent[i+1] = i+1;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union_parent(a, b);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=1;i<=n;i++){
            for(int j=i+1;j<=n;j++){
                double cost = Math.sqrt(Math.pow(node[i].x - node[j].x, 2) + Math.pow(node[i].y - node[j].y, 2));

                pq.add(new Edge(i, j, cost));
            }
        }

        double cost = 0;
        while (!pq.isEmpty()){
            Edge edge = pq.poll();
            if(find_parent(edge.a) != find_parent(edge.b)){
                union_parent(edge.a, edge.b);
                cost += edge.dist;
            }
        }


        System.out.printf("%.2f", cost);


    }

    public static int find_parent(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find_parent(parent[x]);
    }

    public static void union_parent(int a, int b){
        a = find_parent(a);
        b = find_parent(b);
        if(a < b){
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
