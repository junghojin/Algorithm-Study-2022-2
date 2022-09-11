import java.util.*;

public class BJ1644 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        List<Integer> list = new ArrayList<>();

        int[] num = new int[N+1];
        for (int i = 2; i <= N; i++) {
            if (num[i]==-1) continue;
            list.add(i);
            num[i] = i;
            for (int j = 2*i; j <= N; j+=i) {
                num[j] = -1;
            }
        }
        int ans = 0;

        t: for (int i = 0; i < list.size(); i++) {
            int sum=0;
            for (int j = i; j < list.size(); j++) {
                sum+=list.get(j);
                if (sum==N) ans++;
                else if (sum>N) continue t;
            }
        }

        System.out.print(ans);
    }
}
