import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int sz = 0;
		long[] a = new long [5842];
		long MAXN = 2000000000;
		
		for (long p2 = 1; p2 <= MAXN; p2 <<= 1)
			for (long p23 = p2; p23 <= MAXN; p23 *= 3)
				for (long p235 = p23; p235 <= MAXN; p235 *= 5)
					for (long p2357 = p235; p2357 <= MAXN; p2357 *= 7)
						a[sz++] = p2357;
		Arrays.sort(a, 0, sz);
		String[] end = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th"};
		
		for (;;) {
			int n = Integer.parseInt(in.readLine());
			if (n == 0)
				break;
			out.println("The " + n + end[n % 100 <= 13 ? n % 100 : n % 10] + " humble number is " + a[n - 1] + ".");
		}
		out.close();
	}
}
