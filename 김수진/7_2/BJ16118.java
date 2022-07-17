package BJ;

import java.io.*;
import java.util.*;

public class BJ16118 {
    static class Node implements Comparable<Node>{
        int n,o;
        double w;

        public Node(int n, double w) {
            this.n = n;
            this.w = w;
        }

        public Node(int n, double w, int o) {
            this.n = n;
            this.w = w;
            this.o = o;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.w,o.w);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] node = new List[N];

        for (int i = 0; i < N; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());

            node[a].add(new Node(b,d));
            node[b].add(new Node(a,d));
        }

        PriorityQueue<Node> pq = new PriorityQueue();

        // 늑대
        double[][] wolf = new double[2][N];
        int[] fox = new int[N];

        Arrays.fill(wolf[0], Integer.MAX_VALUE);
        Arrays.fill(wolf[1], Integer.MAX_VALUE);
        Arrays.fill(fox, Integer.MAX_VALUE);

        wolf[0][0]=0;
        fox[0]=0;

        // 여우
        for (Node n:node[0]) pq.add(n);

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if (fox[now.n]!=Integer.MAX_VALUE) continue;
            fox[now.n] = (int) now.w;

            for(Node n:node[now.n]){
                if (fox[n.n]!=Integer.MAX_VALUE) continue;
                pq.add(new Node(n.n, now.w + n.w));
            }
        }

        // 늑대
        for (Node n:node[0]) pq.add(new Node(n.n,n.w*0.5,1));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int no = (now.o+1)%2;
            if (wolf[now.o][now.n]!=Integer.MAX_VALUE) continue;
            wolf[now.o][now.n] = now.w;

            for(Node n:node[now.n]){
                if (wolf[no][n.n]!=Integer.MAX_VALUE) continue;
                pq.add(new Node(n.n, now.w + n.w*(no==1?0.5:2),no));
            }
        }

        int cnt=0;

        for (int i = 1; i < N; i++) {
            if (fox[i] < Math.min(wolf[1][i], wolf[0][i])) cnt++;
        }
        System.out.print(cnt);
    }
}
