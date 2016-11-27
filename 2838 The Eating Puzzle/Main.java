import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		int N = nextInt();
		boolean[] dp = new boolean [N + 1];
		dp[0] = true;
		int ans = 0;
		for (int M = nextInt(), k = 0; k < M; k++) {
			int w = nextInt();
			if (w <= N)
				for (int j = N - w; j >= 0; j--)
					if (dp[j]) {
						dp[j + w] = true;
						if (ans < j + w)
							ans = j + w;
					}
		}
		System.out.println(ans);
	}

	static int nextInt() throws IOException {
		int ch, res = 0;
		for (ch = System.in.read(); ch < '0' || '9' < ch; ch = System.in.read());
		for (; '0' <= ch && ch <= '9'; ch = System.in.read())
			res = 10 * res + ch - '0';
		return res;
	}
}
