import java.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (;;) {
			int a = nextInt();
			int b = nextInt();
			if (a == 0)
				break;
			int cnt = 0;
			while (a > 0 && b > 0)
				if (a > b) {
					cnt += a / b;
					a %= b;
				} else {
					cnt += b / a;
					b %= a;
				}
			out.println(((a + b) & 1) == 0 ? "Stan" : "Olie");
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
