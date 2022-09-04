// BOJ - 벽 부수고 이동하기4(16946번)
// BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16946 {
    public static int n, m;
    public static int[][] map;
    public static int[][] cnt;
    public static int[][] ans;
    public static int[][] group;

    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int g_cnt;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        ans = new int[n][m];
        cnt = new int[n][m];
        group = new int[n][m];

        for(int i=0;i<n;i++){
            char[] c = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                map[i][j] = c[j] - '0';
            }
        }
        int bfs_cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 0 && group[i][j] == 0){
                    g_cnt++;
                    bfs_cnt++;
                    bfs(i, j);
                }
            }
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

                if(map[i][j] == 1){
                    boolean[] check = new boolean[g_cnt+1];
                    int sum = 0;
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        if(map[nx][ny] == 0) {
                            int g = group[nx][ny];
                            if(check[g]){
                                continue;
                            } else {
                                sum += ans[nx][ny];
                                check[g] = true;
                            }
                        }
                    }
                    sb.append((sum+1)%10);
                }else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }


        System.out.println(sb.toString());


    }

    public static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        int c = 1;

        q.add(new Node(x, y));
        q2.add(new Node(x, y));
        group[x][y] = g_cnt;
        while (!q.isEmpty()){
            Node node = q.poll();
            for(int d=0;d<4;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(group[nx][ny] != 0) continue;
                if(map[nx][ny] != 0 ) continue;
                group[nx][ny] = g_cnt;
                c++;
                q.add(new Node(nx, ny));
                q2.add(new Node(nx, ny));
            }
        }

        while (!q2.isEmpty()){
            Node node = q2.poll();
            ans[node.x][node.y] = c;
        }

    }


}
