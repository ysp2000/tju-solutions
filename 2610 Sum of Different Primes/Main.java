import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	
	void run() throws IOException {
		int MAXP = 1120;
		int SQRT = 34;
		boolean[] erat = new boolean [MAXP + 1];
		int[] primes = new int [187];
		int psz = 0;
		primes[psz++] = 2;
		for (int i = 4; i <= MAXP; i += 2)
			erat[i] = true;
		for (int i = 3; i <= MAXP; i += 2)
			if (!erat[i]) {
				primes[psz++] = i;
				if (i <= SQRT)
					for (int j = i * i; j <= MAXP; j += i)
						erat[j] = true;
			}
		int[][] dp = new int [1120 + 1][14 + 1];
		dp[0][0] = 1;
		for (int p : primes) {
			for (int i = 1120 - p; i >= 0; i--)
				for (int j = 13; j >= 0; j--)
					dp[i + p][j + 1] += dp[i][j];
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int n = nextInt(); n > 0; n = nextInt())
			out.println(dp[n][nextInt()]);
		out.close();
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
