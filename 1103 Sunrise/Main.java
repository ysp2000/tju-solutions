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

	double EPS = 1e-6;
	double Re = 3950.0;
	double Rs = 432000.0;
	double Ds = 92900000.0;
	double K = 2 * PI / (24 * 60 * 60);
	
	double alpha = atan(Rs / Ds);
	double beta = acos(Re / sqrt(Ds * Ds + Rs * Rs));
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		Point2D Su = new Point2D(Ds, Rs).turn(-(beta + alpha));
		Point2D Sd = new Point2D(Ds, -Rs).turn(-(beta + alpha));
		Line2D sun = new Line2D(new Point2D(), new Point2D());
		Point2D its = new Point2D();
		
		while (in.ready()) {
			double gamma = K * Double.parseDouble(in.readLine());
			if (gamma > 2.0 * alpha)
				out.println("1.000000");
			else {
				sun.set(Sd.rotate(gamma), Su.rotate(gamma));
				its.set(Re, sun.A * (sun.p1.x - Re) / sun.B + sun.p1.y);
				double ans = factor(min(2.0, max(0.0, dist(sun.p1, its) / Rs)));
				out.println(ans < EPS ? "0.0" : ans);
			}
		}
		
		out.close();
	}
	
	double factor(double k) {
		if (k > 1.0)
			return 1.0 - factor(2.0 - k);
		double h = 1.0 - k;
		return 1.0 - (acos(h) - h * sqrt(1.0 - h * h)) / PI;
	}

	double sqr(double x) {
		return x * x;
	}
	
	double dist(Point2D p1, Point2D p2) {
		return sqrt(sqr(p2.x - p1.x) + sqr(p2.y - p1.y));
	}
	
	double sqrt(double x) {
		return Math.sqrt(max(0.0, x));
	}
	
	double acos(double x) {
		return Math.acos(max(-1.0, min(1.0, x)));
	}
	
	class Point2D {
		double x;
		double y;
		
		Point2D() {
			x = y = 0.0;
		}
		
		Point2D(double x, double y) {
			this.set(x, y);
		}
		
		Point2D set(double x, double y) {
			this.x = x;
			this.y = y;
			return this;
		}
		
		Point2D copy() {
			return new Point2D(x, y);
		}

		Point2D rotate(double angle) {
			double cos = cos(angle);
			double sin = sin(angle);
			return rotate(cos, sin);
		}
		
		Point2D rotate(double cos, double sin) {
			return new Point2D(x * cos - y * sin, x * sin + y * cos);
		}
		
		Point2D turn(double angle) {
			double cos = cos(angle);
			double sin = sin(angle);
			return turn(cos, sin);
		}

		Point2D turn(double cos, double sin) {
			return set(x * cos - y * sin, x * sin + y * cos);
		}
	}
	
	class Line2D {
		Point2D p1;
		Point2D p2;
		double A;
		double B;
		double C;
		
		Line2D(Point2D p1, Point2D p2) {
			this.set(p1.copy(), p2.copy());
		}
		
		Line2D set(Point2D p1, Point2D p2) {
			this.p1 = p1;
			this.p2 = p2;
			A = p2.y - p1.y;
			B = p1.x - p2.x;
			C = -(A * p1.x + B * p1.y);
			return this;
		}
	}
}
