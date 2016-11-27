import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		int i, j, MAXN = 300;
		BigInteger[][] dp = new BigInteger [MAXN + 1][MAXN + 1];
		for (dp[0][0] = BigInteger.ONE, i = 1; i <= MAXN; i++)
			for (dp[i][0] = BigInteger.ONE, dp[i - 1][i] = BigInteger.ZERO, j = 1; j <= i; j++)
				dp[i][j] = dp[i][j - 1].add(dp[i - 1][j]);
		
		PrintWriter out = new PrintWriter(System.out);
		
		for (;;) {
			i = readInt();
			j = readInt();
			if (i == 0 && j == 0)
				break;
			out.println(dp[i][j] == null ? 0 : dp[i][j]);
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
