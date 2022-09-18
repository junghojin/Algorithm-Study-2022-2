import java.io.*;
import java.util.*;

public class BJ19238 {
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};

    static class Pos implements Comparable<Pos>{
        int x,y,f;

        public Pos(int x, int y, int f) {
            this.x = x;
            this.y = y;
            this.f = f;
        }

        @Override
        public int compareTo(Pos o) {
            if (o.f==this.f){
                if (o.x==this.x) return this.y-o.y;
                else return this.x-o.x;
            } else return o.f-this.f;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());
        int ans = -1;

        int[][] map = new int[N+2][N+2];
        for (int i = 0; i < N+2; i++) {
            map[0][i] = 1;
            map[i][0] = 1;
            map[N+1][i] = 1;
            map[i][N+1] = 1;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        int[][] passenger = new int[M][2];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = i+2;
            passenger[i][0] = Integer.parseInt(st.nextToken());
            passenger[i][1] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Pos> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[N+1][N+1];
        q.add(new Pos(sx,sy,F));
        visited[sx][sy]=true;

        // 승객 찾을 땐, true
        boolean flg = true;
        int fPass = 0;
        int savedF = 0;
        int cnt = 0;

        while(!q.isEmpty()){
            Pos now = q.poll();
            int x = now.x;
            int y= now.y;
            int f = now.f;

            if (flg&&map[x][y]>1){
                q.clear();
                flg=false;
                fPass=map[x][y]-2;
                map[x][y]=0;
                savedF=f;
                cnt++;
                visited = new boolean[N+1][N+1];
                q.add(new Pos(x,y,f));
                continue;
            }
            if (!flg&&passenger[fPass][0]==x&&passenger[fPass][1]==y){
                q.clear();
                f+=(savedF-f)*2;
                flg=true;
                if (cnt==M){
                    ans = f;
                    break;
                }
                visited = new boolean[N+1][N+1];
                q.add(new Pos(x,y,f));
                continue;
            }

            if (f==0) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];

                if (map[nx][ny]==1 || visited[nx][ny]) continue;
                visited[nx][ny]=true;
                q.add(new Pos(nx,ny,f-1));
            }
        }

        System.out.print(ans);
    }

}
