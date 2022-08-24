// BOJ - 친구 네트워크(4195번)
// Union Find
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static int f, n;
    public static int INF = (int)1e9;
    public static int[] parent;
    public static HashMap<String, Integer> number;
    public static HashMap<Integer, Integer> cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        f = Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<=f;test_case++){
            n = Integer.parseInt(br.readLine());
            number = new HashMap<>();
            cnt = new HashMap<>();
            parent = new int[200001];
            for(int i=0;i<200001;i++){
                parent[i] = i;
            }

            int num = 0;
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();
                if(!number.containsKey(a)){
                    number.put(a, ++num);
                    cnt.put(num, 1);
                }

                if(!number.containsKey(b)){
                    number.put(b, ++num);
                    cnt.put(num, 1);
                }

                int p_a = find_parent(number.get(a));
                int p_b = find_parent(number.get(b));
                if(p_a != p_b){
                    sb.append(cnt.get(p_a)+cnt.get(p_b)+"\n");
                    union_find(p_a, p_b);
                } else {
                    sb.append(cnt.get(p_a)+"\n");
                }

            }
        }
        System.out.println(sb.toString());
    }

        public static int find_parent(int i) {
        if(parent[i] == i) return i;
        return parent[i] = find_parent(parent[i]);
    }

    public static void union_find(int a, int b) {
        cnt.put(a, cnt.get(a)+cnt.get(b));
        parent[b] = a;
    }

}
