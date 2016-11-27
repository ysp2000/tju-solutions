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
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int pNum;
	double[] latitude;
	double[] longitude;
	Point[] ps;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		pNum = nextInt();
		ps = new Point [pNum];
		latitude = new double [pNum];
		longitude = new double [pNum];
		for (int i = 0; i < pNum; i++)
			ps[i] = new Point(toRadians(latitude[i] = nextDouble()), toRadians(longitude[i] = nextDouble()));
		
		int ans = -1;
		double best = 1e100;
		
		for (int i = 0; i < pNum; i++) {
			double cur = -1e100;
			for (int j = 0; j < pNum; j++)
				cur = max(cur, dist2(ps[i], ps[j]));
			if (best > cur) {
				best = cur;
				ans = i;
			}
		}
		
		out.printf(Locale.US, "%.2f %.2f%n", latitude[ans], longitude[ans]);
		out.close();
	}
	
	double dist2(Point p1, Point p2) {
		return sqr(p2.x - p1.x) + sqr(p2.y - p1.y) + sqr(p2.z - p1.z);
	}
	
	double sqr(double x) {
		return x * x;
	}
	
	class Point {
		double x;
		double y;
		double z;
		
		Point(double thetta, double phi) {
			double cost = cos(thetta);
			x = cost * cos(phi);
			y = cost * sin(phi);
			z = sin(thetta);
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
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
