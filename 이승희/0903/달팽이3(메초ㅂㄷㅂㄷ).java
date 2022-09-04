// BOJ - 달팽이3(1959번)
// 메모리 초과 ㅂㄷㅂㄷ 해결중 ㅎㅎ..
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1959 {
    public static int n, m;
    public static boolean[][] map;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        map = new boolean[n][m];
        int nx = 0;
        int ny = 0;
        int dir = 0;
        int cnt = 0;
        map[nx][ny] = true;

        long[] ans = new long[2];
        int check_cnt = 0;
        while (true){
            if(check_cnt >= 4){
                System.out.println(cnt-4);
                System.out.println((nx+1)+" "+(ny+1));
                break;
            }
            nx += dx[dir];
            ny += dy[dir];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                nx -= dx[dir];
                ny -= dy[dir];
                cnt++;
                dir = (dir+1)%4;
                continue;
            } else if(map[nx][ny]){
                check_cnt++;
                cnt++;
                nx -= dx[dir];
                ny -= dy[dir];
                dir = (dir+1)%4;
                continue;
            }
            if(!map[nx][ny] && check_cnt >= 1){
                check_cnt = 0;
            }
            map[nx][ny] = true;

        }
    }
}
