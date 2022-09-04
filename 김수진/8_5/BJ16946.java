import java.io.*;
import java.util.*;

public class BJ16946 {
    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1};
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j)-'0';
            }
        }

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]==0&&!visited[i][j]){
                    Queue<Point> pq = new LinkedList<>();
                    Queue<Point> pq2 = new LinkedList<>();

                    pq.add(new Point(i,j));
                    visited[i][j] = true;
                    int cnt=1;

                    while(!pq.isEmpty()){
                        Point now = pq.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = now.x+dx[k];
                            int ny = now.y+dy[k];

                            if(nx<0||nx>=N||ny<0||ny>=M||visited[nx][ny]) continue;
                            if (map[nx][ny]!=0){
                                if(visited[nx][ny]) continue;
                                pq2.add(new Point(nx,ny));
                                visited[nx][ny]=true;
                                continue;
                            }
                            cnt++;

                            visited[nx][ny] = true;
                            pq.add(new Point(nx,ny));
                        }
                    }

                    while(!pq2.isEmpty()){
                        Point now = pq2.poll();
                        visited[now.x][now.y]=false;

                        map[now.x][now.y] += cnt;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]%10);
            }
            sb.append("\n");
        }


        System.out.print(sb);

    }
}
