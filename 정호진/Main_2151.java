import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_2151 {

    private static int startR, startC, N;
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        startR = -1;
        startC = -1;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '#' && startR == -1) {
                    startR = i;
                    startC = j;
                    map[i][j] = '.';
                }
            }
        }
        // ================ input end =====================

        bfs();
    }

    private static int dr[] = {-1, 1, 0, 0};
    private static int dc[] = {0, 0, -1, 1};

    private static void bfs() {
        PriorityQueue<Mirror> pq = new PriorityQueue<>();
        boolean[][][] visited = new boolean[N][N][4];

        for (int i = 0; i < 4; i++) {
            pq.add(new Mirror(startR, startC, i, 0));
            visited[startR][startC][i] = true;
        }

        while (!pq.isEmpty()) {
            Mirror current = pq.poll();
            int nd = current.dir;
            int nr = current.r + dr[nd];
            int nc = current.c + dc[nd];
            int m = current.cnt; // 지나온 거울의 수

            if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc][nd] || map[nr][nc] == '*') continue;

            if (map[nr][nc] == '#') {
                System.out.println(m);
                return;
            }

            if (map[nr][nc] == '.') {
                visited[nr][nc][nd] = true;
                pq.add(new Mirror(nr, nc, nd, m));
            } else if (map[nr][nc] == '!') { // 거울일 경우
                // 경우 1: 거울 설치 X
                pq.add(new Mirror(nr, nc, nd, m));
                visited[nr][nc][nd] = true;

                // 경우 2: 거울 설치 O
                for (int i = 0; i < 4; i++) {
                    if (i != nd) {
                        pq.add(new Mirror(nr, nc, i, m + 1));
                        visited[nr][nc][i] = true;
                    }
                }
            }
        }
    }

    private static class Mirror implements Comparable<Mirror> {
        int r;
        int c;
        int dir;
        int cnt; // 거울 개수

        public Mirror(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Mirror o) {
            return cnt - o.cnt;
        }
    }
}
