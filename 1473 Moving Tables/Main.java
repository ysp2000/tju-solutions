import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	void run() throws IOException {
		Interval[] its = new Interval [200];
		int[] u = new int [200];
		for (int i = 0; i < 200; i++)
			its[i] = new Interval();
		int tick = 0;
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(); T --> 0;) {
			int N = nextInt();
			for (int i = 0; i < N; i++)
				its[i].set((nextInt() - 1) >> 1, (nextInt() - 1) >> 1);
			Arrays.sort(its, 0, N);
			tick++;
			int ans = 0;
			for (int k = 0; k < N; ans++)
				for (int t = -1, i = 0; i < N; i++)
					if (u[i] != tick && t < its[i].s) {
						u[i] = tick;
						t = its[i].t;
						k++;
					}
			out.println(ans * 10);
		}
		out.close();
	}
	
	class Interval implements Comparable<Interval> {
		int s;
		int t;
		
		void set(int s, int t) {
			this.s = Math.min(s, t);
			this.t = Math.max(s, t);
		}

		@Override
		public int compareTo(Interval i) {
			return s == i.s ? (t - i.t) : s - i.s;
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
