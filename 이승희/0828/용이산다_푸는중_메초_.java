import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_3430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=TC;test_case++){
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] arr = new int[m];
            ArrayList<Integer> ans = new ArrayList<>();
            Queue[] rain_arr = new Queue[n+1];
            boolean[] empty = new boolean[n+1];

            for(int i=0;i<n+1;i++){
                rain_arr[i] = new LinkedList();
            }

            boolean check = false;

            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0;i<m;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=m-1;i>=0;i--){
                if(arr[i] > 0){
                    rain_arr[arr[i]].add(i);
                }
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int i=1;i<n+1;i++){
                if(rain_arr[i].size() > 0){
                    pq.add((Integer)rain_arr[i].poll());
                }
            }

            for(int i=0;i<m;i++){
                if(arr[i] > 0){
                    if(empty[arr[i]]){
                        check = true;
                        break;
                    }

                    empty[arr[i]] = true;
                    if(rain_arr[arr[i]].size() > 0){
                        pq.add((Integer)rain_arr[arr[i]].poll());
                    }

                } else {
                    if(pq.isEmpty()){
                        ans.add(0);
                    } else {
                        int now = pq.poll();
                        ans.add(arr[now]);
                        empty[arr[now]] = false;
                    }
                }
            }

            if(check || !pq.isEmpty()){
                System.out.println("NO");
            } else {
                System.out.println("YES");
                for(int a:ans){
                    System.out.print(a+" ");
                }
                System.out.println();
            }


        }

    }
}
