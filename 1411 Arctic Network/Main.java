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

	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		DSF dsf = new DSF();
		int[] x = new int [500];
		int[] y = new int [500];
		int[][] d = new int [500][500];
		for (int T = nextInt(); T --> 0;) {
			int S = max(1, nextInt());
			int P = nextInt();
			for (int i = 0; i < P; i++) {
				x[i] = nextInt();
				y[i] = nextInt();
			}
			int l = 0;
			int r = 0;
			for (int i = 0; i < P; i++)
				for (int j = i + 1; j < P; j++)
					r = max(r, d[i][j] = sqr(x[i] - x[j]) + sqr(y[i] - y[j]));
			int ans = 1 << 30;
			while (l <= r) {
				int cd = (l + r) >> 1;
				dsf.initialize(P);
				for (int i = 0; i < P; i++)
					for (int j = i + 1; j < P; j++)
						if (d[i][j] <= cd)
							dsf.union(i, j);
				int ssc = dsf.ssc();
				if (ssc <= S) {
					ans = min(ans, cd);
					r = cd - 1;
				} else
					l = cd + 1;
			}
			int a = (int) (100.0 * sqrt(ans) + 0.5);
			out.print(a / 100);
			out.print('.');
			a %= 100;
			if (a < 10)
				out.print('0');
			out.println(a);
		}
		out.close();
	}

	int sqr(int x) {
		return x * x;
	}
	
	class DSF {
		int sz;
		int[] set = new int [500];
		int[] rnk = new int [500];
		
		void initialize(int sz) {
			this.sz = sz;
			fill(rnk, 0, sz, 0);
			for (int i = 0; i < sz; i++)
				set[i] = i;
		}
		
		int ssc() {
			int ret = 0;
			for (int i = 0; i < sz; i++)
				if (rnk[setOf(i)] != -1) {
					rnk[setOf(i)] = -1;
					ret++;
				}
			return ret;
		}

		int setOf(int i) {
			return i == set[i] ? i : (set[i] = setOf(set[i]));
		}
		
		boolean union(int i, int j) {
			i = setOf(i);
			j = setOf(j);
			if (i == j)
				return false;
			if (rnk[i] > rnk[j])
				set[j] = i;
			else {
				set[i] = j;
				if (rnk[i] == rnk[j])
					rnk[j]++;
			}
			return true;
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
