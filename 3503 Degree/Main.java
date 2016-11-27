import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		PrintWriter out = new PrintWriter(System.out);
		int[] a = new int [10000];
		int[] b = new int [10000];
		int tick = -1;
		for (int T = readInt(), t = 0; t < T; t++) {
			tick++;
			int ans = 0;
			for (int n = readInt(), i = 0; i < n; i++) {
				int x = readInt();
				if (b[x] != tick) {
					b[x] = tick;
					a[x] = 0;
				}
				ans = Math.max(ans, ++a[x]);
			}
			out.println(ans);
		}
		out.close();
	}

	static int readInt() throws IOException {
		int n = 0, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (; '0' <= c && c <= '9'; c = System.in.read())
			n = 10 * n + c - '0';
		return n;
	}
}
