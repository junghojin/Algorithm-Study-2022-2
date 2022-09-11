import java.io.*;
import java.util.*;

public class BJ1774 {
    static class Node implements Comparable<Node>{
        int e;
        double d;

        public Node(int e, double d) {
            this.e = e;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.d,o.d);
        }
    }
    static double getDis(int x1,int x2, int y1,int y2){
        return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] list = new int[N][2];
        double[][] dis = new double[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dis[i][j] = getDis(list[i][0],list[j][0],list[i][1],list[j][1]);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            dis[a][b] = 0;
            dis[b][a] = 0;
        }

        boolean[] visited = new boolean[N];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited[0] = true;

        for (int i = 1; i < N; i++) {
            pq.add(new Node(i,dis[0][i]));
        }

        double ans=0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int e = now.e;

            if (visited[e]) continue;
            ans += now.d;
            visited[e] = true;

            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;

                pq.add(new Node(i,dis[e][i]));
            }
        }
        System.out.printf("%.2f",ans);
    }
}
