import java.io.*;

import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		Block[] b = new Block [90];
		int[] dp = new int [90];
		for (int N = nextInt(), t = 1; N > 0; N = nextInt()) {
			int sz = 0;
			for (int i = 0; i < N; i++) {
				int x = nextInt();
				int y = nextInt();
				int z = nextInt();
				b[sz++] = new Block(x, y, z);
				b[sz++] = new Block(z, x, y);
				b[sz++] = new Block(y, z, x);
			}
			sort(b, 0, sz);
			fill(dp, 0, sz, 0);
			int ans = 0;
			for (int i = 0; i < sz; i++) {
				for (int j = 0; j < i; j++)
					if (b[i].place(b[j]) && dp[i] < dp[j])
						dp[i] = dp[j];
				dp[i] += b[i].z;
				if (ans < dp[i])
					ans = dp[i];
			}
			out.print("Case ");
			out.print(t++);
			out.print(": maximum height = ");
			out.println(ans);
		}
		out.close();
	}
	
	class Block implements Comparable<Block> {
		int x;
		int y;
		int z;
		int s;
		
		Block(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
			s = x * y;
		}

		boolean place(Block b) {
			return b.x < x && b.y < y || b.y < x && b.x < y;
		}

		@Override
		public int compareTo(Block b) {
			return s - b.s;
		}
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
