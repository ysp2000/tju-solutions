import java.io.*;

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

	double EPS = 1e-7;
	Point[] p = new Point [1000];
	int[] u = new int [1000];
	double DD;
	int tick = 0;
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (int i = 0; i < 1000; i++)
			p[i] = new Point();
		for (int t = 1;;) {
			int N = readUnsignedInt();
			int D = readUnsignedInt();
			DD = D * D;
			int ans = 0;
			if (N == 0)
				break;
			for (int y, i = 0; i < N; i++) {
				p[i].set(readInt(), y = readInt());
				if (y > D)
					ans = -1;
			}
			if (ans != -1) {
				sort(p, 0, N);
				tick++;
				for (int i = 0; i < N; i++) {
					if (u[i] != tick) {
						double x = p[i].mx;
						ans++;
						u[i] = tick;
						for (int j = i + 1; j < N; j++)
							if (sqr(x - p[j].x) + p[j].yy < DD + EPS)
								u[j] = tick;
					}
				}
			}
			out.print("Case ");
			out.print(t++);
			out.print(": ");
			out.println(ans);
		}
		out.close();
	}
	
	double sqr(double x) {
		return x * x;
	}

	class Point implements Comparable<Point> {
		int x;
		int y;
		double yy;
		double mx;
		
		void set(int x, int y) {
			this.x = x;
			this.y = y;
			mx = x + sqrt(DD - (yy = y * y));
		}
		
		@Override
		public int compareTo(Point p) {
			return mx < p.mx ? -1 : 1;
		}
	}
	
	static int readInt() throws IOException {
		int n, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return s * n;
 	}
	
	static int readUnsignedInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
