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
			int w1 = nextInt();
			int l1 = nextInt();
			int w2 = nextInt();
			int l2 = nextInt();
			int w3 = nextInt();
			int l3 = nextInt();
			if (w1 + l1 + w2 + l2 + w3 + l3 == 0)
				break;
			out.print("Anna's won-loss record is ");
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
