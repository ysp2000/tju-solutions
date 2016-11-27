import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (long T = nextLong(), n, k, lcm; T --> 0;) {
			for (n = nextLong(), lcm = nextLong(); n --> 1;)
				lcm = lcm * (k = nextLong()) / gcd(lcm, k);
			out.println(lcm);
		}
		out.close();
	}
	
	private static long gcd(long a, long b) {
		while (a > 0L && b > 0L)
			if (a > b)
				a %= b;
			else
				b %= a;
		return a + b;
	}

	static long nextLong() throws IOException {
		long n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0L; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10L + c - '0';
		return n;
 	}
}
