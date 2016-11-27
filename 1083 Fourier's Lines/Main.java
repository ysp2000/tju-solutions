import java.io.*;
import java.util.Arrays;

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

	int K;
	int M;
	int N;
	int sNum;
	int[] s = new int [100];
	
	boolean[][] dp = new boolean [101][10001];
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		
		for (int t = 1;; t++) {
			N = nextInt();
			M = nextInt();
			if (N == 0 && M == 0)
				break;
			K = N * N - (M << 1);
			boolean ok = K >= 0;
			if (ok)
				for (int i = 0; i <= N; i++)
					fill(dp[i], 0, K + 1, false);
			out.print("Case ");
			out.print(t);
			out.print(": ");
			out.print(N);
			out.print(" lines ");
			if (ok && dfs(0, 0, N, 0)) {
				int ans = 1;
				int L = 1;
				for (int i = 0; i < sNum; i++) {
					ans += L * s[i];
					L += s[i];
				}
				out.print("with exactly ");
				out.print(M);
				out.print(" crossings can cut the plane into ");
				out.print(ans);
				out.println(" pieces at most.");
			} else {
				out.print("cannot make exactly ");
				out.print(M);
				out.println(" crossings.");
			}
		}
		
		out.close();
	}
	
	boolean dfs(int v, int w, int max, int i) {
		if (v == N) {
			sNum = i;
			return w == K;
		}
		if (w >= K)
			return false;
		if (dp[v][w])
			return false;
		dp[v][w] = true;
		for (int x = min(max, N - v); x > 0; x--)
			if (dfs(v + x, w + x * x, s[i] = x, i + 1))
				return true;
		return false;
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
