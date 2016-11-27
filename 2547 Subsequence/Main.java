import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] a = new int [100000];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(in); T --> 0;) {
			int N = nextInt(in), S = nextInt(in);
			for (int i = 0; i < N; i++)
				a[i] = nextInt(in);
			if (S == 0) {
				out.println("0");
				continue;
			}
			int ans = N + 1;
			for (int i = 0, j = 0, s = 0; j < N; j++)
				if ((s += a[j]) >= S) {
					while (s - a[i] >= S)
						s -= a[i++];
					if (ans > j - i + 1)
						ans = j - i + 1;
				}
			out.println(ans == N + 1 ? 0 : ans);
		}
		out.close();
	}
	
	static int nextInt(BufferedReader in) throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
