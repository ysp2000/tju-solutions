import java.io.*;

import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] a = new int [20];
		int[] b = new int [20];
		
		boolean blank = false;
		PrintWriter out = new PrintWriter(System.out);
		for (int N = nextInt(); N > 0; N = nextInt()) {
			if (blank)
				out.println();
			blank = true;
			for (int i = 0; i < N; i++)
				a[i] = nextInt();
			for (int i = 0; i < N; i++)
				b[i] = nextInt();
			int A = 0;
			int B = 0;
			for (int i = 0; i < N; i++)
				if (abs(a[i] - b[i]) == 1) {
					int k = 1;
					if (min(a[i], b[i]) == 1)
						k = 2;
					if (a[i] < b[i])
						A += k * (a[i] + b[i]);
					else
						B += k * (a[i] + b[i]);
				} else if (abs(a[i] - b[i]) > 1) {
					if (a[i] < b[i])
						B += max(a[i], b[i]);
					else
						A += max(a[i], b[i]);
				}
			out.println("A has " + A + " points. B has " + B + " points.");
		}
		out.close();
	}

	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
