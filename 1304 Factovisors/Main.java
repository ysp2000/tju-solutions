import java.io.*;
import java.util.*;

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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		while (!EOF()) {
			int n = nextInt();
			int m = nextInt();
			out.print(m);
			out.print(m > 0 && solve(n, m) ? " divides " : " does not divide ");
			out.print(n);
			out.println('!');
		}
		
		out.close();
	}
	
	boolean solve(long n, long m) {
		long t = m;
		for (long i = 2L; i * i <= t; i++) {
			if (t % i == 0L) {
				int deg = 0;
				while (t % i == 0L) {
					t /= i;
					deg++;
				}
				if (deg > deg(n, i))
					return false;
			}
		}
		return t == 1L || deg(n, t) > 0;
	}

	int deg(long n, long p) {
		int ret = 0;
		while (n > 0) {
			ret += n / p;
			n /= p;
		}
		return ret;
	}

	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
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
