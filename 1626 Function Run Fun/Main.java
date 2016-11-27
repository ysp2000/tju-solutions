import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int[][][] dp = new int [20 + 1][20 + 1][20 + 1];
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (;;) {
			int a = nextInt();
			int b = nextInt();
			int c = nextInt();
			if (a == -1 && b == -1 && c == -1)
				break;
			out.println("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c));
		}
		out.close();
	}
	
	int w(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0)
			return 1;
		if (a > 20 || b > 20 || c > 20)
			return w(20, 20, 20);
		if (dp[a][b][c] != 0)
			return dp[a][b][c];
		return dp[a][b][c] = (a < b && b < c ? (w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)) : (w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1)));
	}

	static int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return s * n;
 	}
}
