import java.io.*;
import java.util.*;

// 22.09.06.화 - 백준 2146 - 다리만들기 (골드3) - BFS, FloodFill

public class Main_2146 {
    static int N, map[][], indexedMap[][], minLength = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<Point> lands;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        indexedMap = new int[N][N];
        lands = new LinkedList<Point>();

        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // ================== input end ========================

        // 한 면 이상 바다와 붙어있는 지점 리스트에 넣기 - 육지 찾기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0) continue; // 바다이면 살펴볼 필요가 없다.

                for(int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if(isOverRange(nr, nc)) continue;

                    // 한 면이라도 바다와 붙어있는지 확인해서 붙어있다면 lands에 넣기 - 끝
                    if(map[nr][nc] == 0) {
                        lands.add(new Point(i, j));
                        break;
                    }
                }
            }
        }

        // 리스트에서 인덱스 별 지도 만들기, 한 면이 바다와 맞닿아이지 않은 육지는 -1로 표시 / 바다는 0으로 표시
        int idx = 1; // 육지 인덱스 부여
        for(Point p : lands) {
            if(indexedMap[p.r][p.c] >= 1) continue; // 이미 육지라고 인덱스화 되었으므로 pass
            else floodFill(p.r, p.c, idx++);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(indexedMap[i][j] >= 1) continue;
                indexedMap[i][j] = 0;
            }
        }
        // ================= 인덱스화 잘 되었는지 확인 완료 ================

        // 육지에서 다른 육지로의 최소 거리 구하기
        for(Point p : lands) {
            search(p.r, p.c);
        }

        System.out.println(minLength);
    }

    // 최소 거리 찾기
    private static void search(int r, int c) {
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visited = new boolean[N][N];
        visited[r][c] = true;
        queue.add(new Point(r, c, 0));

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(isOverRange(nr, nc) || visited[nr][nc]) continue;

                if(indexedMap[nr][nc] >= 1 && indexedMap[nr][nc] != indexedMap[r][c]) {
                    minLength = Math.min(p.d, minLength);
                    return;
                }

                if(indexedMap[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc, p.d + 1));
                }
            }
        }
    }

    // 같은 육지별로 인덱스화
    private static void floodFill(int r, int c, int idx) {
        Queue<Point> queue = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];

        queue.add(new Point(r, c));
        indexedMap[r][c] = idx;

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(isOverRange(nr, nc)) continue;
                if(visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                indexedMap[nr][nc] = idx;
                queue.add(new Point(nr, nc));
            }
        }
    }

    private static boolean isOverRange(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        else return false;
    }

    private static class Point {
        int r;
        int c;
        int d; // distance : 지나온 칸의 수

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}
