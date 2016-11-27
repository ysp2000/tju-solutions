import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	int N;
	int S;
	int[] a;
	int[] dp = new int [(1 << 20) + 1];

	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		a = new int [20];
		for (int T = nextInt(); T --> 0;) {
			N = nextInt();
			S = 0;
			for (int i = 0; i < N; i++)
				S += a[i] = nextInt();
			if ((S & 3) != 0) {
				out.println("no");
				continue;
			}
			S >>= 2;
			Arrays.fill(dp, 0, 1 << N, -1);
			for (int i = 0; i < N; i++) {
				int max = i;
				for (int j = i + 1; j < N; j++)
					if (a[max] < a[j])
						max = j;
				swap(a, i, max);
			}
			boolean ok = false;
			try {
				dfs(0, 0, 0);
			} catch (Found f) {
				ok = true;
			}
			out.println(ok ? "yes" : "no");
		}
		out.close();
	}
	
	void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	int dfs(int side, int sum, int mask) {
		if (dp[mask] != -1)
			return dp[mask];
		if (sum > 0 && sum % S == 0)
			side++;
		if (side == 4)
			throw new Found();
		if (sum > (side + 1) * S)
			return dp[mask] = 1;
		int ret = 0;
		for (int i = 0; i < N; i++)
			if ((mask & (1 << i)) == 0)
				if (side + dfs(side, sum + a[i], mask | (1 << i)) == 4)
					return dp[mask] = 4 - side;
		return dp[mask] = ret;
	}
	
	class Found extends RuntimeException {
		private static final long serialVersionUID = 4866214977464028152L;
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
