import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	String[] names = new String [16];
	double[][] g = new double [16][16];
	double[][] dp = new double [5][16];
	
	void run() throws IOException {
		Arrays.fill(dp[0], 1.0);
		for (int i = 1; i < 5; i++)
			Arrays.fill(dp[i], -1.0);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int i = 0; i < 16; i++)
			names[i] = in.readLine();
		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 16; j++)
				g[i][j] = nextInt() / 100.0;
		for (int i = 0; i < 16; i++) {
			out.print(names[i]);
			for (int k = 11 - names[i].length(); k --> 0;)
				out.print(' ');
			int p = (int) (dfs(i, 8, 4) * 10000.0 + 0.5);
			out.print("p=");
			out.print(p / 100);
			out.print('.');
			if (p % 100 < 10)
				out.print('0');
			out.print(p % 100);
			out.println('%');
		}
		out.close();
	}
	
	double dfs(int c, int l, int p) {
		if (dp[p][c] > -1.0)
			return dp[p][c];
		double res = 0.0;
		for (int o = (c / l) % 2 == 0 ? c - c % l + l : c - c % l - l, i = 0; i < l; i++)
			res += g[c][o + i] * dfs(o + i, l / 2, p - 1);
		return dp[p][c] = res * dfs(c, l / 2, p - 1);
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
