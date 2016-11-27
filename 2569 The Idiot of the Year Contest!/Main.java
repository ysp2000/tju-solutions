import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(); T --> 0;) {
			BigInteger f = BigInteger.ONE;
			for (int K = nextInt(); K > 0; K--)
				f = f.multiply(BigInteger.valueOf(K));
			int d = nextInt() + '0', a = 0;
			for (char c : f.toString().toCharArray())
				if (c == d)
					a++;
			out.println(a);
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
