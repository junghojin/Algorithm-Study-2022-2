// BOJ - 로봇 청소기(4991번)
// DFS + BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_4991_2 {
    public static class Node {
        int x;
        int y;
        int dis;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        
    }
    public static int n, m, min_dis;
    public static ArrayList<Node> dirty;
    public static int s_x, s_y;
    public static char[][] map;
    public static boolean[] check;
    public static int[][][][] bfs_distance;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;
            bfs_distance = new int[n][m][n][m];
            map = new char[n][m];
            dirty = new ArrayList<>();
            for(int i=0;i<n;i++){
                map[i] = br.readLine().toCharArray();
                for(int j=0;j<m;j++){
                    if(map[i][j] == '*'){
                        dirty.add(new Node(i, j));
                    } else if(map[i][j] == 'o'){
                        s_x = i;
                        s_y = j;
                    }
                }
            }
            check = new boolean[dirty.size()];
            min_dis = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    bfs(i, j);
                }
            }
            dfs(0, new int[dirty.size()]);
            if(min_dis == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(min_dis);

        }


    }

    public static void dfs(int depth, int[] permu){
        if(depth == dirty.size()){
            int dis = 0;
            int r_x = s_x;
            int r_y = s_y;

            for(int i=0;i<permu.length;i++){
                Node d = dirty.get(permu[i]);
                int distance = bfs_distance[r_x][r_y][d.x][d.y];
                if(distance == 0) return;
                dis += distance;
                r_x = d.x;
                r_y = d.y;
            }

            min_dis = Math.min(min_dis, dis);
            return;
        }

        for(int i=0;i<dirty.size();i++){
            if(check[i]) continue;
            check[i] = true;
            permu[depth] = i;
            dfs(depth+1, permu);
            check[i] = false;
        }
    }

    public static void bfs(int x, int y){

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        bfs_distance[x][y][x][y] = 0;
        while (!q.isEmpty()){
            Node node = q.poll();
            for(int i=0;i<4;i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(nx < 0 || ny < 0 | nx >= n || ny >= m) continue;
                if(map[nx][ny] == 'x') continue;
                if(bfs_distance[x][y][nx][ny] != 0) continue;
                q.add(new Node(nx, ny));
                bfs_distance[x][y][nx][ny] = bfs_distance[x][y][node.x][node.y]+1;
            }
        }
    }
}
