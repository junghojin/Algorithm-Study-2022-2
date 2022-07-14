// BOJ - 거울설치(2151번)
// BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2151 {
    public static int n;
    public static int min_value;
    public static class Node {
        int x;
        int y;
        int dir;
        int cnt;
        public Node(int x, int y, int dir, int cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    public static int sx, sy;
    // 하, 상, 좌, 우
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for(int i=0;i<n;i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<n;j++){
                if(map[i][j] == '#'){
                    sx = i;
                    sy = j;
                }
            }
        }
        min_value = Integer.MAX_VALUE;
        bfs();
        System.out.println(min_value);
    }

    public static void bfs(){
        int[][][] visited = new int[n][n][4];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx, sy, 0, 0));
        q.add(new Node(sx, sy, 1, 0));
        q.add(new Node(sx, sy, 2, 0));
        q.add(new Node(sx, sy, 3, 0));
        visited[sx][sy][0] = 0;
        visited[sx][sy][1] = 0;
        visited[sx][sy][2] = 0;
        visited[sx][sy][3] = 0;

        while (!q.isEmpty()){
            int q_len = q.size();
            for(int i=0;i<q_len;i++){
                Node node = q.poll();
                int nx = node.x + dx[node.dir];
                int ny = node.y + dy[node.dir];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny][node.dir] < node.cnt || map[nx][ny] == '*') continue;

                if(map[nx][ny] == '.'){
                    visited[nx][ny][node.dir] = node.cnt;
                    q.add(new Node(nx, ny, node.dir, node.cnt));
                } else if(map[nx][ny] == '!'){
                    visited[nx][ny][node.dir] = node.cnt;
                    if(node.dir <= 1){
                        visited[nx][ny][2] = node.cnt;
                        visited[nx][ny][3] = node.cnt;
                        q.add(new Node(nx, ny, node.dir, node.cnt));
                        q.add(new Node(nx, ny, 2, node.cnt+1));
                        q.add(new Node(nx, ny, 3, node.cnt+1));
                    } else {
                        visited[nx][ny][0] = node.cnt;
                        visited[nx][ny][1] = node.cnt;
                        q.add(new Node(nx, ny, node.dir, node.cnt));
                        q.add(new Node(nx, ny, 0, node.cnt+1));
                        q.add(new Node(nx, ny, 1, node.cnt+1));
                    }
                } else if(map[nx][ny] == '#'){
                    min_value = Math.min(min_value, node.cnt);
                }

            }
        }

    }
}
