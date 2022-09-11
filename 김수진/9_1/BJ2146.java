import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2146 {
    static class Point implements Comparable<Point>{
        int x,y,d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Point o) {
            return this.d-o.d;
        }
    }

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1){
                    PriorityQueue<Point> pq = new PriorityQueue<Point>();

                    boolean[][] visited = new boolean[N][N];

                    pq.add(new Point(i,j,0));
                    map[i][j] = 2;
                    visited[i][j] = true;


                    t: while(!pq.isEmpty()){
                        Point now = pq.poll();
                        int x = now.x;
                        int y = now.y;
                        int d = now.d;


                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if(nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny]) continue;

                            visited[nx][ny] = true;

                            if (map[nx][ny]==1) {
                                if (d!=0) {
                                    ans = Math.min(ans, d);
                                    break t;
                                }
                                map[nx][ny] = 2;
                                pq.add(new Point(nx,ny,0));
                            } else pq.add(new Point(nx,ny,d+1));

                        }

                    }
                }
            }
        }

        System.out.print(ans);

    }
}
