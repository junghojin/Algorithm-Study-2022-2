import java.util.*;

public class BJ1701 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int l = s.length();
        int[][] arr = new int[l+1][l+1];
        int ans=0;

        for (int i = 1; i <= l; i++) {
            for (int j = i+1; j <= l; j++) {
                if (s.charAt(i-1)==s.charAt(j-1)) {
                    arr[i][j]=arr[i-1][j-1]+1;
                    ans = Math.max(ans, arr[i][j]);
                }
            }
        }
        System.out.print(ans);
    }
}
