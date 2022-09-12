// BOJ - 스타트택스 (19238번)
// BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int n, m, oil, people;
	public static int[][] map;
	public static int[][] cus_map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static boolean[] check;
	public static int t_x, t_y;
	public static class Node implements Comparable<Node>{
		int x;
		int y;
		int dis;
		public Node(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		@Override
		public int compareTo(Node o) {
			if(this.dis == o.dis) {
				if(this.x == o.x) {
					return this.y - o.y;
				} 
				return this.x - o.x;
			}
			return this.dis - o.dis;
		}
	}
	public static int[][] cus;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		oil = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		cus = new int[m+1][4];
		cus_map = new int[n][n];
		check = new boolean[m+1];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(br.readLine(), " ");
		t_x = Integer.parseInt(st.nextToken())-1;
		t_y = Integer.parseInt(st.nextToken())-1;
		
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<4;j++) {
				int num = Integer.parseInt(st.nextToken())-1;
				cus[i][j] = num;
			}
			cus_map[cus[i][0]][cus[i][1]] = i;
		}
		
		people = m;
		boolean end = false;
		while (true) {
			if(people == 0) {
				break;
			}
			
			// 손님 고르기
			Node node = bfs1();
			if(node == null) {
				end = true;
				break;
			}
			int cus_num = cus_map[node.x][node.y];
			// 손님 데려다 주기
			
			int cal1 = node.dis;
			int cal2 = bfs2(node.x, node.y, cus[cus_num][2], cus[cus_num][3]);
	
			
			if(oil < cal1+cal2 || cal2 == -1) {
				end = true;
				break;
			}
			
			oil -= (cal1+cal2);
			oil += (cal2 * 2);
			check[cus_num] = true;
			cus_map[node.x][node.y] = 0;
			t_x = cus[cus_num][2];
			t_y = cus[cus_num][3];
			people--;
				
		}
		if(end) System.out.println(-1);
		else System.out.println(oil);
	}
	
	public static Node bfs1() {
		boolean[][] visited = new boolean[n][n];
		Queue<Node> q = new LinkedList<>();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited[t_x][t_y] = true;
		q.offer(new Node(t_x, t_y, 0));
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(cus_map[node.x][node.y] >= 1 && !check[cus_map[node.x][node.y]]) {
				pq.add(new Node(node.x, node.y, node.dis));
			}
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
					if(!visited[nx][ny] && map[nx][ny] != 1) {
						visited[nx][ny] = true;
						q.add(new Node(nx, ny, node.dis+1));
					}
				}
			}
		}
		if(pq.isEmpty()) return null;
		return pq.poll();
		
	}
	
	public static int bfs2(int s_x, int s_y, int e_x, int e_y) {
		boolean[][] visited = new boolean[n][n];
		Queue<Node> q = new LinkedList<>();

		visited[s_x][s_y] = true;
		q.offer(new Node(s_x, s_y, 0));
		int dis = -1;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.x == e_x && node.y == e_y) {
				return node.dis;
			}
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
					if(!visited[nx][ny] && map[nx][ny] != 1) {
						visited[nx][ny] = true;
						q.add(new Node(nx, ny, node.dis+1));
					}
				}
			}
		}
		return dis;
	}

}
