import java.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int N;
	int M;
	Edge[][] g = new Edge [25][3];
	int[] sz = new int [25];
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (int u = 0; u < 25; u++)
			for (int v = 0; v < 3; v++)
				g[u][v] = new Edge();
		for (;;) {
			N = nextInt();
			M = nextInt();
			if (N == 0)
				break;
			fill(sz, 0, N, 0);
			for (int i = 0; i < M; i++) {
				int u = nextInt();
				int v = nextInt();
				g[u][sz[u]++].set(i, v);
				g[v][sz[v]++].set(i, u);
			}
			int ans = 0;
			for (int v = 0; v < N; v++)
				ans = max(ans, dfs(v, 0));
			out.println(ans);
		}
		out.close();
	}
	
	int dfs(int v, int mask) {
		int res = 0;
		for (int i = 0, s = sz[v], m; i < s; i++)
			if ((m = mask | (1 << g[v][i].id)) != mask)
				res = max(res, 1 + dfs(g[v][i].to, m));
		return res;
	}

	class Edge {
		int id;
		int to;
		
		void set(int id, int to) {
			this.id = id;
			this.to = to;
		}
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
