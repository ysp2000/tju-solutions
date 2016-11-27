import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (;;) {
			long a = nextLong();
			long b = nextLong();
			if (a + b == 0L)
				break;
			long res = 1L;
			for (long n = a + b, k = Math.min(a, b), i = 1; i <= k; i++)
				res = res * (n - k + i) / i;
			out.println(res);
		}
		out.close();
	}

	static long nextLong() throws IOException {
		long n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0L; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10L + c - '0';
		return n;
 	}
}
