import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int[] a = new int [500000 + 1];
		boolean[] set = new boolean [3012500 + 1];		
		a[0] = 0;
		for (int i = 1; i <= 500000; i++) {
			int x = a[i - 1] - i;
			if (x <= 0 || set[x])
				x = x + (i << 1);
			set[a[i] = x] = true;
		}
		PrintWriter out = new PrintWriter(System.out);
		for (int x = nextInt(); x >= 0; x = nextInt())
			out.println(a[x]);
		out.close();
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
