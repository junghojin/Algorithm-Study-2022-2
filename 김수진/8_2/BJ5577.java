import java.io.*;

public class BJ5577 {
    static int s,e,arr[];
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ans=N;

        for (int i = 0; i < N; i++) arr[i]=Integer.parseInt(br.readLine());;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= 3; j++) {
                s=e=i;
                int a = arr[i];

                if (arr[i]!=j){
                    arr[i] = j;
                    DFS();
                    arr[i] = a;
                }
            }
        }
        System.out.print(ans);
    }

    static void DFS(){
        int length = arr.length-(e-s)+1;
        ans = Math.min(ans, length);

        if(s>=0&&pop(s)) DFS();
    }

    static Boolean pop(int idx){
        int l = e==s?0:e-s-1;
        while(s>=0&&arr[s]==arr[idx]) s--;
        while(e<arr.length&&arr[e]==arr[idx]) e++;

        if (e-s-l>4) return true;
        return false;
    }
}
