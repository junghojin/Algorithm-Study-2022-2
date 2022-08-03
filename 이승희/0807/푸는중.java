// 견우와 직녀
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12783 {
    public static int n, m;
    public static int[][] map;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static int min_time = Integer.MAX_VALUE;
    public static class Node {
        int x;
        int y;
        int dis;
        boolean use;
        public Node(int x, int y, int dis, boolean use){
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.use = use;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.println(min_time);
    }

    public static void bfs(){
        int[][] visited = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        Queue<Node> q = new LinkedList<>();
        visited[0][0] = 0;
        q.add(new Node(0, 0, 0, false));
        while (!q.isEmpty()){
            Node node = q.poll();
            if(node.x == n-1 && node.y == n-1){
                continue;
            }
            for(int d=0;d<4;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(visited[nx][ny] < node.dis+1) continue;
                if(map[nx][ny] == 1){
                    visited[nx][ny] = node.dis+1;
                    q.add(new Node(nx, ny, node.dis+1, node.use));
                } else if(map[nx][ny] >= 2){

                    if((node.dis+1) % map[nx][ny] == 0){
                        visited[nx][ny] = node.dis+1;
                        q.add(new Node(nx, ny, node.dis+1, true));
                    }
                } else {

                    if(node.use) continue;
                    if(check(nx, ny)) continue;
                    if((node.dis+1) % m == 0){
                        visited[nx][ny] = node.dis;
                        q.add(new Node(nx, ny, node.dis+1, true));
                    }
                }
            }
        }

//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                if(visited[i][j] == Integer.MAX_VALUE){
//                    System.out.print('m'+" ");
//                } else {
//                    System.out.print(visited[i][j]+" ");
//                }
//            }
//            System.out.println();
//        }


        min_time = visited[n-1][n-1];
    }

    public static boolean check(int x, int y){
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            int cnt = 0;
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if(map[nx][ny] == 0) cnt++;
            nx = x + dx[(d+1)%4];
            ny = y + dy[(d+1)%4];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if(map[nx][ny] == 0) cnt++;
            if(cnt == 2) return true;
        }
        return false;
    }

}
