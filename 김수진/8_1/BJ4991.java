package BJ;

import java.io.*;
import java.util.*;

public class BJ4991 {
    static int N,ans,a,b, dx[]={1,-1,0,0},dy[]={0,0,1,-1};
    static boolean visited[];
    static List<Point> dirty;
    static char[][] map;
    static class Point {
        int x,y,w;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a==0 && b==0) break;

            map = new char[b][a];
            dirty = new ArrayList<>();
            Point now = null;

            for (int i = 0; i < b; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < a; j++) {
                    if (map[i][j] == '*') dirty.add(new Point(i,j));
                    else if (map[i][j] == 'o') now = new Point(i,j);
                }
            }

            N=dirty.size();
            visited = new boolean[N];
            ans=Integer.MAX_VALUE;

            permutation(0,0,now);
            sb.append(ans+"\n");
        }
        System.out.print(sb);
    }

    static void permutation(int idx, int sum, Point now){
        if (idx==N) ans = Math.min(ans, sum);
        else if (sum>=ans) return;
        else {
            for (int i = 0; i < N; i++) {
                if (!visited[i]){
                    visited[i]=true;
                    int dist=find(i,now);
                    if (dist==-1) ans=-1;
                    permutation(idx+1,sum+dist, dirty.get(i));
                    visited[i]=false;
                }
            }
        }
    }

    static int find(int findIdx, Point pre){
        boolean[][] visitedM = new boolean[b][a];
        int result=-1;
        //BFS
        visitedM[pre.x][pre.y]=true;

        Queue<Point> q = new LinkedList<>();
        q.add(pre);

        while(!q.isEmpty()){
            Point now = q.poll();

            if (now.x==dirty.get(findIdx).x&&now.y==dirty.get(findIdx).y) {
                result = now.w;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];

                if (nx<0||nx>=b||ny<0||ny>=a||visitedM[nx][ny]||map[nx][ny]=='x') continue;
                visitedM[nx][ny]=true;
                q.add(new Point(nx,ny,now.w+1));
            }
        }
        return result;
    }
}
