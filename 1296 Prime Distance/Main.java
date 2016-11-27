import java.io.*;
import java.util.Arrays;

import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}

		PrintWriter out = new PrintWriter(System.out);
		boolean[] erat = new boolean [1000000 + 1];
		for (;;) {
			int l, r;
			long L = l = max(2, nextInt());
			long R = r = nextInt();
			if (r < 0)
				break;
			if (r <= 2) {
				out.println("There are no adjacent primes.");
				continue;
			}
			Arrays.fill(erat, 0, r - l + 1, false);
			for (long i = Math.max(4L, (l & 1) == 0 ? L : (L + 1L)); i <= R; i += 2)
				erat[(int) (i - L)] = true;
			for (long i = 3L, s = i * i; s <= R; i += 2, s = i * i)
				for (long j = Math.max(s, L % i == 0 ? L : (L + i - L % i)); j <= R; j += i)
					erat[(int) (j - L)] = true;
			int prv = -1;
			int min = 1000001;
			int max = 0;
			int a1, a2;
			int b1, b2;
			a1 = a2 = b1 = b2 = -1;
			for (int i = l;; i++) {
				if ((i & 1) == 0 && i != 2)
					if (i == r)
						break;
					else
						continue;
				if (!erat[i - l]) {
					if (prv == -1)
						prv = i;
					else {
						int dist = i - prv;
						if (min > dist) {
							min = dist;
							a1 = prv;
							a2 = i;
						}
						if (max < dist) {
							max = dist;
							b1 = prv;
							b2 = i;
						}
						prv = i;
					}
				}
				if (i == r)
					break;
			}
			out.println(a1 == -1 ? "There are no adjacent primes." : (a1 + "," + a2 + " are closest, " + b1 + "," + b2 + " are most distant."));
		}
		out.close();
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == -1)
				return -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
