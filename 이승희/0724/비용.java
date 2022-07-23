// BOJ - 비용(2463번)
// Union find

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2463 {
    public static int n, m;
    public static int[] parent;
    public static int[] child;

    public static class Edge implements Comparable<Edge>{
        int x;
        int y;
        int cost;
        public Edge(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return o.cost - this.cost;
        }
    }
    public static ArrayList<Edge> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        long sum = 0;
        list = new ArrayList<>();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sum += c;
            list.add(new Edge(Math.min(a, b), Math.max(a, b), c));
        }
        parent = new int[n+1];
        child = new int[n+1];

        for(int i=1;i<=n;i++){
            parent[i] = i;
            child[i] = 1;
        }

        long answer = 0;
        Collections.sort(list);

        for(Edge edge:list){
           answer += sum * union(edge.x, edge.y);
           answer %= 1000000000;
           sum -= edge.cost;
        }

        System.out.println(answer);

    }

    public static int find_parent(int x){
        if(parent[x] == x) return x;
        return parent[x] = find_parent(parent[x]);
    }

    public static long union(int a, int b){
        int parentA = find_parent(a);
        int parentB = find_parent(b);
        if(parentA == parentB) return 0;
        parent[parentB] = parentA;
        long cnt = (long) child[parentA] * child[parentB];
        child[parentA] += child[parentB];
        child[parentB] = 0;
        return cnt;

    }

}
