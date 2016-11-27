import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	int INF = Integer.MAX_VALUE >> 1;
	String FILENAME = "unionday";
	
	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int vNum;
	int[] x;
	int[] y;
	
	Random rnd = new Random(System.nanoTime());
	
	void run() throws IOException {
		in = new BufferedReader(new FileReader(FILENAME + ".in"));
		out = new PrintWriter(FILENAME + ".out");
		
		vNum = nextInt();
		
		x = new int [vNum];
		y = new int [vNum];
		
		for (int i = 0; i < vNum; i++) {
			x[i] = nextInt();
			y[i] = nextInt();
		}

		out.println(prim());
		out.close();
	}
	
	double prim() {
		double ret = 0.0;
		boolean[] used = new boolean [vNum];
		int[] dist = new int [vNum];
		RMQ rmq = new RMQ(vNum);
		fill(dist, INF);
		rmq.set(0, dist[0] = 0);

		for (;;) {
			int v = rmq.minInd(0, vNum - 1);
			if (v == -1) break;
			ret += sqrt(dist[v]);
			used[v] = true;
			rmq.set(v, INF);
			for (int i = 0; i < vNum; i++)
				if (!used[i] && dist[i] > dist(v, i))
						rmq.set(i, dist[i] = dist(v, i));
		}
		
		return ret;
	}
	
	int dist(int i, int j) {
		return sqr(x[j] - x[i]) + sqr(y[j] - y[i]);
	}

	int sqr(int x) {
		return x * x;
	}

	class RMQ {
		int n;
		int[] a;
		int[] b;
		
		RMQ(int n) {
			this.n = n;
			a = new int [2 * n];
			b = new int [2 * n];
			Arrays.fill(a, INF);
			for (int i = 0; i < n; i++)
				b[n + i] = i;
		}
		
		void set(int i, int v) {
			a[i += n] = v;
			for (i >>= 1; i > 0; i >>= 1) {
				int j = i << 1;
				a[i] = a[j];
				b[i] = b[j];
				if (++j < (n << 1))
					if (a[i] > a[j]) {
						a[i] = a[j];
						b[i] = b[j];
					}
			}
		}
		
		int minInd(int l, int r) {
			l += n; r += n;
			int mi = -1, mv = INF;
			while (l <= r) {
				if ((l & 1) == 1)
					if (mv > a[l]) {
						mv = a[l];
						mi = b[l];
					}
				if ((r & 1) == 0)
					if (mv > a[r]) {
						mv = a[r];
						mi = b[r];
					}
				l = (l + 1) >> 1;
				r = (r - 1) >> 1;
			}
			return mi;
		}
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
