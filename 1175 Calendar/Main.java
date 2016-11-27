import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	String[] ds = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
	int[] ml = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (int N = nextInt(); N >= 0; N = nextInt()) {
			int y = 2000;
			int m = 1;
			int d = N + 1;
			boolean leap;
			for (;;) {
				leap = leap(y);
				if (leap && d <= 366 || !leap && d <= 365)
					break;
				d -= leap ? 366 : 365;
				y++;
			}
			leap = leap(y);
			for (; d > ml[m] + (m == 2 && leap ? 1 : 0); d -= ml[m] + (m == 2 && leap ? 1 : 0), m++);
			out.print(y);
			out.print('-');
			if (m < 10)
				out.print('0');
			out.print(m);
			out.print('-');
			if (d < 10)
				out.print('0');
			out.print(d);
			out.print(' ');
			out.println(ds[(N + 5) % 7]);
		}
		out.close();
	}

	boolean leap(int y) {
		return y % 4 == 0 ? (y % 100 == 0 ? (y % 400 == 0 ? true : false) : true) : false;
	}

	static int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return s * n;
 	}
}
