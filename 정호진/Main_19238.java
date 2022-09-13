import java.io.*;
import java.util.*;

// 22.09.13.화 - 백준 19238번 - 스타트 택시 (골드3) - BFS, 구현

public class Main_19238 {

    private static int N, M, K, R, C, map[][], startMap[][], arrivals[][]; // K: 남은 연료양, (R, C): 택시 위치
    private static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        // 입력 모두 읽기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken()); // 승객의 수
        K = Integer.parseInt(stk.nextToken()); // 남은 연료 양

        map = new int[N+1][N+1]; // (1,1) 부터 시작
        startMap = new int[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                // 벽이면 -1로 세팅할 것
                map[i][j] = Integer.parseInt(stk.nextToken()) == 1 ? -1 : 0;
            }
        }

        // 택시 위치 읽어오기
        stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());

        // 승객 위치 읽어오기
        arrivals = new int[M+1][2]; // 도착지의 위치가 같을 수 있기 때문에 따로 map에 넣지 않고, 도착지는 승객별 개별로 보관한다.
        for(int i = 1; i <= M; i++) {
            stk = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(stk.nextToken());
            int startC = Integer.parseInt(stk.nextToken());
            int arrivalR = Integer.parseInt(stk.nextToken());
            int arrivalC = Integer.parseInt(stk.nextToken());

            startMap[startR][startC] = i; // 출발지
            arrivals[i][0] = arrivalR; // 도착지
            arrivals[i][1] = arrivalC;
        }
        // ===================== input end =======================
        
        // 승객 수만큼 택시 운행 시작
        boolean flag = true; // 승객 태우기를 완료했는지 체크하는 flag
        for(int i = 0; i < M; i++) {
            // 승객 태우고 목적지 도착하기
            if(!move()) {
                // 모든 승객 태우기 완료 X || 승객 태우던 중 연료 바닥났을 경우
                flag = false; 
                break;
            }
        }

        // 모든 승객 태우기를 완료했으면 남은 연료양 출력
        if(flag) {
            System.out.println(K);
        } else {
            System.out.println(-1);
        }

    }

    /** 승객 태우기 */
    private static boolean move() {
        Queue<Point> queue = new LinkedList<>();
        Queue<Point> passengers = new PriorityQueue<>();
        boolean[][] visited = new boolean[N+1][N+1];

        visited[R][C] = true; // 현재 택시 위치는 방문 처리
        queue.add(new Point(R, C, 0));

        // 택시위치와 가장 가까운 승객 태우기
        while(!queue.isEmpty()) {
            Point p = queue.poll();

            // 택시가 위치한 자리에 승객이 있을 수 있기 때문에 방향 이동 전 승객이 있는지 여부 확인
            if(startMap[p.r][p.c] >= 1) {
                // 현재 거리를 기준으로 승객 태우기
                passengers.add(new Point(p.r, p.c, p.movement));
            }

            if(passengers.size() == M) break;

            for(int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                int movement = p.movement + 1;
                
                if(isOverRange(nr, nc) || visited[nr][nc] || map[nr][nc] == -1) continue;

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc, movement));
            }
        }

        // 태울 승객이 없으면 운행 실패
        if(passengers.isEmpty()) return false;
        else {
            return move2(passengers.poll());
        }
    }

    /** 승객 목적지로 데려다 주기 */
    private static boolean move2(Point point) { // cnt: 이동한 칸의 수, no: 승객 번호
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];

        visited[point.r][point.c] = true;
        int no = startMap[point.r][point.c];
        startMap[point.r][point.c] = 0;
        K -= point.movement;
        queue.add(new Point(point.r, point.c, 0));

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                int movement = p.movement + 1;

                // 다른 승객이 있는 곳이거나 다른 승객의 목적지는 지나칠 수 없다.
                if(isOverRange(nr, nc) || visited[nr][nc] || map[nr][nc] == -1) continue;

                // 연료가 고갈되었다면 더이상 이동할 수 없다.
                if(K - movement < 0) continue;

                // 목적지에 도착했다면
                if(nr == arrivals[no][0] && nc == arrivals[no][1]) {
                    K += (movement * 2) - movement; // 연료 2배로 충전하기
                    
                    // 목적지에서 다시 택시느 승객을 태우기 위해 출발한다.
                    R = nr;
                    C = nc;
                    return true;
                }

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc, movement));
            }
        }
        return false;
    }

    // 범위를 넘어섰는지의 여부
    private static boolean isOverRange(int r, int c) {
        if(r >= 1 && r <= N && c >= 1 && c <= N) return false;
        return true;
    }

    private static class Point implements Comparable<Point>{
        int r;
        int c;
        int movement; // 이동한 칸의 수

        public Point(int r, int c, int movement) {
            this.r = r;
            this.c = c;
            this.movement = movement;
        }

        @Override
        public int compareTo(Point p) {
            if(movement == p.movement) {
                return r == p.r ? c - p.c : r - p.r;
            } else return movement - p.movement;
        }
    }

}
