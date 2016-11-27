import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	int MAXL = 32;
	int N;
	int len;
	int[] ans = new int [MAXL];
	int[] cur = new int [MAXL];
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (N = nextInt(); N > 0; N = nextInt()) {
			len = MAXL;
			cur[0] = 1;
			dfs(1);
			out.print(ans[0]);
			for (int i = 1; i < len; i++) {
				out.print(' ');
				out.print(ans[i]);
			}
			out.println();
		}
		out.close();
	}

	void dfs(int k) {
		if (k >= len)
			return;
		int max = cur[k - 1];
		if (max == N) {
			len = k;
			for (int i = 0; i < k; i++)
				ans[i] = cur[i];
			return;
		}
		for (int i = k - 1; i >= 0; i--)
			for (int j = i; j >= 0; j--) {
				int sum = cur[i] + cur[j];
				if (max < sum && sum <= N) {
					cur[k] = sum;
					dfs(k + 1);
				}
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
