import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		BigInteger N = new BigInteger(nextToken());
		int P = nextInt();
		BigInteger X = pow(N, P);
		print(X.toString());
		out.close();
	}
	
	void print(String s) {
		int cnt = -1;
		for (int i = 0; i < s.length(); i++) {
			if (++cnt == 70) {
				out.println();
				cnt = 0;
			}
			out.print(s.charAt(i));
		}
		out.println();
	}

	BigInteger pow(BigInteger n, int p) {
		BigInteger res = BigInteger.ONE;
		for (; p > 0; p >>= 1) {
			if ((p & 1) == 1)
				res = res.multiply(n);
			n = n.multiply(n);
		}
		return res;
	}

	String nextToken() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
