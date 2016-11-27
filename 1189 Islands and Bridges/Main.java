import java.io.*;

import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}

		new Main().run();
	}

	int MAXN = 13;
	int MAXM = MAXN * (MAXN - 1);
	int MAXK = 1 << MAXN;
	
	BufferedReader in;
	
	int vNum;
	int eNum;
	Graph g = new Graph();
	int[] w = new int [MAXN];
	int[] dp = new int [MAXK * MAXN * MAXN];
	long[] cnt = new long [MAXK * MAXN * MAXN];
	
	int N;
	int M;
	int K;
	
	int fmask;
	long count;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int[] ans = new int [13];
		long[] num = new long [13];
		for (int T = nextInt(); T --> 0;) {
			vNum = nextInt();
			eNum = nextInt();
			g.clear(vNum);
			for (int i = 0; i < vNum; i++)
				w[i] = nextInt();
			for (int i = 0; i < eNum; i++)
				g.addEdge(nextInt() - 1, nextInt() - 1);
			if (vNum == 1) {
				out.println(w[0] + " 1");
				continue;
			}
			N = vNum;
			M = N * N;
			K = 1 << N;
			fill(dp, 0, K * N * N, -1);
			fill(cnt, 0, K * N * N, 0);
			fmask = K - 1;
			int max = -1000;
			for (int v = 0; v < vNum; v++) {
				ans[v] = dfs(1 << v, v, -1);
				if ((num[v] = count) > 0)
					max = Math.max(max, ans[v]);
			}
			if (max == -1000) {
				out.println("0 0");
				continue;
			}
			out.print(max); out.print(' ');
			long n = 0L;
			for (int v = 0; v < vNum; v++)
				if (ans[v] == max)
					n += num[v];
			out.println(n >> 1);
		}
		out.close();
	}
	
	int dfs(int m, int v, int p) {
		int t, ind = m * M + v * N + p;
		if (p != -1 && dp[ind] != -1)
			return dp[ind];
		int ret = w[v];
		long num = m == fmask ? 1L : 0L;
		for (int i = g.head[v]; i != 0; i = g.next[i]) {
			int k = g.vert[i];
			int nmask = m | (1 << k);
			if (nmask != m) {
				int val = w[v] * (1 + w[k] + (p != -1 && g.edge(p, k) ? w[p] * w[k] : 0)) + dfs(nmask, k, v);
				if (cnt[t = nmask * M + k * N + v] > 0)
					if (ret < val) {
						ret = val;
						num = cnt[t];
					} else if (ret == val)
						num += cnt[t];
			}
		}
		if (p == -1)
			count = num;
		else
			cnt[ind] = num;
		return p == -1 ? ret : (dp[ind] = ret);
	}

	class Graph {
		int[] head = new int [MAXN];
		int[] adj = new int [MAXN * MAXN];
		int[] next = new int [MAXM + 1];
		int[] vert = new int [MAXM + 1];
		int cnt = 1;
		int tick = 1;
		
		void clear(int n) {
			fill(head, 0, n, 0);
			cnt = 1;
			tick++;
		}
		
		void addEdge(int u, int v) {
			add(u, v);
			add(v, u);
		}
		
		void add(int u, int v) {
			adj[u * MAXN + v] = tick;
			next[cnt] = head[u];
			vert[cnt] = v;
			head[u] = cnt++;
		}
		
		boolean edge(int u, int v) {
			return adj[u * MAXN + v] == tick;
		}
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
