import java.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;

public class Main {

	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}

		new Main().run();
	}

	int INF = Integer.MAX_VALUE;
	
	int N;
	int[] a;
	int[][] mem;
	
	void run() throws IOException {
		N = nextInt();
		a = new int [N];
		for (int i = 0; i < N; i++)
			a[i] = nextInt();
		mem = new int [N][N];
		for (int[] arr : mem)
			fill(arr, INF);
		System.out.println(rec(0, N - 1));
	}
	
	int rec(int left, int right) {
		if (left + 1 == right)
			return 0;
		if (mem[left][right] != INF)
			return mem[left][right];
		int ret = INF;
		for (int mid = left + 1; mid < right; mid++) 
			ret = min(ret, a[left] * a[mid] * a[right] + rec(left, mid) + rec(mid, right));
		return mem[left][right] = ret;
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
