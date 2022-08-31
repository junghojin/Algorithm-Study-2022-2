import java.util.*;
import java.io.*;

// 22. 08. 31. 수 - 백준 15652 - 친구비 - Union & Find, HashMap 

public class Main_16562 {

    static final int INF = 98765432;
    static int costs[], parents[], minCosts[];
    static Map<Integer, Integer> parentsCost; // 부모 별 비용

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        costs = new int[N];
        parents = new int[N];
        minCosts = new int[N];
        boolean[] isConnected = new boolean[N];
        parentsCost = new HashMap<Integer, Integer>();

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(stk.nextToken());
            parents[i] = i;
            minCosts[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken()) - 1;
            int b = Integer.parseInt(stk.nextToken()) - 1;
            isConnected[a] = true;
            isConnected[b] = true;
            union(a, b);
        }
        // ===================== input end =========================

        // 최상위 부모 노드로 업데이트 하기 / 가격 업데이트 하기
        for(int i = 0; i < N; i++) {
            // 최상위 부모노드로 업데이트
            int tempParent = parents[i];
            parents[i] = findParent(i);

            if(!parentsCost.containsKey(parents[i])) {
                parentsCost.put(parents[i], costs[parents[i]]);
            }
            parentsCost.put(parents[i], Math.min(costs[i], parentsCost.get(parents[i])));
        }

        // 최소 비용 구하기
        int total = 0;
        Iterator<Integer> values = parentsCost.values().iterator();
        while(values.hasNext()) {
            total += values.next();
        }

        // 출력
        if (total <= k) System.out.println(total);
        else System.out.println("Oh no");
    }

    private static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static int findParent(int node) {
        if (parents[node] == node) return node;
        else return parents[node] = findParent(parents[node]);
    }
}
