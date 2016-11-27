import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int i = 0; i < segments.length; i++) segments[i] = new Line();
		for (int i = 0; i < events.length; i++) events[i] = new Event();
		for (int testNum = Integer.parseInt(in.readLine()), testCase = 1; testCase <= testNum; testCase++)
			solveCase(testCase);
		out.close();
	}
	
	int MAXN = 100000;

	int segNum;
	int evntNum;
	
	Line[] segments = new Line [MAXN];
	Event[] events = new Event [2 * MAXN];
	
	long ans;
	
	void solveCase(int testCase) throws IOException {
		int segNum = Integer.parseInt(in.readLine());
		for (int seg = 0; seg < segNum; seg++) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			segments[seg].set(Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()));
		}
		sort(segments, 0, segNum);
		evntNum = 0;
		Line base = segments[0];
		ans = 0L;
		for (int seg = 0; seg < segNum; seg++) {
			Line cs = segments[seg];
			if (base.A == cs.A && base.B == cs.B && base.C == cs.C) {
				events[evntNum++].set( 1, cs.A == 0 ? min(cs.p1.x, cs.p2.x) : min(cs.p1.y, cs.p2.y));
				events[evntNum++].set(-1, cs.A == 0 ? max(cs.p1.x, cs.p2.x) : max(cs.p1.y, cs.p2.y));
			} else {
				count();
				evntNum = 0;
				base = segments[seg--];
			}
		}
		count();
		out.println("Scenario #" + testCase + ":\n" + ans + "\n");
	}
	
	void count() {
		int bal = 0;
		sort(events, 0, evntNum);
		for (int en = 0; en < evntNum; en++) {
			Event e = events[en];
			if (e.type == 1)
				ans += bal;
			bal += e.type;
		}
	}

	int gcd(int a, int b) {
		a = abs(a);
		b = abs(b);
		while (a > 0 && b > 0)
			if (a > b)
				a %= b;
			else
				b %= a;
		return a + b;
	}
	
	class Point {
		int x;
		int y;
		
		void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	class Line implements Comparable<Line> {
		Point p1 = new Point();
		Point p2 = new Point();
		int A;
		int B;
		int C;
		
		void set(int x1, int y1, int x2, int y2) {
			p1.set(x1, y1);
			p2.set(x2, y2);
			A = p2.y - p1.y;
			B = p1.x - p2.x;
			
			int gcd = gcd(A, B);
			A /= gcd;
			B /= gcd;
			C = -(A * p1.x + B * p1.y);
			
			if (A < 0 || A == 0 && (B < 0 || B == 0 && C < 0)) {
				A = -A;
				B = -B;
				C = -C;
			}
		}

		@Override
		public int compareTo(Line l) {
			if (A != l.A) return A < l.A ? -1 : 1;
			if (B != l.B) return B < l.B ? -1 : 1;
			if (C != l.C) return C < l.C ? -1 : 1;
			return 0;
		}
	}
	
	class Event implements Comparable<Event> {
		int type;
		int time;

		void set(int type, int time) {
			this.type = type;
			this.time = time;
		}

		@Override
		public int compareTo(Event e) {
			if (time != e.time) return time < e.time ? -1 : 1;
			if (type != e.type) return type < e.type ? -1 : 1;
			return 0;
		}
	}
}
