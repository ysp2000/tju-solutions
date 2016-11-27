import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	double EPS = 1e-11;
	
	int MAXV = 1000;
	
	int vNum;
	int lNum;
	Point[] verts = new Point [MAXV];
	Line[] edges = new Line [MAXV];
	
	Point lp1 = new Point();
	Point lp2 = new Point();
	Line line = new Line();
	
	Point[] ps = new Point [MAXV];
	int pNum;
	double ans;
	
	void run() throws IOException {
		for (int i = 0; i < verts.length; i++)
			verts[i] = new Point();
		for (int i = 0; i < edges.length; i++)
			edges[i] = new Line();
		for (int i = 0; i < ps.length; i++)
			ps[i] = new Point();
		
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (;;) {
			vNum = nextInt();
			lNum = nextInt();
			if (vNum == 0 && lNum == 0)
				break;
			for (int i = 0; i < vNum; i++)
				verts[i].set(nextDouble(), nextDouble());
			for (int i = 0; i < vNum; i++)
				edges[i].set(verts[i], verts[(i + 1) % vNum]);
			for (int q = 0; q < lNum; q++) {
				lp1.set(nextDouble(), nextDouble());
				lp2.set(nextDouble(), nextDouble());
				line.set(lp1, lp2);
				pNum = 0;
				for (int i = 0; i < vNum; i++)
					check(edges[i]);
				ans = 0.0;
				sort(ps, 0, pNum);
				for (int i = 1; i < pNum; i += 2)
					ans += dist(ps[i - 1], ps[i]);
				for (int i = 0; i < vNum; i++) {
					int j = (i + 1) % vNum;
					int k = (j + 1) % vNum;
					if (abs(line.calc(edges[j].p1)) < EPS && abs(line.calc(edges[j].p2)) < EPS && edges[i].lower(edges[j].p1) && edges[k].lower(edges[j].p2))
						ans += dist(edges[j].p1, edges[j].p2);
				}
				out.printf(Locale.US, "%.3f%n", ans);
			}
		}
		
		out.close();
	}
	
	Point its = new Point();
	
	void check(Line edge) {
		if (cll(line, edge, its)) {
			if (edge.contains(its) && !edge.lower(its)) {
//				System.out.println(line + " " + edge + " " + its);
				ps[pNum++].set(line.getT(its), its);
			}
		}
	}
	
	double dist(Point p1, Point p2) {
		return sqrt(sqr(p2.x - p1.x) + sqr(p2.y - p1.y));
	}

	double sqr(double x) {
		return x * x;
	}

	boolean cll(Line l1, Line l2, Point ret) {
		double det = -det(l1.A, l1.B, l2.A, l2.B);
		if (abs(det) < EPS)
			return false;
		its.set(det(l1.C, l1.B, l2.C, l2.B) / det, det(l1.A, l1.C, l2.A, l2.C) / det);
		return true;
	}

	double det(double a1, double b1, double a2, double b2) {
		return a1 * b2 - a2 * b1;
	}

	boolean between(double l, double x, double r, double eps) {
		return l - eps <= x && x <= r + eps;
	}
	
	class Point implements Comparable<Point> {
		double t;
		double x;
		double y;
		
		Point() {
		}
		
		void set(double t, Point src) {
			this.t = t;
			this.set(src.x, src.y);
		}

		void set(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return String.format(Locale.US, "(%.2f, %.2f)", x, y);
		}

		@Override
		public int compareTo(Point p) {
			return t < p.t ? -1 : 1;
		}
		
		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return abs(x - p.x) < EPS && abs(y - p.y) < EPS;
		}
	}
	
	class Line {
		Point p1;
		Point p2;
		Point pc = new Point();
		double A;
		double B;
		double C;
		double L;
		
		Line() {
		}
		
		boolean lower(Point p) {
			double v1 = line.calc(p1);
			double vc = line.calc(pc);
			double v2 = line.calc(p2);
			if (v1 + EPS < vc && vc < v2 - EPS)
				return p1.equals(p);
			if (v2 + EPS < vc && vc < v1 - EPS)
				return p2.equals(p);
			return false;
		}

		boolean upper(Point p) {
			double v1 = line.calc(p1);
			double vc = line.calc(pc);
			double v2 = line.calc(p2);
			if (v1 + EPS < vc && vc < v2 - EPS)
				return p2.equals(p);
			if (v2 + EPS < vc && vc < v1 - EPS)
				return p1.equals(p);
			return false;
		}

		double calc(Point p) {
			return A * p.x + B * p.y + C;
		}
		
		boolean contains(Point p) {
			return between(0.0, getT(p), 1.0, EPS / L);
		}

		double getT(Point p) {
			if (abs(B) < EPS)
				return (p.y - p1.y) / A;
			return (p1.x - p.x) / B;
		}

		void set(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			A = p2.y - p1.y;
			B = p1.x - p2.x;
			C = -(A * p1.x + B * p1.y);
			L = sqrt(A * A + B * B);
			pc.set(0.5 * (p1.x + p2.x), 0.5 * (p1.y + p2.y));
		}
		
		@Override
		public String toString() {
			return "[" + p1 + ", " + p2 + "]";
		}
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
