import java.io.*;
import java.util.*;

import static java.util.Arrays.fill;
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
	int MAXV = 200;
	int MAXE = 1000;
	
	int N;
	int M;
	MultiList g = new MultiList();
	Set<Integer> set = new HashSet<Integer>();
	
	int wsz = 0;
	int[] weights = new int [MAXE];
	
	int S;
	int T;
	int min;
	int max;
	
	int[] used = new int [MAXV];
	int tick = 1;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (;;) {
			N = nextInt();
			M = nextInt();
			
			if (N == 0)
				break;
			
			g.prepare(N);
			set.clear();
			
			for (int i = 0; i < M; i++) {
				int u = nextInt() - 1;
				int v = nextInt() - 1;
				int w = nextInt();
				g.add(u, v, w);
				g.add(v, u, w);
				set.add(w);
			}
			
			S = nextInt() - 1;
			T = nextInt() - 1;
			
			wsz = 0;
			for (int w : set)
				weights[wsz++] = w;
			sort(weights, 0, wsz);
			int ans = INF;
			
			for (int k = 0; k < wsz; k++)
				for (int i = 0; i + k < wsz; i++) {
					min = weights[i];
					max = weights[i + k];
					tick++;
					if (max - min < ans && dfs(S))
						ans = max - min;
				}
			
			out.println(ans == INF ? -1 : ans);
		}
		
		out.close();
	}
	
	boolean dfs(int v) {
		used[v] = tick;
		if (v == T)
			return true;
		for (int i = g.head[v]; i != 0; i = g.next[i]) {
			int x = g.vert[i];
			int w = g.dist[i];
			if (used[x] != tick && min <= w && w <= max && dfs(x))
				return true;
		}
		return false;
	}

	class MultiList {
		int[] head = new int [MAXV];
		int[] next = new int [1 + 2 * MAXE];
		int[] vert = new int [1 + 2 * MAXE];
		int[] dist = new int [1 + 2 * MAXE];
		int cnt = 1;
		
		void prepare(int vNum) {
			fill(head, 0, vNum, 0);
			cnt = 1;
		}
		
		void add(int u, int v, int w) {
			next[cnt] = head[u];
			vert[cnt] = v;
			dist[cnt] = w;
			head[u] = cnt++;
		}
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
