import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(); T --> 0;) {
			int dif = Math.abs(nextInt() - nextInt());
			int l = 0;
			int r = 65535;
			int k = -1;
			while (l <= r) {
				long m = (r + l) >> 1;
				if (m * (m + 1L) <= dif)
					l = (k = (int) m) + 1;
				else
					r = (int) m - 1;
			}
			int rst = dif - k * (k + 1);
			out.println((2 * k + ((rst) > 0 ? (rst > k + 1 ? 2 : 1) : 0)));
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
