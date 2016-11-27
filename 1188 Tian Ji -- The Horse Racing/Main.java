import java.io.*;

import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] a = new int [1000];
		int[] b = new int [1000];
		PrintWriter out = new PrintWriter(System.out);
		for (int N = nextInt(); N > 0; N = nextInt()) {
			for (int i = 0; i < N; i++)
				a[i] = nextInt();
			for (int i = 0; i < N; i++)
				b[i] = nextInt();
			sort(a, 0, N);
			sort(b, 0, N);
			int u = 0, v = N - 1, x = 0, y = N - 1, ans = 0;
			while (u <= v)
				if (a[v] < b[y] || a[u] < b[x] || a[v] == b[y] && a[u] == b[x])
					ans += cmp(a[u++], b[y--]);
				else if (a[v] > b[y])
					ans += cmp(a[v--], b[y--]);
				else if (a[u] > b[x])
					ans += cmp(a[u++], b[x++]);
			out.println(200 * ans);
		}
		out.close();
	}

	static int cmp(int a, int b) {
		return a == b ? 0 : (a < b ? -1 : 1);
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
