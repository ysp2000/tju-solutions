import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int MAXN = 10000;
	int[] head = new int [MAXN];
	int[] next = new int [2 * (MAXN - 1) + 1];
	int[] vert = new int [2 * (MAXN - 1) + 1];
	int cnt = 1;
	int[] bal = new int [MAXN];
	int ans;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int n = nextInt(); n > 0; n = nextInt()) {
			cnt = 1;
			Arrays.fill(head, 0, n, 0);
			for (int i = 0; i < n; i++) {
				int u = nextInt() - 1;
				bal[u] = nextInt() - 1;
				for (int m = nextInt(), j = 0; j < m; j++) {
					int v = nextInt() - 1;
					add(u, v);
					add(v, u);
				}
			}
			ans = 0;
			dfs(0, -1);
			out.println(ans);
		}
		out.close();
	}
	
	void add(int u, int v) {
		next[cnt] = head[u];
		vert[cnt] = v;
		head[u] = cnt++;
	}
	
	void dfs(int v, int p) {
		for (int i = head[v]; i != 0; i = next[i]) {
			int x = vert[i];
			if (x != p) {
				dfs(x, v);
				bal[v] += bal[x];
			}
		}
		ans += abs(bal[v]);
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
