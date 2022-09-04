import java.io.*;
import java.util.*;

public class BJ4195 {
    static HashMap<String, String> parent;
    static HashMap<String, Integer> cnt;

    static String findParent(String child){
        if (parent.get(child)==child) return child;
        else {
            parent.put(child, findParent(parent.get(child)));
            return parent.get(child);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int F = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            cnt = new HashMap<>();

            for (int j = 0; j < F; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!cnt.containsKey(f1)) {
                    cnt.put(f1,1);
                    parent.put(f1,f1);
                }
                if (!cnt.containsKey(f2)) {
                    cnt.put(f2, 1);
                    parent.put(f2,f2);
                }

                int cntS = cnt.get(findParent(f1));
                if(findParent(f1)!=findParent(f2)) cntS += cnt.get(findParent(f2));
                parent.put(findParent(f1), findParent(f2));
                cnt.put(findParent(f1),cntS);

                sb.append(cnt.get(findParent(f2))+"\n");
            }
        }
        System.out.print(sb);
    }
}
