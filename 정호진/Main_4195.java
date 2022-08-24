import java.io.*;
import java.util.*;

// 22.08.25.
// 백준 4195 - 친구 네트워크 - Union & Find 

public class Main_4195 {

    private static int size[], parents[]; // size: 속한 네트워크 규모, parents: 부모


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder(); //
        // 테스트 케이스
        for(int t = 0; t < T; t++) {
            Map<String, Integer> people = new HashMap<>(); // 사람이름에 따른 번호를 부여하기 위함
            int number = 0; // 사람 이름에 따른 번호를 부여하기 위함
            int node1 = 0;
            int node2 = 0;

            // input + 네트워크 규모 찾기
            int N = Integer.parseInt(br.readLine()); // 친구 관계 수

            // 초기화
            size = new int[N*2]; // 관계 수 * 2 = 사람 수
            parents = new int[N*2];
            Arrays.fill(size, 1); // 해당 번호의 노드가 속한 규모는 자기자신 뿐이다.

            // 처음 노드의 부모는 노드 자신이다.
            for(int i = 0; i < N * 2; i++) {
                parents[i] = i;
            }

            StringTokenizer stk = null;
            for(int n = 0; n < N; n++) {
                stk = new StringTokenizer(br.readLine(), " ");
                String person1 = stk.nextToken();
                String person2 = stk.nextToken();

                if (!people.containsKey(person1)) {
                    people.put(person1, number++);
                }
                node1 = people.get(person1);

                if (!people.containsKey(person2)) {
                    people.put(person2, number++);
                }
                node2 = people.get(person2);

                union(node1, node2); // merge
                // 두 노드는 이미 합쳐진 상태이기 때문에, 두 노드의 부모노드는 같다.
                // 두 노드 중 한 노드의 부모노드 네트워크 규모를 출력하면 된다.
                result.append(size[findParent(node1)] + "\n");
            }
        }
        // result 출력
        System.out.println(result.toString());
    }

    /** 한 집합(그룹)으로 만들기 */
    private static void union(int node1, int node2) {
        int parentNode1 = findParent(node1);
        int parentNode2 = findParent(node2);

        // parentNode가 같다는 것은 이미 한 그룹내에 속한다는 것
        // 한 그룹 내에 속할 때, 네트워크 규모는 이미 정해진 상태이므로 
        // 규모를 0이나 중복하여 더하여 세팅할 필요가 없다. 
        
        // 더 작은 값을 가진 노드로 합친다.
        if(parentNode1 < parentNode2) {
            parents[parentNode2] = parentNode1;
            // 네트워크 규모를 업데이트 한다.
            size[parentNode1] += size[parentNode2];
            size[parentNode2] = 0;
        } else if(parentNode1 > parentNode2) {
            parents[parentNode1] = parentNode2;
            // 네트워크 규모를 업데이트 한다.
            size[parentNode2] += size[parentNode1];
            size[parentNode1] = 0;
        }
    }

    /** 부모 찾기 */
    private static int findParent(int node) {
        if(node == parents[node])
            return node;
        else
            return parents[node] = findParent(parents[node]);
    }
}
