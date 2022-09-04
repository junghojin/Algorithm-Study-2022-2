// BOJ - 합이 0인 네정수(7453번)
// 투포인터
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7453 {
    public static int n;
    public static long ans;
    public static int[] A, B, C, D;
    public static int[] AB, CD;
    // 투포인터를 위한 포인터
    public static int ABid, CDid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());

        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];
        AB = new int[n*n];
        CD = new int[n*n];
        ans = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        ABid = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                AB[ABid] = A[i] + B[j];
                CD[ABid] = C[i] + D[j];
                ABid++;
            }
        }
        CDid = ABid-1;

        Arrays.sort(AB);
        Arrays.sort(CD);
        int i=0;
        // 투포인터
        while(i < ABid && CDid >= 0){
            int ABvalue = AB[i];
            int CDvalue = CD[CDid];
            int ABcnt = 0;
            int CDcnt = 0;
            int ABCD = ABvalue + CDvalue;

            if(ABCD == 0){
                while (i <ABid && ABvalue == AB[i]){
                    i++;
                    ABcnt++;
                }

                while (CDid >= 0 && CDvalue == CD[CDid]){
                    CDid--;
                    CDcnt++;
                }

                ans += (long)ABcnt * (long)CDcnt;
            } else if(ABCD > 0){
                CDid--;
            } else {
                i++;
            }
        }

        System.out.println(ans);

    }
}
