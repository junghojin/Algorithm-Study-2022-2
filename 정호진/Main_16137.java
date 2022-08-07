import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16137 {

    static int map[][], minTime, N, M; // minTime: 견우가 직녀에게 갈 수 있는 최소 시간
    static boolean visited[][][]; // [][][]에서 마지막 []는 N번째 0에(오작교가 없는 곳에) 오작교를 세우고 건넌지 있는지 없는지의 여부
    static int[] dr = {-1, 0, 1, 0}; // 상, 좌, 하, 우
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N][M];
        minTime = Integer.MAX_VALUE;
        int zeroCnt = 0; // 오작교를 놓을 수 있는 곳의 개수
        visited = new boolean[N][M][2]; // 3: 0은 오작교를 만나지 않은 경우, 1은 오작교를 만난 경우

        for (int r = 0; r < N; r++) {
            stk = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 0 && crossCheck(r, c)) { // 교차로를 체크하여 건널 수 없는 경우라면 -1로 세팅
                    map[r][c] = -1;
                }
            }
        }

//        for(int r = 0; r < R; r++) {
//            System.out.println(Arrays.toString(map[r]));
//        }

        // ============================== input end ==============================
        bfs();
        System.out.println(minTime);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 0}); // 0: r, 1: c, 2: time, 3: 오작교를 건설하였는지, 건설하지 않았는지의 여부
        visited[0][0][0] = true; // 아직 오작교를 건설하지 않았으므로 0,0에서 시작하여 (0)오작교를 건설하지 않았다고 표시

        while (!queue.isEmpty()) {
            int[] currentInfo = queue.poll();

            if (currentInfo[0] == N - 1 && currentInfo[1] == N - 1) {
                minTime = Math.min(minTime, currentInfo[2]);
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nr = currentInfo[0] + dr[d];
                int nc = currentInfo[1] + dc[d];
                int nt = currentInfo[2] + 1;
                int isBuilt = currentInfo[3];
                if (isOverBoundary(nr, nc) || map[nr][nc] == -1 || visited[nr][nc][isBuilt])
                    continue;
                if (map[nr][nc] == 0 && isBuilt == 0) { // 오작교를 만들 수 있는 곳이고 아직 건설된 오작교가 없다면? 그리고 오작교를 생성할 수 있는 주기라면?
                    // 오작교 건설했을 때, 건널 수 있는 주기인 경우
                    if(M % nt == 0) {
                        visited[nr][nc][1] = true;
                        queue.offer(new int[]{nr, nc, nt, 1});
                    }
                } else if (map[nr][nc] >= 2) { // 오작교가 존재하고,
                    if (map[nr][nc] % nt == 0 && map[currentInfo[0]][currentInfo[1]] == 1) { // 오작교를 건널수 있으며 오작교를 연속으로 건너는 것이 아니라면? 이동 가능
                        visited[nr][nc][isBuilt] = true;
                        queue.offer(new int[]{nr, nc, nt, isBuilt});
                    }
                } else if (map[nr][nc] == 1) { // 일반 땅이라면 길을 건넌다.
                    visited[nr][nc][isBuilt] = true;
                    queue.offer(new int[]{nr, nc, nt, isBuilt});
                }
            }
        }
    }


    private static boolean isOverBoundary(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N)
            return true;
        else
            return false;
    }

    private static boolean crossCheck(int r, int c) {
        boolean flag = false;

        // 절벽의 상 혹은 하 방향에 절벽이 있는지의 여부 확인
        if ((r - 1 >= 0 && map[r - 1][c] == 0) || (r + 1 < N && map[r + 1][c] == 0))
            flag = true;

        // 절벽이 서로 교차하는지의 여부 확인
        if (flag) { // 현재 위치의 상 혹은 하 방향에 절벽이 있다.
            if ((c - 1 >= 0 && map[r][c - 1] == 0) || (c + 1 < N && map[r][c + 1] == 0)) {// 현재 위치의 좌 혹은 우 방향에 절벽이 있다.
                flag = true;
            } else {// 현재 위치의 좌 혹은 우 방향에는 절벽이 없다. (절벽이 교차하지 않는다.)
                flag = false;
            }
        }
        return flag;
    }
}



