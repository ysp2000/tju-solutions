import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	BufferedReader in;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		Point[] p = new Point [5000];
		for (int i = 0; i < 5000; i++)
			p[i] = new Point();
		List<Integer> lst = new ArrayList<Integer>(5000);
		Comparator cmp = new IntCmp();
		for (int T = nextInt(); T --> 0;) {
			int N = nextInt();
			for (int i = 0; i < N; i++)
				p[i].set(nextInt(), nextInt());
			Arrays.sort(p, 0, N);
			lst.clear();
			int ans = 0;
			for (int i = 0; i < N; i++) {
				Point cp = p[i];
				if (lst.isEmpty()) {
					lst.add(cp.y);
					continue;
				}
				if (cp.y < lst.get(0)) {
					ans++;
					lst.add(0, cp.y);
					continue;
				}
				int ind = Collections.binarySearch(lst, cp.y, cmp);
				if (ind < 0)
					ind = -(ind + 1);
				lst.set(--ind, cp.y);
			}
			out.println(ans + 1);
		}
		out.close();
	}
	
	class Point implements Comparable<Point> {
		int x;
		int y;
		
		void set(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point p) {
			return x == p.x ? y - p.y : x - p.x;
		}
	}
	
	class IntCmp implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 <= o2 ? -1 : 1;
		}
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
