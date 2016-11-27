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

	BufferedReader in;
	PrintWriter out;

	int INF = Integer.MAX_VALUE >> 1;
	int MAXN = 100;
	int N;
	int[][] dp = new int [MAXN][MAXN];
	int[] a = new int [MAXN];
	int[] p = new int [MAXN];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(); T --> 0; ) {
			N = nextInt();
			for (int i = 0; i < N; i++) {
				a[N - i - 1] = nextInt();
				p[N - i - 1] = nextInt();
			}
			for (int i = 0; i < N; i++)
				fill(dp[i], 0, N, INF);
			dp[0][0] = (a[0] + 10) * p[0];
			for (int i = 1; i < N; i++)
				for (int j = 0; j <= i; j++)
					for (int k = 0; k <= min(i - 1, j); k++)
						if (k < j)
							dp[i][j] = min(dp[i][j], dp[i - 1][k] + (a[i] + 10) * p[j]);
						else
							dp[i][j] = min(dp[i][j], dp[i - 1][k] + a[i] * p[j]);
			int ans = INF;
			for (int i = 0; i < N; i++)
				ans = min(ans, dp[N - 1][i]);
			out.println(ans);
		}
		
		out.close();
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
