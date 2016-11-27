import java.io.*;

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

	int MOD = 201004;
	
	int SMALL_SQRT = 3163;
	int SMALL = SMALL_SQRT * SMALL_SQRT;
	
	boolean[] erat = new boolean [SMALL + 1];
	int[] primes = new int [664861];
	int pNum = 0;
	
	int[] pd = new int [30];
	int pdsz = 0;
	
	void run() throws IOException {
		for (int i = 2; i <= SMALL; i++) {
			if (!erat[i]) {
				primes[pNum++] = i;
				if (i <= SMALL_SQRT)
					for (int j = i * i; j <= SMALL; j += i)
						erat[j] = true;
			}
		}
		
		PrintWriter out = new PrintWriter(System.out);

		for (;;) {
			pdsz = 0;
			int N = readInt();
			int M = readInt();
			if (N == 0)
				break;
			primeDivisors(N);
			out.println((powMod(N, M - 1) * phi(N)) % MOD);
		}
		
		out.close();
	}
	
	long phi(int n) {
		for (int i = 0; i < pdsz; i++)
			n -= n / pd[i];
		return n;
	}

	long powMod(long n, int m) {
		long res = 1L;
		for (; m > 0; m >>= 1) {
			if ((m & 1) == 1)
				res = (res * n) % MOD;
			n = (n * n) % MOD;
		}
		return res;
	}

	void primeDivisors(int n) {
		for (int i = 0; n > 1 && erat[n] && i < pNum; i++) {
			int p = primes[i];
			if (n % p == 0)
				for (pd[pdsz++] = p; n % p == 0; n /= p);
		}
		if (n > 1)
			pd[pdsz++] = n;
	}

	int readInt() throws IOException {
		int n = 0, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (; '0' <= c && c <= '9'; c = System.in.read())
			n = 10 * n + c - '0';
		return n;
	}
}
