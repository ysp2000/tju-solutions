import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int INF = Integer.MAX_VALUE / 2;

	int N;
	Enumerator e1;
	Enumerator e2;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		N = Integer.parseInt(in.readLine());
		int[] a = new int [N];
		int[] b = new int [N];
		int[] c = new int [N];
		int[] d = new int [N];
		for (int i = 0; i < N; i++) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			a[i] = Integer.parseInt(tok.nextToken());
			b[i] = Integer.parseInt(tok.nextToken());
			c[i] = -Integer.parseInt(tok.nextToken());
			d[i] = -Integer.parseInt(tok.nextToken());
		}
		e1 = new Enumerator(a, b);
		e2 = new Enumerator(c, d);
		
		long ans = 0L;
		while (true) {
			int cur1 = e1.rmq.val[1]; if (cur1 == INF) break;
			int cur2 = e2.rmq.val[1]; if (cur2 == INF) break;
			if (cur1 < cur2) {
				e1.next();
			} else if (cur1 != cur2) {
				e2.next();
			} else {
				int cnt1 = 0;
				int cnt2 = 0;
				while (e1.rmq.val[1] == cur1) {
					cnt1++;
					e1.next();
				}
				while (e2.rmq.val[1] == cur2) {
					cnt2++;
					e2.next();
				}
				ans += cnt1 * (long) cnt2;
			}
		}
		
		out.println(ans);
		out.close();
	}
	
	class Enumerator {
		int[] a;
		int[] b;
		int[] pnt;
		RMQ rmq;
		
		Enumerator(int[] a, int[] b) {
			Arrays.sort(a);
			Arrays.sort(b);
			this.a = a;
			this.b = b;
			pnt = new int [N];
			rmq = new RMQ(N);
			for (int i = 0; i < N; i++)
				rmq.val[i + rmq.n] = a[i] + b[0];
			rmq.build();
		}
		
		void next() {
			if (rmq.val[1] != INF) {
				int i = rmq.ind[1];
				if (pnt[i] < N - 1) {
					int old = b[pnt[i]++];
					int cur = b[pnt[i]];
					rmq.set(i, rmq.val[i + rmq.n] + cur - old);
				} else {
					rmq.set(i, INF);
				}
			}
		}
	}
	
	class RMQ {
		int n;
		int[] val;
		int[] ind;
		
		RMQ(int n) {
			this.n = n;
			this.val = new int [2 * n];
			this.ind = new int [2 * n];
			for (int i = 0; i < n; i++)
				ind[i + n] = i;
		}
		
		void build() {
			for (int v = n - 1; v > 0; v--) {
				int l = (v << 1);
				int r = l + 1;
				if (val[l] < val[r]) {
					val[v] = val[l];
					ind[v] = ind[l];
				} else {
					val[v] = val[r];
					ind[v] = ind[r];
				}
			}
		}

		void set(int i, int nv) {
			val[i += n] = nv;
			for (int v = i >>= 1; v > 0; v >>= 1) {
				int l = (v << 1);
				int r = l + 1;
				if (val[l] < val[r]) {
					val[v] = val[l];
					ind[v] = ind[l];
				} else {
					val[v] = val[r];
					ind[v] = ind[r];
				}
			}
		}
	}
}
