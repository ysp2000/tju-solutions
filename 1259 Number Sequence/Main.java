import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}

		int[] a = { 0, 9, 180, 2700, 36000, 450000, 5400000, 63000000, 720000000, Integer.MAX_VALUE };
		int[] b = { 0, 1, 10,  100,  1000,  10000,  100000,  1000000,  10000000,  100000000, 1000000000 };

		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(); T --> 0;) {
			int n = nextInt() - 1, k;
			for (int i = 1, x = 0; n >= x; x += digNum(i++))
				n -= x;
			for (k = 0; n >= a[k]; k++)
				n -= a[k];
			out.println((b[k] + n / k) / b[k - n % k] % 10);
		}
		out.close();
	}

	static int digNum(int x) {
		int ret = 0;
		for (; x > 0; x /= 10)
			ret++;
		return ret;
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
