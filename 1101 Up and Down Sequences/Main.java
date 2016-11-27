import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int UP = 0;
		int BOTH = 1;
		int DOWN = 2;

		PrintWriter out = new PrintWriter(System.out);
		
		for (;;) {
			int len = 0;
			int state = BOTH;
			int up = 0;
			int down = 0;
			int usz = 0;
			int dsz = 0;
			int p = nextInt();
			if (p == 0)
				break;
			int cnt = 1;
			for (int n = nextInt(); n != 0; p = n, n = nextInt()) {
				cnt++;
				if (n < p) {
					if (state == UP) {
						up += len;
						usz++;
						len = 1;
						state = DOWN;
					} else if (state == BOTH) {
						state = DOWN;
						len++;
					} else if (state == DOWN) {
						len++;
					}
				}
				if (n == p)
					len++;
				if (n > p) {
					if (state == UP) {
						len++;
					} else if (state == BOTH) {
						state = UP;
						len++;
					} else if (state == DOWN) {
						down += len;
						dsz++;
						len = 1;
						state = UP;
					}
				}
			}
			if (len > 0) {
				if (state == UP) {
					up += len;
					usz++;
				} else if (state == DOWN) {
					down += len;
					dsz++;
				}
					
			}
			out.printf(java.util.Locale.US, "Nr values = %d: %.6f %.6f%n", cnt, usz == 0 ? 0 : up / (double) usz, dsz == 0 ? 0 : down / (double) dsz);
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
