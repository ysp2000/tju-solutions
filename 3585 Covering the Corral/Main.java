import static java.lang.Math.*;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.exit;
import static java.lang.System.arraycopy;
import static java.util.Arrays.sort;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.fill;
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {
		}
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new Main().run();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "1", 1 << 24).run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");

	int C;
	int M;
	Cover[] c;
	
	int[] col;
	int ans = Integer.MAX_VALUE;
	
	private void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);

		C = nextInt();
		M = nextInt();
		c = new Cover [M];
		
		for (int i = 0; i < M; i++) {
			c[i] = new Cover(i, nextInt(), nextInt());
			if (c[i].l == C) {
				out.println(1);
				out.close();
				return;
			}
		}
		
		Event[] e = new Event [5 * M];
		int eNum = 0;

		for (int i = 0; i < M; i++) {
			e[eNum++] = new Event(1, c[i].x, c[i]);
			e[eNum++] = new Event(-1, c[i].e, c[i]);
			e[eNum++] = new Event(0, c[i].e + 1, c[i]);
			Cover nc = new Cover(c[i].id, c[i].x + C, c[i].l);
			e[eNum++] = new Event(1, nc.x, nc);
			e[eNum++] = new Event(-1, nc.e, nc);
		}
		
		sort(e, 0, eNum);
		TreeSet<Cover> setEnd = new TreeSet<Cover>(new CmpEnd());
		
		for (int i = 0; i < eNum; i++) {
			int t = e[i].t;
			
//			System.out.println(t + " " + setEnd);
			
			if (t == -1) {
//				System.out.println("rem " + e[i].c);
				setEnd.remove(e[i].c);
			} else if (t == 0) {
				e[i].c.next = setEnd.last().id;
			} else if (t == 1) {
//				System.out.println("add " + e[i].c);
				setEnd.add(e[i].c);
			}
		}
	
		for (int i = 0; i < M; i++)
			System.out.println(c[i]);
		
		col = new int [M];
		stack = new int [M];
		
		for (int i = 0; i < M; i++) {
			if (col[i] == 0) {
				ssz = 0;
//				System.out.println();
				if (!dfs(i, 0)) continue;
				int xmin = c[stack[ssz - 1]].em;
				int xcur = c[stack[ssz - 1]].x;
				int cur = ssz - 1;
				
				if (xmin > xcur)
					xcur += C;
				
				while (xcur > xmin) {
					int nx = c[stack[--cur]].x;
					if (nx < xcur)
						nx += C;
					xcur = nx;
					System.out.println(xcur + " " + (xmin + C));
				}
				System.out.println(ssz + " " + cur);
				ans = min(ans, ssz - cur);
			}
		}
		
		out.println(ans);
		
		in.close();
		out.close();
	}

	boolean its(Cover c1, Cover c2) {
//		System.out.println(c1 + " " + c2);
		if (c1.isSingle() && c2.isSingle())
			return its(c1.x, c1.e, c2.x, c2.e);
		if (c1.isSingle()) {
			return its(c1.x, c1.e, 0, c2.em) || its(c1.x, c1.e, c2.x, C - 1);
		} else if (c2.isSingle()) {
			return its(0, c1.em, c2.x, c2.e) || its(c1.x, C - 1, c2.x, c2.e);
		}
		return true;
	}
	
	boolean its(int b1, int e1, int b2, int e2) {
		if (b1 > b2) return its(b2, e2, b1, e1);
//		System.out.println(b1 + " " + e1 + " " + b2 + " " + e2);
		return e1 + 1 >= b2;
	}

	int[] stack;
	int ssz;
	
	int dist(int i, int j) {
		return i < j ? j - i : C - (i - j);
	}

	boolean dfs(int v, int k) {
		stack[ssz++] = v;
		col[v] = 1;

		int nxt = c[v].next;
		boolean ret = false;
		
		if (col[nxt] == 0) {
			ret = dfs(nxt, k + 1);
		} else if (col[nxt] == 1) {
			ret = true;
		}
			
		col[v] = 2;
		return ret;
	}

	class CmpEnd implements Comparator<Cover> {
		@Override
		public int compare(Cover o1, Cover o2) {
			if (o1.e == o2.e) {
				if (o1.x == o2.x)
					return o1.id - o2.id;
				return o1.x - o2.x;
			}
			return o1.e < o2.e ? -1 : 1;
		}
	}
	
	class CmpEndMod implements Comparator<Cover> {
		@Override
		public int compare(Cover o1, Cover o2) {
			if (o1.em == o2.em)
				return o1.id - o2.id;
			return o1.em < o2.em ? -1 : 1;
		}
	}

	class Cover implements Comparable<Cover> {
		int id;
		int x;
		int l;
		int e;
		int em;
		int next;
		
		Cover(int id, int x, int l) {
			this.id = id;
			this.x = x;
			this.l = l;
			this.e = x + l - 1;
			this.em = e % C;
		}

		public int compareTo(Cover c) {
			if (e == c.e) {
				if (x == c.x)
					return id - c.id;
				return x - c.x;
			}
			return e < c.e ? -1 : 1;
		}
		
		boolean isSingle() {
			return x <= em;
		}
		
		public String toString() {
			return "[id = " + id + ", x = " + x + ", e = " + e + ", em = " + em + ", next = " + next + "]";
		}
	}
	
	class Event implements Comparable<Event> {
		int t;
		int x;
		Cover c;
		
		Event(int t, int x, Cover c) {
			this.t = t;
			this.x = x;
			this.c = c;
		}

		public int compareTo(Event e) {
			return x == e.x ? (e.t - t) : (x < e.x ? -1 : 1);
		}
	}
	
	void chk(boolean b) {
		if (b)
			return;
		System.out.println(new Error().getStackTrace()[1]);
		exit(999);
	}
	void deb(String fmt, Object... args) {
		System.out.printf(Locale.US, fmt + "%n", args);
	}
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
	boolean EOF() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = in.readLine();
			if (s == null)
				return true;
			st = new StringTokenizer(s);
		}
		return false;
	}
}