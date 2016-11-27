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

	double EPS = 1e-7;
	double INF = 1e+7;
	
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {
		}
		new Thread() {
			public void run() {
				try {
					new Main().run();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");

	private void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int n = nextInt();
		String str = nextToken();
		
		Point[] p = new Point[n];
		
		for (int i = 0; i < n; i++) {
			p[i] = new Point(nextToken().charAt(0), nextDouble(), nextDouble());
		}
		
		ArrayList<Double> x = new ArrayList<Double>();
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				Line l = new Line(p[i], p[j]);
				
				if (!l.colinearOX()) {
					x.add(l.crossOX());
				}
			}
		}
		
		x.add(-INF);
		x.add(INF);
		
		Collections.sort(x);
		
		int sz = x.size();
		
		ArrayList<Pair> ans = new ArrayList<Pair>();
		
		for (int i = 1; i < sz; i++) {
			if (!eq(x.get(i - 1), x.get(i))) {
				double bx = 0.5 * (x.get(i - 1) + x.get(i));
				
				for (int j = 0; j < n; j++) {
					p[j].ang = Math.atan2(p[j].y, p[j].x - bx);
				}
				
				sort(p);
				
				boolean f = true;
				
				for (int j = 0; j < n && f; j++) {
					f = p[j].ch == str.charAt(j);
				}
				
				if (f) {
					ans.add(new Pair(x.get(i - 1), x.get(i)));
				}
			}
		}
		
		sz = ans.size();
		out.println(sz);
		
		if (sz > 0) {
			out.print(ans.get(0));
			
			for (int i = 1; i < sz; i++) {
				out.print(" " + ans.get(i));
			}
		}
		
		in.close();
		out.close();
	}

	boolean eq(double a, double b) {
		return abs(a - b) < EPS;
	}

	boolean inf(double a) {
		return eq(a, INF) || eq(a, -INF);
	}
	
	class Point implements Comparable<Point> {
		char ch;
		double x, y;
		double ang;
		
		Point(char ch, double x, double y) {
			this.ch = ch;
			this.x = x;
			this.y = y;
		}
		
		public int compareTo(Point p) {
			if (ang < p.ang) {
				return 1;
			}
			
			return -1;
		}
	}
	
	class Line {
		Point p1, p2;
		double a, b, c;
		
		Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = -(a * p1.x + b * p1.y);
		}
		
		boolean colinearOX() {
			return eq(a, 0.0);
		}

		double crossOX() {
			return -c / a;
		}
	}
	
	class Pair {
		double a, b;
		
		Pair(double a, double b) {
			this.a = a;
			this.b = b;
		}
		
		public String toString() {
			return (inf(a) ? "*" : a) + " " + (inf(b) ? "*" : b);
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