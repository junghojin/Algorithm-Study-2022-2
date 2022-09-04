import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_16946_벽부수고이동4 {

    static int N,M,cnt, group=1;
    static int[][] map;
    static boolean[][] visit;
    static int[][] cntMap;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        cntMap = new int[N][M];

        for(int n = 0 ; n <  N; n++){
            String input = br.readLine();
            for(int m = 0 ; m <M; m++){
                map[n][m] = input.charAt(m)-'0';
            }
        }

        int[] groupCnt = new int[N*M+1];
        visit= new boolean[N][M];
        for(int n = 0 ; n <  N; n++){
            for(int m = 0 ; m <M; m++){
                if(map[n][m]==0  && !visit[n][m]){
                    cnt = 0;
                    dfs(n,m);
                    groupCnt[group++] = cnt%10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int n = 0 ; n <  N; n++){
            for(int m = 0 ; m <M; m++){
                HashSet<Integer> groupSet = new HashSet<>();
                int sum = 1;
                if(map[n][m] == 1){
                    for(int d = 0; d<4;d++){
                        int mx = n + dx[d];
                        int my = m + dy[d];
                        if(!isAvail(mx,my)) continue;
                        groupSet.add(cntMap[mx][my]);
                    }
                    for(int g : groupSet){
                        sum += groupCnt[g];
                    }
                    sb.append(sum%10);
                }
                else {
                    sb.append(0);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());

    }

    static void dfs(int x, int y){

        if(map[x][y] != 0){
            return;
        }

        cnt++;
        visit[x][y] = true;
        cntMap[x][y] = group;

        for(int d = 0; d<4; d++){
            int mx = x + dx[d];
            int my = y + dy[d];

            if(!isAvail(mx,my) || visit[mx][my]) continue;
            dfs(mx,my);
        }
    }

    static boolean isAvail(int x, int y){
        if( x < 0 || x >=N || y<0 || y>= M) return false;
        return true;
    }

}
