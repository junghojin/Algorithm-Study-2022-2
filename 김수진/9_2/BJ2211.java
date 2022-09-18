import java.io.*;
import java.util.*;

public class BJ2211 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]>[] node = new ArrayList[N+1];
        int pre[] = new int[N+1];

        for (int i = 0; i <= N; i++) node[i] = new ArrayList();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            node[a].add(new int[]{b,w});
            node[b].add(new int[]{a,w});
        }

        boolean[] visited = new boolean[N+1];
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        pq.add(new int[] {1,1,0});

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            if(visited[now[1]]) continue;
            pre[now[1]]=now[0];
            visited[now[1]]=true;

            for(int[] c:node[now[1]]){
                if (visited[c[0]])continue;
                pq.add(new int[] {now[1],c[0],c[1]+now[2]});
            }
        }

        System.out.println(N-1);
        for (int i=2;i<=N;i++) System.out.println(i+" "+pre[i]);
    }
}
