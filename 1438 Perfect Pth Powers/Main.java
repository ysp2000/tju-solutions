import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (long x = nextLong(); x != 0L; x = nextLong()) {
			int ans = 1;
			boolean neg = x < 0L;
			if (neg)
				x = -x;
			lp: for (int p = 2; p <= 32; p++) {
				if (neg && (p & 1) == 0)
					continue;
				long b = (long) Math.pow(x, 1.0 / p);
				for (long dt = -2L; dt < 2L; dt++)
					if (lpow(b + dt, p) == x) {
						ans = p;
						continue lp;
					}
			}
			out.println(ans);
		}
		out.close();
	}

	static long lpow(long b, int e) {
		long ret = 1L;
		for (; e > 0; e >>= 1) {
			if ((e & 1) == 1)
				ret *= b;
			b *= b;
		}
		return ret;
	}
	
	static long nextLong() throws IOException {
		long n, c, s = 1L;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1L;
		for (n = 0L; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10L + c - '0';
		return s * n;
 	}
}
