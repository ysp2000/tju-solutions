import java.io.*;

import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		boolean[] used = new boolean [1000 + 1];
		int[] a = new int [50];
		
		PrintWriter out = new PrintWriter(System.out);
		for (int t = 1;; t++) {
			int M = nextInt();
			int N = nextInt();
			if (M == 0)
				break;
			fill(used, 0, N * M + 1, false);
			for (int i = 0; i < N; i++)
				used[a[i] = nextInt()] = true;
			sort(a, 0, N);
			int ans = 0;
			for (int j = 1, i = N - 1; i >= 0; i--) {
				int cur = a[i];
				int k = M - 1;
				boolean win = true;
				for (int l = cur + 1; l <= N * M; l++)
					if (!used[l]) {
						used[l] = true;
						k--;
						win = false;
						break;
					}
				for (; k > 0 && j <= N * M; j++)
					if (!used[j]) {
						used[j] = true;
						k--;
					}
				if (win && cur > j - 1)
					ans++;
			}
			out.println("Case " + t + ": " + ans);
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
