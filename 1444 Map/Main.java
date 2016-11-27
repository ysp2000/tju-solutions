import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
	double EPS = 1e-10;
	double SQRT2 = sqrt(2.0);

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
	
	String[] dirs = { "southwest", "northwest", "southeast", "northeast" };
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		boolean blank = false;
		for (double r = nextDouble(); r > 0; r = nextDouble()) {
			if (blank)
				out.println();
			blank = true;
			double x1 = nextDouble();
			double y1 = nextDouble();
			double x2 = nextDouble();
			double y2 = nextDouble();
			double dx = x2 - x1;
			double dy = y2 - y1;
			
			if (abs(dx) < EPS || abs(dy) < EPS) {
				move(dx, dy);
			} else {
				double dd = min(abs(dx), abs(dy));
				double nx = x1 + signum(dx) * dd;
				double ny = y1 + signum(dy) * dd;
				
				if (abs(x2 - nx) < EPS && abs(y2 - ny) < EPS) {
					print(dd * SQRT2, dirs[index(dx, dy)], null);
				} else if (nx * nx + ny * ny < r * r) {
					print(dd * SQRT2, dirs[index(dx, dy)], null);
					move(x2- nx, y2 - ny);
				} else {
					move(x2- nx, y2 - ny);
					print(dd * SQRT2, dirs[index(dx, dy)], null);
				}
			}
		}
		
		out.close();
	}
	
	void move(double dx, double dy) {
		if (abs(dx) < EPS)
			print(dy, "north", "south");
		else
			print(dx, "east", "west");
	}
	
	void print(double delta, String forward, String back) {
		out.println((delta > 0.0 ? forward : back) + " " + String.format(Locale.US, "%.10f", abs(delta)));
	}
	
	int index(double dx, double dy) {
		return 2 * (dx > 0 ? 1 : 0) + (dy > 0 ? 1 : 0);
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
