import java.util.*;
import java.io.*;

// 22. 09. 04. 일 - 백준 16946 - 벽 부수고 이동하기 (골드 2)

public class Main_16946 {

    private static int map[][], group[][], groupSize[], N, M;
    private static boolean visited[][];
    private static int dr[] = new int[]{-1, 1, 0, 0}; // 상하좌우
    private static int dc[] = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken()); // 행
        M = Integer.parseInt(stk.nextToken()); // 열
        map = new int[N][M];
        group = new int[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        // ===================== input end ============================

        // Group 찾기
        int idx = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
               if(map[i][j] == 0 && group[i][j] == 0)
                   floodFill(i, j, ++idx);
            }
        }

        // Group의 idx 별 칸 수 count
        groupSize= new int[idx+1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int groupIdx = group[i][j];
                groupSize[groupIdx]++;
            }
        }

        // 출력
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                output.append(countBlock(i, j));
            }
            output.append("\n");
        }
        System.out.println(output.toString());
    }

    // 벽이 있는 칸의 인접한 칸들의 그룹번호를 더하여 이동할 수 있는 칸의 개수 구하기
    private static int countBlock(int r, int c) {
        if(map[r][c] == 0) return 0;

        // 동일한 인덱스는 더하지 않기 위해 Set 이용
        Set<Integer> set = new HashSet<>();
        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(isOverRange(nr, nc) || group[nr][nc] == 0) continue;
            set.add(group[nr][nc]);
        }

        int cnt = 1;
        for(int idx : set) {
            cnt += groupSize[idx];
        }
        return cnt % 10;
    }


    // 플러드필 알고리즘: 주위 빈 칸(0) 모두 찾아 그룹화하기
    private static void floodFill(int r, int c, int idx) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        group[r][c] = idx;

        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            for(int d = 0; d < 4; d++) { // 4방 탐색하면서 그룹화 하기
                int nr = point[0] + dr[d];
                int nc = point[1] + dc[d];
                if(isOverRange(nr, nc) || group[nr][nc] != 0 || map[nr][nc] == 1) // 연결되어있는 지점
                    continue;
                queue.add(new int[]{nr, nc});
                group[nr][nc] = idx;
            }
        }
    }

    // 범위 체크
    private static boolean isOverRange(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= M) return true;
        else return false;
    }
}
