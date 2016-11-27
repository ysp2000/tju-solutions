/* naive */

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	BufferedReader in;
	double EPS = 1e-9;
	int MAXP = 50000;
	int pNum;
	int hSize;
	Point[] pts = new Point [50000];
	Point[] hull = new Point [50000];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < MAXP; i++)
			pts[i] = new Point();
		PrintWriter out = new PrintWriter(System.out);
		for (pNum = nextInt(); pNum > 0; pNum = nextInt()) {
			for (int i = 0; i < pNum; i++)
				pts[i].set(nextInt(), nextInt());
			convexHull();
			int ans = largestTriangle(hull, hSize);
			out.print(ans / 2);
			out.println((ans & 1) == 0 ? ".00" : ".50");
		}
		out.close();
	}

	int largestTriangle(Point[] p, int n) {
		int ret = 0;
		for (int p0 = 0, p1 = 1, p2 = 2; p0 < n;) {
			for (;; p1 = (p1 + 1) % n) {
				while (area(p[p0], p[p1], p[p2]) < area(p[p0], p[p1], p[(p2 + 1) % n]) + EPS)
					p2 = (p2 + 1) % n;
				if (area(p[p0], p[p1], p[p2]) > area(p[p0], p[(p1 + 1) % n], p[p2]) + EPS)
					break;
			}
			ret = max(ret, area(p[p0], p[p1], p[p2]));
			p0++;
			if (p1 == p0)
				p1 = (p1 + 1) % n;
			if (p2 == p1)
				p2 = (p2 + 1) % n;
		}
		return ret;
	}
	
	int area(Point p0, Point p1, Point p2) {
		return abs(vec(p0, p1, p2));
	}

	void convexHull() {
		int k = lowestRight();
		for (int i = 0; i < pNum; i++) {
			pts[i].d = dist(pts[k], pts[i]);
			pts[i].a = Math.atan2(pts[i].y - pts[k].y, pts[i].x - pts[k].x);
		}
		pts[k].a = -1.0;
		Arrays.sort(pts, 0, pNum);
		k = -1;
		for (int i = 0; i < pNum; i++) {
			while (k > 0 && vec(hull[k], hull[k - 1], pts[i]) >= 0)
				k--;
			hull[++k] = pts[i];
		}
		hSize = k + 1;
	}

	double dist(Point p1, Point p2) {
		return sqrt(sqr(p2.x - p1.x) + sqr(p2.y - p1.y));
	}

	double sqr(double x) {
		return x * x;
	}
	
	int vec(Point p0, Point p1, Point p2) {
		return vec(p1.x - p0.x, p1.y - p0.y, p2.x - p0.x, p2.y - p0.y);
	}

	int vec(int x1, int x2, int y1, int y2) {
		return x1 * y2 - x2 * y1;
	}

	int lowestRight() {
		int ret = 0;
		for (int i = 1; i < pNum; i++)
			if (pts[ret].y > pts[i].y + EPS || abs(pts[ret].y - pts[i].y) < EPS && pts[ret].x < pts[i].x)
				ret = i;
		return ret;
	}

	class Point implements Comparable<Point> {
		int x;
		int y;
		double d;
		double a;
		
		void set(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point p) {
			return a < p.a - EPS ? -1 : (a > p.a + EPS ? 1 : d < p.d ? -1 : 1);
		}
		
		@Override
		public String toString() {
			return String.format(Locale.US, "(%d, %d)", x, y);
		}
	}
	
	int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return s * n;
 	}
}
