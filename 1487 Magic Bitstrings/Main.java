import java.io.*;
import java.math.BigInteger;
import java.util.Random;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new Main().run();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "1", 1L << 24).start();
		
	}

	PrintWriter out;
	
	int MAXP = 100000;
	int P;
	
	int[] up = new int [MAXP + 1];
	int[] dn = new int [MAXP + 1];
	int s;
	
	void run() throws IOException {
		out = new PrintWriter(System.out);
		
		for (;;) {
			P = nextInt();
			if (P == 0)
				break;
			if (P == 2) {
				out.println("Impossible");
				continue;
			}
			int ans = P;
			for (int i = min(320, P - 1); i > 0; i--)
				if (can(i, true)) {
					ans = i;
					break;
				}
			if (ans == P) {
				for (int i = min(320, P - 1); i > 0; i--)
					if (can(i, false)) {
						ans = i;
						break;
					}
				if (ans == P) {
					out.println("Impossible");
				} else {
					for (int i = 1; i < P; i++)
						out.print(up[i]);
					out.println();
				}
			} else {
				can(ans, true);
				for (int i = 1; i < P; i++)
					out.print(up[i]);
				out.println();
			}
		}
		
		out.close();
	}

	boolean can(int from, boolean type) {
		fill(up, 0, P, -1);
		fill(dn, 0, P, -1);
		s = from;
		for (int i = 1; i < P; i++)
			if (up[i] == -1 && !set(i, type ? 0 : 1, 0, (P - 1) / gcd(i, P - 1), type))
				return false;
		return true;
	}
	
	boolean set(int uind, int val, int step, int max, boolean type) {
		if (step == max)
			return true;
		int neg = 1 - val;
		int dind = uind * s % P;
		if (val == 1 && uind < s)
			return false;
		if (type) {
			if (up[uind] == neg || dn[uind] == val)
				return false;
			up[uind] = val;
			dn[uind] = neg;
		} else {
			if (up[uind] == neg || dn[uind] == neg)
				return false;
			up[uind] = val;
			dn[uind] = val;
		}
		return set(dind, type ? neg : val, step + 1, max, type);
	}

	int gcd(int a, int b) {
		while (a > 0 && b > 0)
			if (a > b)
				a %= b;
			else
				b %= a;
		return a + b;
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
