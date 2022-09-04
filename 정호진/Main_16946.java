import java.util.*;
import java.io.*;

public class Main_16946 {

    private static int map[][], result[][], N, M;
    private static boolean visited[][];
    private static int dr[] = new int[]{-1, 1, 0, 0}; // 상하좌우
    private static int dc[] = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken()); // 행
        M = Integer.parseInt(stk.nextToken()); // 열
        map = new int[N][M];
        result = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        // ===================== input end =============================

        // 탐색 시작
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                search(i, j, 0, true);
            }
        }

        // 출력
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                output.append(result[i][j]);
            }
            output.append("\n");
        }
        System.out.println(output.toString());
    }

    private static void search(int r, int c, int zeroCnt, boolean isFirst) {

        if(!isFirst || map[r][c] == 1) {
            return;
        } else {
            result[r][c] = zeroCnt + 1;
        }

        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 범위 벗어나면 다른 방향으로 이동
            if(!rangeCheck(nr, nc) || visited[nr][nc]) continue;

            if(map[nr][nc] > map[r][c]) {
                // 다음 향할 곳의 값이 내가 현재 있는 위치보다 값이 크다면 현재 위치의 값 갱신 후 더이상 이동할 필요가 X

                map[r][c] += map[nr][nc];
                continue;
            } else {
                // 다음 향할 곳의 값이 내가 현재 있는 위치보다 값이 작다면 다음 향할 곳의 값 갱신 필요
                visited[nr][nc] = true;
                search(nr, nc, map[r][c], false);
                visited[nr][nc] = false;
            }
        }
    }

    // 범위 체크
    private static boolean rangeCheck(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= M) return false;
        else return true;
    }
}
