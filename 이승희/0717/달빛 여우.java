// BOJ - 달빛 여우(16118번)
// 다익스트라
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16118 {
    public static int n, m;
    public static int[] distance1;
    public static int[][] distance2;
    public static int INF = (int)2e9;
    public static class Node implements Comparable<Node>{
        int idx;
        int dis;
        int run;

        public Node(int idx, int dis){
            this.idx = idx;
            this.dis = dis;
        }

        public Node(int idx, int dis, int run){
            this.idx = idx;
            this.dis = dis;
            this.run = run;
        }

        @Override
        public int compareTo(Node o) {
            if(this.dis < o.dis) return -1;
            return 1;
        }
    }

    public static ArrayList<Node>[] list = new ArrayList[40001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        distance1 = new int[n+1];
        distance2 = new int[2][n+1];


        for(int i=0;i<=n;i++){
            distance1[i] = INF;
            distance2[0][i] = INF;
            distance2[1][i] = INF;
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c*2));
            list[b].add(new Node(a, c*2));
        }

        dijkstra();
        dijkstra2();


        int cnt = 0;
        for(int i=2;i<=n;i++){
            int min_value = (int)2e9;
            min_value = Math.min(Math.min(min_value, distance2[0][i]), distance2[1][i]);
            if(distance1[i] < min_value) cnt++;
        }

        System.out.println(cnt);
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        distance1[1] = 0;

        while (!pq.isEmpty()){
            Node node = pq.poll();
            if(distance1[node.idx] < node.dis) continue;

            for(Node next:list[node.idx]){
                int next_idx = next.idx;
                int next_dis = next.dis;
                int cost = node.dis + next_dis;
                if(cost < distance1[next_idx]){
                    distance1[next_idx] = cost;
                    pq.add(new Node(next_idx, cost));
                }
            }

        }

    }

    public static void dijkstra2(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));
        distance2[0][1] = 0;

        while (!pq.isEmpty()){
            Node node = pq.poll();
            if(distance2[node.run][node.idx] < node.dis) continue;


            for(Node next:list[node.idx]){
                int next_idx = next.idx;
                int next_dis = next.dis;

                int cost = node.dis + (node.run == 0 ? next_dis/2 : next_dis*2);
                if(cost < distance2[1-node.run][next_idx]){
                    distance2[1-node.run][next_idx] = cost;
                    pq.add(new Node(next_idx, cost, 1-node.run));
                }
            }

        }

    }
}
