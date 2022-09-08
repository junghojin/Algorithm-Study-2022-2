import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146 {
    public static int n;
    public static int s;
    public static int[][] map;
    public static int[][] check;
    public static int min_value;

    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        s = 0;
        map = new int[n][n];
        check = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min_value = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(check[i][j] == 0 && map[i][j] == 1){
                    bfs(i, j, ++s);
                }
            }
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == 1) {
                    boolean[][] visited = new boolean[n][n];
                    dfs(i, j, check[i][j], 0, visited);
                }
            }
        }
        System.out.println(min_value);
    }

    public static void bfs(int x, int y, int c){
        Queue<Node> q = new LinkedList<>();
        check[x][y] = c;
        q.add(new Node(x, y));
        while (!q.isEmpty()){
            Node node = q.poll();
            for(int d=0;d<4;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if(0 <= nx && nx < n && 0 <= ny && ny < n){
                    if(map[nx][ny] == 1){
                        if(check[nx][ny] == 0){
                            check[nx][ny] = c;
                            q.add(new Node(nx, ny));
                        }
                    }
                }
            }
        }
    }

    public static void dfs(int x, int y, int c, int len, boolean[][] visited){
        visited[x][y] = true;
        if(len > min_value) return;

        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(!visited[nx][ny]){
                if(check[nx][ny] == c){
                    return;
                } else if(check[nx][ny] == 0){
                    dfs(nx, ny, c, len+1, visited);
                } else {
                    min_value = Math.min(min_value, len);
                    return;
                }
            }
        }
    }
}
