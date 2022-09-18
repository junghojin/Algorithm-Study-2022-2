import java.util.*;

class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static class Robot {
        int x,y,d,w;

        public Robot(int x, int y, int d, int w) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.w = w;
        }
    }
    public int solution(int[][] map) {
        int answer = 0;

        int N = map.length;

        boolean[][][] visited = new boolean[N][N][4];

        visited[0][0][0] = true;
        visited[0][1][2] = true;

        Queue<Robot> q = new LinkedList<>();

        q.add(new Robot(0,0,0,0));

        while(!q.isEmpty()){
            Robot now = q.poll();

            int x1 = now.x;
            int y1 = now.y;
            int x2 = now.x+dx[now.d];
            int y2 = now.y+dy[now.d];

            if ((x1==N-1&&y1==N-1)||(x2==N-1&&y2==N-1)){
                answer=now.w;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int add = (now.d + i)%4;
                int nx1 = x1 + dx[add];
                int ny1 = y1 + dy[add];
                int nx2 = x2 + dx[add];
                int ny2 = y2 + dy[add];

                if (nx1<0||nx1>=N||ny1<0||ny1>=N||map[nx1][ny1]==1) continue;
                if (nx2<0||nx2>=N||ny2<0||ny2>=N||map[nx2][ny2]==1) continue;

                if (i%2!=0){
                    if (!visited[x1][y1][add]) {
                        visited[x1][y1][add] = true;
                        visited[nx1][ny1][(add+2)%4] = true;
                        q.add(new Robot(x1,y1,add,now.w+1));
                    }
                    if (!visited[x2][y2][add]) {
                        visited[x2][y2][add] = true;
                        visited[nx2][ny2][(add+2)%4] = true;
                        q.add(new Robot(x2,y2,add,now.w+1));
                    }
                }
                if (!visited[nx1][ny1][now.d]){
                    visited[nx1][ny1][now.d] = true;
                    visited[nx2][ny2][(now.d+2)%4] = true;
                    q.add(new Robot(nx1,ny1,now.d,now.w+1));

                }
            }
        }

        return answer;
    }
}
