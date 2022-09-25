import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N];
        int[] preCnt = new int[N];
        List<Integer>[] pre = new List[N];

        Queue<Integer> queue = new LinkedList<>();


        for (int i = 0; i < N; i++) {
            pre[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            String building;

            while (!(building=st.nextToken()).equals("-1")){
                pre[Integer.parseInt(building)-1].add(i);
                preCnt[i]++;
            }

            if (preCnt[i]==0) queue.add(i);
        }

        int[] ans = new int[N];

        while(!queue.isEmpty()){
            int now = queue.poll();
            ans[now]+=time[now];
            for(int next:pre[now]){
                preCnt[next]--;
                ans[next]=Math.max(ans[next],ans[now]);
                if (preCnt[next]==0) queue.add(next);
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(ans[i]+"\n");
        }


    }
}
