import java.io.*;
import java.util.*;

import static java.lang.Math.*;

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
	
	int L1;
	int L2;
	int[] S1;
	int[] S2;
	int BASE = java.math.BigInteger.probablePrime(19, new Random()).intValue();
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		L1 = nextInt(); S1 = new int [L1];
		L2 = nextInt(); S2 = new int [L2];
		for (int i = 0; i < L1; i++) S1[i] = nextInt() + 100;
		for (int i = 0; i < L2; i++) S2[i] = nextInt() + 100;
		Set<Integer> set = new HashSet<Integer>();
		int l = 0;
		int r = min(L1, L2);
		int ans = 0;
		while (l <= r) {
			int m = (l + r) >> 1;
			int h = 0;
			int p = 1;
			for (int i = 0; i < m; i++)
				h = (h * BASE) + S1[i];
			set.clear(); set.add(h);
			for (int i = 1; i < m; i++)
				p *= BASE;
			for (int i = m; i < L1; i++)
				set.add(h = ((h - p * S1[i - m]) * BASE) + S1[i]);
			h = 0;
			for (int i = 0; i < m; i++)
				h = (h * BASE) + S2[i];
			boolean ok = set.contains(h);
			for (int i = m; i < L2 && !ok; i++)
				ok = set.contains(h = ((h - p * S2[i - m]) * BASE) + S2[i]);
			if (ok) {
				ans = max(ans, m);
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		out.println(ans);
		out.close();
	}
	
	int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return s == 1 ? n : -n;
 	}
}
