import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
	double EPS = 1e-5;
	double INF = 1e+50;
	
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		tok = new StringTokenizer(in.readLine());
		
		int test = 1;
		
		for (int lNum = nextInt(); lNum >= 0; lNum = nextInt()) {
			double[] len = readArray(lNum);
			double[] ang = readArray(lNum);
			Point[] points = new Point [lNum];
			
			for (int i = lNum - 1; i >= 0; i--) {
				for (int j = i + 1; j < lNum; j++)
					points[j].z += len[i];
				points[i] = new Point(0.0, 0.0, len[i]);
				double angle = -Math.toRadians(ang[i]);
				for (int j = i; j < lNum; j++)
					points[j] = (i & 1) == 0 ? points[j].roatateXOZ(angle) : points[j].roatateYOZ(angle);
			}

			boolean ok = true;
			int belowFloor = lNum;
			
			for (int i = 0; i < lNum; i++) {
				if (points[i].z < -EPS) {
					belowFloor = i;
					ok = false;
					break;
				}
			}
			
			Line[] links = new Line [lNum];
			int[] nums = new int [lNum];
			int lCnt = 0;
			Point cur = new Point(0.0, 0.0, 0.0);
			
			for (int i = 0; i < lNum; i++) {
				if (!cur.equals(points[i])) {
					links[lCnt] = new Line(cur, points[i]);
					nums[lCnt] = i;
					lCnt++;
					cur = points[i];
				}
			}
			
			int linkCollision = lNum;
			
			lp: for (int i = 0; i < lCnt; i++) {
				for (int j = 0; j < i - 1; j++) {
					if (dist(links[i], links[j]) < EPS) {
						linkCollision = nums[i];
						ok = false;
						break lp;
					}
				}
			}
			
			out.print("Case " + test++ + ": ");
			
			if (ok) {
				out.println("robot's hand is at " + points[lNum - 1]);
			} else {
				out.print("servo " + (min(belowFloor, linkCollision) + 1) + " ");
				if (belowFloor < linkCollision)
					out.println("attempts to move arm below floor");
				else
					out.println("causes link collision");
			}
		}
		
		out.close();
	}
	
	double dist(Line l1, Line l2) {
		double D1 = sqr(l1.l) + sqr(l1.m) + sqr(l1.n);
		double D2 = sqr(l2.l) + sqr(l2.m) + sqr(l2.n);
		
		double A1 = (l1.l * l2.l + l1.m * l2.m + l1.n * l2.n) / D1;
		double A2 = (l1.l * l2.l + l1.m * l2.m + l1.n * l2.n) / D2;
		
		double B1 = (l1.l * l2.x + l1.m * l2.y + l1.n * l2.z - (l1.l * l1.x + l1.m * l1.y + l1.n * l1.z)) / D1;
		double B2 = (l2.l * l1.x + l2.m * l1.y + l2.n * l1.z - (l2.l * l2.x + l2.m * l2.y + l2.n * l2.z)) / D2;
		
		double t = (A1 * B2 + B1) / (1.0 - A1 * A2);
		double p = A2 * t + B2;
		
		return between(0.0, t, 1.0, EPS / dist(l1.p1, l1.p2)) && between(0.0, p, 1.0, EPS / dist(l2.p1, l2.p2)) ? dist(l1.getPoint(t), l2.getPoint(p)) : INF;
	}

	double dist(Point p1, Point p2) {
		return sqrt(sqr(p2.x - p1.x) + sqr(p2.y - p1.y) + sqr(p2.z - p1.z));
	}
	
	double sqr(double x) {
		return x * x;
	}

	double[] readArray(int size) throws IOException {
		double[] ret = new double [size];
		for (int i = 0; i < size; i++)
			ret[i] = nextDouble();
		return ret;
	}

	boolean eq(double a, double b, double eps) {
		return abs(b - a) < EPS;
	}
	
	boolean between(double a, double x, double b, double eps) {
		return a + eps < x && x < b - eps;
	}
	
	class Point {
		double x;
		double y;
		double z;
		
		Point(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		Point add(Point v) {
			return new Point(x + v.x, y + v.y, z + v.z);
		}

		Point subtract(Point v) {
			return new Point(x - v.x, y - v.y, z - v.z);
		}
		
		Point roatateXOZ(double angle) {
			double cos = cos(angle);
			double sin = sin(angle);
			return new Point(x * cos - z * sin, y, x * sin + z * cos);
		}
		
		Point roatateYOZ(double angle) {
			double cos = cos(angle);
			double sin = sin(angle);
			return new Point(x, y * cos + z * sin, -y * sin + z * cos);
		}
		
		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return eq(x, p.x, EPS) && eq(y, p.y, EPS) && eq(z, p.z, EPS);
		}
		
		@Override
		public String toString() {
			return String.format(Locale.US, "(%.3f,%.3f,%.3f)", x, y, z);
		}
	}
	
	class Line {
		Point p1;
		Point p2;
		
		double l;
		double m;
		double n;
		
		double x;
		double y;
		double z;
		
		Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			
			l = p2.x - p1.x;
			m = p2.y - p1.y;
			n = p2.z - p1.z;
			
			x = p1.x;
			y = p1.y;
			z = p1.z;
		}
		
		Point getPoint(double t) {
			return new Point(l * t + x, m * t + y, n * t + z);
		}
	}
	
	String nextToken() throws IOException {
		if (!tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		
		return tok.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}