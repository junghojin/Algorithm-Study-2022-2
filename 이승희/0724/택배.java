// BOJ - 택배(1724번)
// 플로이드 워셜
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1719 {
    public static int n, m;
    public static int[][] distance, path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distance = new int[n+1][n+1];
        path = new int[n+1][n+1];


        for(int i=1;i<=n;i++){
            Arrays.fill(distance[i], 10000*1000-1);
            distance[i][i] = 0;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            path[a][b] = b;
            path[b][a] = a;
            distance[a][b] = Math.min(distance[a][b], c);
            distance[b][a] = Math.min(distance[b][a], c);
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(k == i) continue;
                    if(distance[i][j] > distance[i][k]+distance[k][j]){
                        distance[i][j] = distance[i][k]+distance[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j) {
                    System.out.print("-" + " ");
                    continue;
                }
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }

    }
}
