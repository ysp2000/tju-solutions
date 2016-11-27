import java.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int N = nextInt();
		int K = nextInt();
		int[] xs = new int [K];
		for (int i = 0; i < K; i++) xs[i] = N - nextInt();
		int ans = 0;
		sort(xs);
		for (int f = 0, x = 0; x <= N; x++) {
			int y = abs(x - xs[f]);
			for (int ny; f + 1 < K && y >= (ny = abs(x - xs[f + 1])); f++, y = ny);
			ans = max(ans, y);
		}
		System.out.println(ans);
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
