import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class BJ_4195_친구네트워크 {

    static int parents[] = new int[200001];
    static int sizes[] = new int[200001];
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            HashMap<String ,Integer> map = new HashMap<>();
            int valueNum = 1;
            int F = Integer.parseInt(br.readLine());

            init();

            for(int i = 0 ; i < F; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if(!map.containsKey(a)) map.put(a,valueNum++);
                if(!map.containsKey(b)) map.put(b,valueNum++);

                sb.append(union(map.get(a), map.get(b))).append("\n");
            }
            System.out.println(sb.toString());
        }

    }
    static void init(){
        for(int i = 0 ; i <=20000;i++){
            parents[i] = i;
            sizes[i] = 1;
        }
    }
    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static int union(int a, int b){
        a = find(a);
        b = find(b);

        if(a != b){
            if(sizes[a] < sizes[b]) { // 더 큰 집합에 합쳐질 수 있도록
                int tmp = a;
                a = b;
                b = tmp;
            }
            sizes[a] += sizes[b];
            parents[b] = a;
        }
        return sizes[a];
    }
}
