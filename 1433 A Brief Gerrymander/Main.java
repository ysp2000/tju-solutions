import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;

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
	
	int[][] map = new int [100][100];
	int[] bit = new int [100];
	int[] fs = new int [100];
	int tick;
	
	long[] m1 = new long [100];
	long[] m2 = new long [100];
	
	int[][] dp = new int [100][100 + 1];
	int[][] p = new int [100][100 + 1];
	int[] ans = new int [100];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int n = nextInt(); n != -1; n = nextInt()) {
			tick++;
			for (int i = 0; i < n; i++) {
				int sn = nextInt() - 1;
				int an = nextInt() - 1;
				map[sn][an] = tick;
			}
			int S = nextInt();
			for (int i = 0; i < S; i++)
				fs[i] = nextInt() - 1;
			for (int cf = 0, sn = 0; sn < 100; sn++) {
				if (sn == fs[cf])
					cf++;
				bit[sn] = cf;
			}
			int N = nextInt();
			
			if (N == 100) {
				out.print("100");
				for (int i = 1; i <= 100; i++)
					out.print(" " + i);
				out.println();
				continue;
			}
			N -= 1;
			fill(m1, 0);
			fill(m2, 0);
			for (int sn = 0; sn < 100; sn++)
				for (int an = 0; an < 100; an++)
					if (map[sn][an] == tick)
						set(an + 1, bit[sn]);
			for (int i = 0; i <= N; i++) {
				fill(dp[i], -1);
				fill(p[i], -1);
			}
			dp[0][1] = 0;
			for (int fixed = 0; fixed < N; fixed++) {
				for (int last = 0; last < 100; last++) {
					int cv = dp[fixed][last];
					if (cv != -1) {
						long cm1 = 0;
						long cm2 = 0;
						for (int next = last + 1; next <= 100; next++) {
							cm1 |= m1[next - 1];
							cm2 |= m2[next - 1];
							int nv = cv + Long.bitCount(cm1) + Long.bitCount(cm2);
							if (dp[fixed + 1][next] < nv) {
								dp[fixed + 1][next] = nv;
								p[fixed + 1][next] = last;
							}
						}
					}
				}
			}
			int ind = 100;
			for (int i = N; i > 0; i--) {
				ans[i - 1] = ind;
				ind = p[i][ind];
			}
			out.print((N + 1) + " 1");
			for (int i = 0; i < N; i++)
				out.print(" " + ans[i]);
			out.println();
		}
		out.close();
	}
	
	void set(int an, int bit) {
		if (bit < 63)
			m1[an] |= 1L << bit;
		else
			m2[an] |= 1L << (bit - 64);
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
