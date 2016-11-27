import java.io.*;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		long[] a = new long [800];
		long[] b = new long [800];
		PrintWriter out = new PrintWriter(System.out);
		for (int T = readInt(), t = 1; t <= T; t++) {
			int N = readInt();
			for (int i = 0; i < N; i++)
				a[i] = readInt();
			for (int i = 0; i < N; i++)
				b[i] = readInt();
			sort(a, 0, N);
			sort(b, 0, N);
			long ans = 0L;
			for (int i = 0; i < N; i++)
				ans += a[i] * b[N - i - 1];
			out.println("Case #" + t + ": " + ans);
		}
		out.close();
	}
	
	static int readInt() throws IOException {
		int n = 0, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (; '0' <= c && c <= '9'; c = System.in.read())
			n = 10 * n + c - '0';
		return s * n;
	}
}
