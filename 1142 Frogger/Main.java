import java.io.*;
import java.util.Locale;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	void run() throws IOException {
		int[] x = new int [200];
		int[] y = new int [200];
		Edge[] e = new Edge [199 * 100];
		DSF dsf = new DSF();
		for (int i = 0; i < 199 * 100; i++)
			e[i] = new Edge();
		PrintWriter out = new PrintWriter(System.out);
		for (int t = 1, N = nextInt(); N > 0; N = nextInt()) {
			int k = 0;
			for (int i = 0; i < N; i++) {
				x[i] = nextInt();
				y[i] = nextInt();
				for (int j = 0; j < i; j++)
					e[k++].update(i, j, sqr(x[i] - x[j]) + sqr(y[i] - y[j]));
			}
			sort(e, 0, k);
			dsf.update(N);
			out.println("Scenario #" + t++);
			out.print("Frog Distance = ");
			for (int i = 0; i < k; i++) {
				dsf.union(e[i].u, e[i].v);
				if (dsf.setOf(0) == dsf.setOf(1)) {
					out.printf(Locale.US, "%.3f%n", Math.sqrt(e[i].w));
					break;
				}
			}
			out.println();
		}
		
		out.close();
	}

	int sqr(int x) {
		return x * x;
	}

	class Edge implements Comparable<Edge> {
		int u = 0;
		int v = 0;
		int w = 0;
		
		void update(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge e) {
			return w - e.w;
		}
	}
	
	class DSF {
		int[] set = new int [200];
		int[] rnk = new int [200];
		
		void update(int sz) {
			fill(rnk, 0, sz, 0);
			for (int i = 0; i < sz; i++)
				set[i] = i;
		}
		
		int setOf(int x) {
			return x == set[x] ? x : (set[x] = setOf(set[x]));
		}
		
		boolean union(int i, int j) {
			i = setOf(i);
			j = setOf(j);
			if (i == j)
				return false;
			if (rnk[i] > rnk[j]) {
				set[j] = i;
			} else {
				set[i] = j;
				rnk[j] += rnk[i] == rnk[j] ? 1 : 0;
			}
			return true;
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
