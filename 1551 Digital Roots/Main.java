import java.io.*;
import java.math.BigInteger;
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
		
//		for (long N = nextLong(); N > 0; N = nextLong()) {
//			if ((N %= 9) == 0)
//				N = 9;
//			out.println(N);
//		}
		
		BigInteger NINE = BigInteger.valueOf(9L);
		
		for (;;) {
			BigInteger N = new BigInteger(nextToken());
			if (N.equals(BigInteger.ZERO))
				break;
			int r = N.remainder(NINE).intValue();
			out.println(r == 0 ? 9 : r);
		}
		
		out.close();
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
}
