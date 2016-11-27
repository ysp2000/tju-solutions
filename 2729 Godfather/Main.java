import java.io.*;
import java.util.*;

public class Main {
	int INF = Integer.MAX_VALUE >> 1;
	
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new Main().run();
				} catch (IOException e) {}
			}
		}, "1", 1L << 25).start();
	}

	int N;
	
	int[] head;
	int[] next;
	int[] vert;
	int cnt = 1;
	
	int best = INF;
	int asz = 0;
	int[] ans;
	
	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		N = nextInt();
		head = new int [N];
		next = new int [2 * N + 1];
		vert = new int [2 * N + 1];
		ans = new int [N];
		
		for (int i = 1; i < N; i++) {
			int u = nextInt() - 1;
			int v = nextInt() - 1;
			add(u, v);
			add(v, u);
		}
		
		dfs(0, -1);
		
		Arrays.sort(ans, 0, asz);
		out.print(ans[0]);
		for (int i = 1; i < asz; i++)
			out.print(" " + ans[i]);
		out.println();
		out.close();
	}
	
	int dfs(int v, int p) {
		int sum = 1;
		int max = 0;
		
		for (int i = head[v]; i != 0; i = next[i]) {
			int x = vert[i];
			if (x == p)
				continue;
			int cur = dfs(x, v);
			sum += cur;
			max = Math.max(max, cur);
		}
		
		max = Math.max(max, N - sum);
		
		if (best > max) {
			best = max;
			asz = 0;
		}
		
		if (best == max)
			ans[asz++] = v + 1;
		
		return sum;
	}

	void add(int u, int v) {
		next[cnt] = head[u];
		vert[cnt] = v;
		head[u] = cnt++;
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
