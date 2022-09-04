import java.io.*;
import java.util.*;

public class BJ7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        
        long[][] arr = new long[4][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Long.parseLong(st.nextToken());
            }
        }

        HashMap<Long, Long> map = new HashMap();

        long ans=0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                Long s = arr[0][j]+arr[1][k];
                if (map.containsKey(s)) map.put(s,map.get(s) + 1);
                else map.put(s,1L);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Long s = arr[2][i]+arr[3][j];
                if (map.containsKey(-s)){
                    ans+=map.get(-s);
                }
            }
        }

        System.out.print(ans);
    }
}
