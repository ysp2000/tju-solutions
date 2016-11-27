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

	int MAX = 1 << 12;
	int DEF = -1;
	int MASK = (1 << 5) - 1;
	int S;
	int D;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		while (in.ready()) {
			S = nextInt();
			D = nextInt();
			int ans = DEF;
			for (int mask = 0; mask < MAX; mask++)
				ans = Math.max(ans, calc(mask));
			out.println(ans > DEF ? ans : "Deficit");
		}
		
		out.close();
	}
	
	int calc(int mask) {
		int ret = sum(mask, 12);
		for (int i = 0; i < 8; i++, mask >>= 1)
			if (sum(mask & MASK, 5) > DEF)
				return Integer.MIN_VALUE;
		return ret;
	}

	int sum(int mask, int total) {
		int bc = Integer.bitCount(mask);
		return bc * (S + D) - D * total;
	}

	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
