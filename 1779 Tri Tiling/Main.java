import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[][] go = { { 1, 4, 7 }, { 0, 6 }, { 5 }, { 4 }, { 0, 3 }, { 2 }, { 1 }, { 0 } };
		int[][] dp = new int [30 + 1][8];
		dp[0][0] = 1;
		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 8; j++)
				for (int k = 0; k < go[j].length; k++)
					dp[i + 1][go[j][k]] += dp[i][j];
		PrintWriter out = new PrintWriter(System.out);
		for (int n;;)
			if ((n = nextInt()) < 0)
				break;
			else
				out.println(dp[n][0]);
		out.close();
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
