package BJ;

import java.io.*;
import java.util.*;

public class BJ2151 {
    static class Node{
        int x,y,w,d;

        public Node(int x, int y, int w, int d) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.d = d;
        }
    }
    static int[] dx = {0,1,0,-1},dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        List<Integer[]> door = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if(map[i][j]=='#'){
                    door.add(new Integer[]{i,j});
                }
            }
        }

        boolean[][][] visited = new boolean[N][N][4];


        Queue<Node> q = new LinkedList<>();
        int sx = door.get(0)[0];
        int sy = door.get(0)[1];
        for (int i = 0; i < 4; i++) {
            q.add(new Node(sx,sy,0,i));
            visited[sx][sy][i] = true;
        }

        t:while(!q.isEmpty()){
            Node now = q.poll();
            int d = now.d;
            int x = now.x;
            int y = now.y;
            visited[x][y][d]=true;

            while(true){
                if (x==door.get(1)[0] && y==door.get(1)[1]){
                    System.out.print(now.w);
                    break t;
                }
                x +=dx[d];
                y +=dy[d];


                if(x<0||x>=N||y<0||y>=N||map[x][y]=='*'||visited[x][y][d]) break;
                visited[x][y][d]=true;
                if(map[x][y]=='!'){
                    for (int i = 1; i <=3; i+=2) {
                        q.add(new Node(x,y,now.w+1,(d+i)%4));
                    }
                }
            }
        }
    }
}
