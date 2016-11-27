import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		int IT = 25;
		int D = nextInt();
		int N = nextInt();
		
		int len = len(N);
		int p10 = pow(10, D);
		for (int p = p10, rlen = D; len - rlen >= D; p *= 10, rlen++)
			if (BigInteger.valueOf(N / p).isProbablePrime(IT))
				for (int i = Math.max(N % p, cmp[rlen - 1]); i < p; i++) {
					if (BigInteger.valueOf(i).isProbablePrime(IT)) {
						System.out.println(N - N % p + i);
						return;
					}
				}
		long min = Long.MAX_VALUE;
		for (int p = p10, rlen = D; len - rlen >= D; p *= 10, rlen++)
			for (int up = cmp[len - rlen], i = Math.max(N / p, cmp[len - rlen - 1]); i < up; i++)
				if (BigInteger.valueOf(i).isProbablePrime(IT)) {
					min = Math.min(min, i * (long) p + prime[rlen]);
					break;
				}
		if (min == Long.MAX_VALUE)
			for (int p = p10, rlen = D; rlen <= 9; p *= 10, rlen++)
				min = Math.min(min, prime[rlen] + (long) prime[Math.max(D, len - rlen + 1)] * p);
		System.out.println(min);
	}

	static int[] prime = { 0, 2, 11, 101, 1009, 10007, 100003, 1000003, 10000019, 100000007, 1000000007 };
	static int[] cmp = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
	
	static int len(int x) {
		for (int i = 1; i <= 9; i++)
			if (x < cmp[i])
				return i;
		return 10;
	}

	static int pow(int b, int p) {
		int res = 1;
		for (; p > 0; p >>= 1) {
			if ((p & 1) == 1)
				res *= b;
			b *= b;
		}
		return res;
	}

	static int nextInt() throws IOException {
		int ch, res = 0;
		for (ch = System.in.read(); ch < '0' || '9' < ch; ch = System.in.read());
		for (; '0' <= ch && ch <= '9'; ch = System.in.read())
			res = 10 * res + ch - '0';
		return res;
	}
}
