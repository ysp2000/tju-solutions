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
	int T;
	int[] fi = new int [25];
	int[] di = new int [25];
	int[] ti = new int [25];
	int[] ans = new int [25];
	int[] dp = new int [(192 + 1) * (25 + 1) * (192 + 1)];
	
	int M1;
	int M2;
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		boolean blank = false;
		for (N = nextInt(); N > 0; N = nextInt()) {
			if (blank)
				out.println();
			blank = true;
			T = nextInt() * 12;
			for (int i = 0; i < N; i++)
				fi[i] = nextInt();
			for (int i = 0; i < N; i++)
				di[i] = nextInt();
			for (int i = 0; i < N - 1; i++)
				ti[i] = nextInt();
			M2 = (T + 1);
			M1 = (N + 1) * M2;
			fill(dp, 0, (T + 1) * (N + 1) * (T + 1), -1);
			dfs(0, 0, 0);
			int exp = dp[0];
			fill(ans, 0, N, 0);
			for (int t = 0, i = 0, l = 0; t < T;) {
				int a = max(0, fi[i] - l * di[i]) + dp[M1 * (t + 1) + M2 * i + l + 1];
				int b = t + ti[i] <= T ? dp[M1 * (t + ti[i]) + M2 * (i + 1)] : -1;
				if (a >= b) {
					t++;
					l++;
					ans[i]++;
				} else {
					t += ti[i];
					i++;
					l = 0;
				}
			}
			out.print(ans[0] * 5);
			for (int i = 1; i < N; i++) {
				out.print(", ");
				out.print(ans[i] * 5);
			}
			out.println();
			out.print("Number of fish expected: ");
			out.println(exp);
		}
		
		out.close();
	}

	int dfs(int t, int i, int l) {
		if (t > T)
			return -1;
		int til = M1 * t + M2 * i + l;
		if (t == T || i == N || l == T)
			return dp[til] = 0;
		if (dp[til] != -1)
			return dp[til];
		return dp[til] = max(max(0, fi[i] - l * di[i]) + dfs(t + 1, i, l + 1), dfs(t + ti[i], i + 1, 0));
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
