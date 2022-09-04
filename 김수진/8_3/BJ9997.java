import java.util.Scanner;

public class BJ9997 {
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long [] bits = new long[N];



        for (int i = 0; i < N; i++) {
            char[] w = sc.next().toCharArray();
            long bit = 0;

            for(char c:w){
                bit = bit|(1<<(c-'a'));
            }
            bits[i]=bit;
        }
        DFS(0,0,bits,N);
        System.out.print(ans);
    }

    static void DFS(int idx, long bit, long[] bits, int N){
        if (idx==N){
            if (bit==(1<<26)-1) ans++;
        } else{
            DFS(idx+1,bit,bits,N);
            DFS(idx+1,bit|bits[idx],bits,N);
        }
    }
}
