import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int T = nextInt(), ans, M, H; T --> 0; out.println(ans))
			for (ans = 0, M = nextInt(), H = nextInt(); H --> 0; ans = (ans + modPow(nextInt(), nextInt(), M)) % M);
		out.close();
	}

	int modPow(int base, int deg, int mod) {
		int ret = 1;
		for (base %= mod; deg > 0; deg >>= 1) {
			if ((deg & 1) == 1)
				ret = (ret * base) % mod;
			base = (base * base) % mod;
		}
		return ret % mod;
	}

	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
