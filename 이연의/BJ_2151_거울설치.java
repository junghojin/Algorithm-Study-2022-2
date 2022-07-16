
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static class Point implements Comparable<Point>{
        int i, j, d, c;
        Point(int i, int j, int d, int c){
            this.i = i;
            this.j = j;
            this.d = d;
            this.c = c;
        }
        @Override
        public int compareTo(Point o){
            return this.c - o.c;
        }
    }

    static int N,ans=Integer.MAX_VALUE;
    static char map[][];
    static int visited[][][];
    static int [][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    // 상,하 ,좌, 우 -> 0, 1, 2, 3
    static PriorityQueue<Point> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        map = new char[N][N];
        visited = new int[N][N][4];

        for(int i=0;i<N;i++){
            map[i]= bf.readLine().toCharArray();
        }
        for(int i = 0 ;i < N;i++){
            for(int j = 0; j<N;j++){
                for(int d = 0 ; d< 4;d++){
                    visited[i][j][d]= Integer.MAX_VALUE;
                }
            }
        }

        outer : for(int i = 0 ; i< N;i++){
            for(int j = 0; j < N; j++){
                if(map[i][j]=='#'){
                    for(int d = 0; d<4;d++){
                        int mx = i + dir[d][0];
                        int my = j + dir[d][1];
                        if(!boundary(mx,my)) continue ;
                        if(map[mx][my] != '*'){
                            pq.add(new Point(mx,my,d,0));
                            map[i][j] = '*';
                        }
                    }
                    break outer;
                }
            }
        }

        bfs();
        System.out.println(ans);
    }

    static void bfs(){


        while(!pq.isEmpty()){
            Point now  = pq.poll();

            if(visited[now.i][now.j][now.d] < now.c) continue;
            visited[now.i][now.j][now.d] = now.c;

            if(map[now.i][now.j]=='#'){
                ans = Math.min(ans, now.c);
                return;
            }
            // . 이면 진행 방향 그대로
            // i 이면 진행방향 바뀜 & 그대로
            if(map[now.i][now.j] != '*'){
                int mi = now.i + dir[now.d][0];
                int mj = now.j + dir[now.d][1];
                if(boundary(mi,mj) && map[mi][mj] != '*' && now.c+1 <= ans)
                    pq.offer(new Point(mi,mj, now.d, now.c));

                if(map[now.i][now.j]=='!'){
                    if(now.d == 0 || now.d == 1){
                        for(int i = 2 ; i<4;i++){
                            mi = now.i + dir[i][0];
                            mj = now.j + dir[i][1];
                            if(boundary(mi,mj) && map[mi][mj] != '*' && now.c+1 <= ans)
                                pq.offer(new Point(mi,mj,i, now.c+1));
                        }

                    }else{
                        for(int i = 0; i<2;i++){
                            mi = now.i + dir[i][0];
                            mj = now.j + dir[i][1];
                            if(boundary(mi,mj) && map[mi][mj] != '*' && now.c+1 <= ans)
                                pq.offer(new Point(mi,mj,i, now.c+1));
                        }
                    }
                }
            }
        }

    }
    static boolean boundary(int x, int y){
        if(x < 0 || x>=N || y < 0 || y >= N) return false;
        return true;
    }

}
