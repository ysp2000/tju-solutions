import java.io.*;
import java.util.Locale;

import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		double EPS = 1e-8;
		double[] A = new double [100 + 2];
		double[] B = new double [100 + 2];
		double[] cur = A;
		double[] nxt = B;
		
		PrintWriter out = new PrintWriter(System.out);
		for (int C = nextInt(); C > 0; C = nextInt()) {
			int N = nextInt();
			if (N > 20000)
				N = 20000 + ((N - 20000) % 2 == C % 2 ? (C % 2) : (1 - C % 2));
			int M = nextInt();
			if (M > C) {
				out.println("0.000");
				continue;
			}
			fill(cur, 0, C + 1, 0.0);
			cur[0] = 1.0;
			for (int it = 0; it < N; it++) {
				fill(nxt, 0, C + 1, 0.0);
				for (int k = 0; k <= C; k++)
					if (cur[k] > EPS) {
						if (k > 0)
							nxt[k - 1] += k * cur[k] / C;
						if (k < C)
							nxt[k + 1] += (C - k) * cur[k] / C;
					}
				if (cur == A) {
					cur = B;
					nxt = A;
				} else {
					cur = A;
					nxt = B;
				}
			}
			out.printf(Locale.US, "%.3f%n", cur[M]);
		}
		out.close();
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
