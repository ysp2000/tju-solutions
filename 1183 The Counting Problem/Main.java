import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	long[] t = new long [10];
	int[] zcnt = { 0, 1, 10, 190, 2890, 38890, 488890, 5888890, 68888890 };
	int[][] cnt = new int [8 + 1][10];	
	
	void run() throws IOException {
		for (int p = 1, i = 1; i <= 8; cnt[i][0] = zcnt[i], p *= 10, i++)
			for (int v = i * p, j = 1; j < 10; j++)
				cnt[i][j] = v;
		PrintWriter out = new PrintWriter(System.out);
		long[] a = new long [10];
		calc(10, a);
		System.out.println(Arrays.toString(a));
		long[] b = new long [10];
//		for (;;) {
//			int A = nextInt();
//			if (A == 0)
//				break;
//			int B = nextInt();
//			fill(a, 0);
//			fill(b, 0);
//			calc(A, a);
//			System.out.println(Arrays.toString(a));
//			calc(B, b);
//			System.out.println(Arrays.toString(b));
//			out.print(b[0] - a[0]);
//			for (int i = 1; i < 9; i++) {
//				out.print(' ');
//				out.print(b[i] - a[i]);
//			}
//			out.println();
//		}
		out.close();
	}
	
	void calc(int N, long[] a) {
		int d = 0;
		int p = 1;
		while (N > 0) {
			for (; N % 10 == 0; N /= 10, p *= 10, d++)
				if (N == 0)
					return;
			System.out.println(N + " " + p + " " + d);
			fill(t, 0);
			for (int n = N; n > 0; n /= 10)
				t[n % 10]++;
			for (int i = 0; i < 10; i++)
				a[i] += t[i] * p + cnt[d][i];
			N--;
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
