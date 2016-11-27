import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int INF = Integer.MAX_VALUE >> 1;
	
	int MAXSZ = 201;
	int MAXV = MAXSZ * MAXSZ;
	int MAXE = 4 * MAXV;
	
	char[][] vmap = new char [MAXSZ][MAXSZ - 1];
	char[][] hmap = new char [MAXSZ - 1][MAXSZ];
	MultiList graph = new MultiList();
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (;;) {
			int wNum = nextInt();
			int dNum = nextInt();
			if (wNum == -1 && dNum == -1)
				break;
			for (char[] arr : vmap)
				fill(arr, ' ');
			for (char[] arr : hmap)
				fill(arr, ' ');
			for (int i = 0; i < wNum; i++) {
				int x = nextInt();
				int y = nextInt();
				int d = nextInt();
				int t = nextInt();
				if (d == 0) {
					for (int dt = 0; dt < t; dt++)
						hmap[y - 1][x + dt] = 'w';
				} else {
					for (int dt = 0; dt < t; dt++)
						vmap[y + dt][x - 1] = 'w';
				}
			}
			for (int i = 0; i < dNum; i++) {
				int x = nextInt();
				int y = nextInt();
				int d = nextInt();
				if (d == 0) {
					hmap[y - 1][x] = 'd';
				} else {
					vmap[y][x - 1] = 'd';
				}
			}
			graph.clear();
			for (int y = 0; y < MAXSZ; y++) {
				for (int x = 0; x < MAXSZ; x++) {
					if (x > 0 && vmap[y][x - 1] != 'w')
						graph.add(v(y, x), v(y, x - 1), vmap[y][x - 1] == ' ' ? 0 : 1);
					if (x + 1 < MAXSZ && vmap[y][x] != 'w')
						graph.add(v(y, x), v(y, x + 1), vmap[y][x] == ' ' ? 0 : 1);
					if (y > 0 && hmap[y - 1][x] != 'w')
						graph.add(v(y, x), v(y - 1, x), hmap[y - 1][x] == ' ' ? 0 : 1);
					if (y + 1 < MAXSZ && hmap[y][x] != 'w')
						graph.add(v(y, x), v(y + 1, x), hmap[y][x] == ' ' ? 0 : 1);
				}
			}
			
			int dx = (int) floor(nextDouble());
			int dy = (int) floor(nextDouble());
			int ans = dijkstraRMQ(v(0, 0), v(dy, dx));
			
			out.println(ans == INF ? -1 : ans);
		}
		
		out.close();
	}
	
	RMQ rmq = new RMQ();
	int[] dist = new int [MAXV];
	int[] used = new int [MAXV];
	int tick = 1;
	
	int dijkstraRMQ(int from, int to) {
		rmq.clear();
		fill(dist, INF);
		tick++;
		
		rmq.set(from, dist[from] = 0);
		
		for (;;) {
			int v = rmq.get();
			if (v == -1)
				return INF;
			if (v == to)
				return dist[v];
			used[v] = tick;
			rmq.set(v, INF);
			for (int i = graph.head[v]; i != 0; i = graph.next[i]) {
				int nv = graph.vert[i];
				int nc = graph.cost[i];
				if (used[nv] != tick && dist[nv] > dist[v] + nc) {
					rmq.set(nv, dist[nv] = dist[v] + nc);
				}
			}
		}
	}

	int v(int y, int x) {
		return y * MAXSZ + x;
	}
	
	class MultiList {
		int[] head = new int [MAXV];
		int[] next = new int [2 * MAXE + 1];
		int[] vert = new int [2 * MAXE + 1];
		int[] cost = new int [2 * MAXE + 1];
		int cnt = 1;

		void clear() {
			fill(head, 0);
			cnt = 1;
		}
		
		void add(int u, int v, int w) {
//			if (true) {
//				print(u);
//				out.print(' ');
//				print(v);
//				out.println(" " + w);
//			}
			
			next[cnt] = head[u];
			vert[cnt] = v;
			cost[cnt] = w;
			head[u] = cnt++;
		}
	}
	
	class RMQ {
		int n;
		int[] val = new int [2 * MAXV];
		int[] ind = new int [2 * MAXV];

		void clear() {
			n = MAXV;
			fill(val, INF);
			fill(ind, 0, MAXV, 0);
			for (int i = 0; i < MAXV; i++)
				ind[n + i] = i;
		}
		
		void set(int index, int newVal) {
			val[n + index] = newVal;
			for (int v = (n + index) >> 1; v > 0; v >>= 1) {
				if (val[2 * v] < val[2 * v + 1]) {
					val[v] = val[2 * v];
					ind[v] = ind[2 * v];
				} else {
					val[v] = val[2 * v + 1];
					ind[v] = ind[2 * v + 1];
				}
			}
		}
		
		int get() {
			return val[1] == INF ? -1 : ind[1];
		}
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	void print(int v) {
		int x = v % MAXSZ;
		int y = v / MAXSZ;
		out.print("(" + x + " " + y + ")");
	}

	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
	
	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
	
	boolean EOF() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = in.readLine();
			if (s == null)
				return true;
			st = new StringTokenizer(s);
		}
		return false;
	}
}
