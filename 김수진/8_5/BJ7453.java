import java.io.*;
import java.util.*;

public class BJ7453 {
    static long[] list,list2;
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

        list = new long[n*n];
        list2 = new long[n*n];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                list[j*n+k] = arr[0][j]+arr[1][k];
                list2[j*n+k] = arr[2][j]+arr[3][k];
            }
        }
        long ans=0;
        Arrays.sort(list);
        Arrays.sort(list2);

        int s1=0,s2=n*n-1;

        while(s1<n*n && 0<=s2){
            long sum = list[s1]+list2[s2];
            long cnt1=1, cnt2=1;

            if (sum==0){
                while (s1<n*n-1 && list[s1]==list[s1+1]){
                    cnt1++;
                    s1++;
                }
                while (s2>0 && list2[s2]==list2[s2-1]){
                    cnt2++;
                    s2--;
                }
                ans+=cnt1*cnt2;
            }
            if (sum<0) s1++;
            else s2--;

        }

        System.out.print(ans);
    }
}