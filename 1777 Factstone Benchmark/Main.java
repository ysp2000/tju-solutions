import java.io.*;
import static java.lang.Math.*;
import static java.util.Arrays.binarySearch;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		double max = 1 << 23, il = 1.0 / log(2);
		double[] log = new double [481178];
		for (int i = 1;; i++)
			if ((log[i] = log[i - 1] + log(i) * il) > max)
				break;
		PrintWriter out = new PrintWriter(System.out);
		for (int y = nextInt(); y > 0; y = nextInt())
			out.println(-(binarySearch(log, 1 << (2 + (y - 1960) / 10)) + 1) - 1);
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
