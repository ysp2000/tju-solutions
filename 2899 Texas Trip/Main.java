import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
	double EPS = 1e-7;
	double INF = 1e+60;
	double TAU = 0.5 * (sqrt(5.0) - 1.0);
	
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int pNum = 0;
	double[] xs = new double [30];
	double[] ys = new double [30];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(), t = 0; t < T; t++) {
			pNum = nextInt();

			for (int i = 0; i < pNum; i++) {
				xs[i] = nextDouble();
				ys[i] = nextDouble();
			}
			
			double a = 0.0 - EPS;
			double b = 0.5 * PI + EPS;
			double x1 = b - TAU * (b - a);
			double x2 = a + TAU * (b - a);
			double y1 = f(x1);
			double y2 = f(x2);
			
			while (abs(a - b) > EPS) {
				if (y1 < y2) {
					b = x2;
					x2 = x1;
					y2 = y1;
					x1 = b - TAU * (b - a);
					y1 = f(x1);
				} else {
					a = x1;
					x1 = x2;
					y1 = y2;
					x2 = a + TAU * (b - a);
					y2 = f(x2);
				}
			}
			
			out.printf(Locale.US, "%.2f%n", f(0.5 * (a + b)));
		}
		
		out.close();
	}
	
	double f(double x) {
		double cos = cos(x);
		double sin = sin(x);
		double left = INF;
		double right = -INF;
		double bottom = INF;
		double top = -INF;
		
		for (int i = 0; i < pNum; i++) {
			double cx = xs[i] * cos - ys[i] * sin;
			double cy = ys[i] * cos + xs[i] * sin;
			
			left = min(left, cx);
			right = max(right, cx);
			bottom = min(bottom, cy);
			top = max(top, cy);
		}
		
		double side = max(right - left, top - bottom);
		return side * side;
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
