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

	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		boolean[] erat = new boolean [1000000 + 1];
		int psz = 0;
		int[] primes = new int [78498];
		primes[psz++] = 2;
		for (int i = 4; i <= 1000000; i += 2)
			erat[i] = true;
		for (int i = 3; i <= 1000000; i += 2)
			if (!erat[i]) {
				primes[psz++] = i;
				if (i <= 1000)
					for (int j = i * i; j <= 1000000; j += i)
						erat[j] = true;
			}
		for (String s = in.readLine(); s.charAt(0) != '0'; s = in.readLine()) {
			StringTokenizer tok = new StringTokenizer(s);
			char[] k = tok.nextToken().toCharArray();
			int n = k.length, L = Integer.parseInt(tok.nextToken()), ans = -1;
			for (int i = 0; i < psz && primes[i] < L; i++) {
				int K = 0, mod = primes[i], p10 = 1;
				for (int j = n - 1; j >= 0; j--) {
					K = (K + (k[j] - '0') * p10) % mod;
					p10 = p10 * 10 % mod;
				}
				if (K == 0L) {
					ans = primes[i];
					break;
				}
			}
			if (ans == -1)
				out.println("GOOD");
			else {
				out.print("BAD "); out.println(ans);
			}
		}
		out.close();
	}
}
