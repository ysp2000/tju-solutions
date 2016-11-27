import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		int MAXN = 500000;
		int PERIOD = 20000;
		boolean[] erat = new boolean [MAXN + 1];
		long[] primes = new long [41538];
		int psz = 0;
		primes[psz++] = 2;
		for (int i = 4; i <= MAXN; i += 2)
			erat[i] = true;
		boolean ok = true;
		for (int i = 3; i <= MAXN; i += 2)
			if (!erat[i]) {
				primes[psz++] = i;
				if (ok)
					if (i * i <= MAXN)
						for (int j = i * i; j <= MAXN; j += i)
							erat[j] = true;
					else
						ok = false;
			}
		PrintWriter out = new PrintWriter(System.out);
		lp: for (long N = nextLong(); N > 0L; N = nextLong()) {
			if (BigInteger.valueOf(N).isProbablePrime(12)) {
				out.print("    ");
				out.println(N);
			} else {
				for (int i = 0; i < 2500 && N > 1L; i++) {
					long p = primes[i];
					if (p * p > N) {
						if (N > 1L) {
							out.print("    ");
							out.println(N);
						}
						continue lp;
					}
					while (N % p == 0L) {
						N /= p;
						out.print("    ");
						out.println(p);
					}
				}
				if (N > 1L) {
					if (BigInteger.valueOf(N).isProbablePrime(12)) {
						out.print("    ");
						out.println(N);
						continue lp;
					} else {
						for (int i = 2000; i < psz && N > 1L; i++) {
							long p = primes[i];
							if (p * p > N) {
								if (N > 1L) {
									out.print("    ");
									out.println(N);
								}
								continue lp;
							}
							while (N % p == 0L) {
								N /= p;
								out.print("    ");
								out.println(p);
							}
						}
					}
					if (N > 1L) {
						if (BigInteger.valueOf(N).isProbablePrime(12)) {
							out.print("    ");
							out.println(N);
							continue lp;
						} else {
							int cnt = 0;
							for (long p = primes[psz - 1]; N > 1L; p += 2L) {
								if (p * p > N) {
									if (N > 1L) {
										out.print("    ");
										out.println(N);
									}
									continue lp;
								}
								while (N % p == 0L) {
									N /= p;
									out.print("    ");
									out.println(p);
								}
								if (++cnt == PERIOD) {
									if (BigInteger.valueOf(N).isProbablePrime(12)) {
										out.print("    ");
										out.println(N);
										continue lp;
									}
									cnt = 0;
								}
							}
							if (N > 1L && BigInteger.valueOf(N).isProbablePrime(12)) {
								out.print("    ");
								out.println(N);
								continue lp;
							}
						}
					}
				}
			}
			out.println();
		}
		out.close();
	}

	static long nextLong() throws IOException {
		long n, c, s = 1L;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == '-')
				s = -1L;
		for (n = 0L; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10L + c - '0';
		return s * n;
 	}
}
