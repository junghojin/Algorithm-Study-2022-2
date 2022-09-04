import java.util.*;

public class BJ1959 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long M = sc.nextLong();
        long N = sc.nextLong();

        long a,b,cnt;

        if (M>N){
            cnt=4*(N/2);
            if (N%2==1){
                a=M-N/2;
                if(M!=N)cnt+=1;
            } else {
                a=N/2+2;
                if(M!=N)cnt+=3;
            }
            b=N/2+N%2;
        } else {
            cnt=4*(M/2);
            if (M%2==1){
                b=N-M/2;
                if(M!=N)cnt+=1;
            } else {
                b=M/2+2;
                if(M!=N)cnt+=3;
            }
            a=M/2+M%2;
        }
        System.out.print(cnt+"\n");
        System.out.print(a+" "+b);
    }
}
