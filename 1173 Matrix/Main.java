/* Backtracking */
import java.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	int INF = Integer.MAX_VALUE >> 1;
	int N;
	Row[] row = new Row [7];
	int[] pSum = new int [7];
	int[][] sum = new int [7][7];
	int best;
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (int i = 0; i < 7; i++)
			row[i] = new Row();
		for (N = nextInt(); N > 0; N = nextInt()) {
			for (int i = 0; i < N; i++)
				row[i].initialize(N);
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					row[i].set(j, nextInt());
			sort(row, 0, N);
			pSum[N - 1] = 0;
			for (int i = N - 2; i >= 0; i--)
				pSum[i] = pSum[i + 1] + row[i + 1].min;
			best = INF;
			for (int i = 0; i < N; i++)
				sum[0][i] = row[0].v[i];
			dfs(1);
			out.println(best);
		}
		out.close();
	}
	
	void dfs(int r) {
		if (r == N) {
			update();
			return;
		}
		lp: for (int o = 0; o < N; o++) {
			for (int i = 0; i < N; i++) {
				sum[r][i] = sum[r - 1][i] + row[r].v[(i + o) % N];
				if (sum[r][i] + pSum[r] >= best)
					continue lp;
			}
			dfs(r + 1);
		}
	}

	void update() {
		int max = sum[N - 1][0];
		for (int i = 1; i < N; i++)
			max = max(max, sum[N - 1][i]);
		best = min(best, max);
	}

	class Row implements Comparable<Row> {
		int sz;
		int min;
		int max;
		int[] v = new int [7];
		
		void initialize(int sz) {
			this.sz = sz;
			max = -(min = INF);
		}

		void set(int j, int x) {
			v[j] = x;
			min = min(min, x);
			max = max(max, x);
		}

		@Override
		public int compareTo(Row r) {
			return max < r.max ? 1 : -1;
		}
	}

	int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return s * n;
 	}
}
