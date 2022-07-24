package BJ;

import java.io.*;
import java.util.*;

public class BJ2463 {
    static int parent[], cnt[];
    static class Node implements Comparable<Node>{
        int a,b,w;

        public Node(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return o.w-this.w;
        }
    }
    static long unionParent(int a, int b){
        long sum = (long) cnt[a]*cnt[b];
        parent[b]=a;
        cnt[a]+=cnt[b];

        return sum;
    }

    static int getParent(int x){
        if (parent[x]==x) return x;
        return parent[x]=getParent(parent[x]);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[M];
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i]=i;
        }
        cnt = new int[N+1];
        Arrays.fill(cnt,1);

        long sum=0;
        long ans=0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(a,b,w);
            sum+=w;
        }

        Arrays.sort(nodes);
        int MOD = 1000000000;
        for (int i = 0; i < M; i++) {
            Node now = nodes[i];
            int a = getParent(now.a);
            int b = getParent(now.b);
            if (a!=b){
                ans+=(sum*(unionParent(Math.min(a,b),Math.max(a,b))%MOD))%MOD;
                ans%=MOD;
            }
            sum-=now.w;
        }

        System.out.print(ans);
    }
}
