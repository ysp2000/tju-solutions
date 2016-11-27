import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok;
		int[] a = new int [50];
		long INF = Long.MAX_VALUE >> 1;
		
		for (String s = in.readLine(); s.charAt(0) != '0'; s = in.readLine()) {
			tok = new StringTokenizer(s);
			int n = Integer.parseInt(tok.nextToken());
			int T = Integer.parseInt(tok.nextToken());
			for (int i = 0; i < n; i++)
				a[i] = Integer.parseInt(in.readLine());
			for (int t = 0; t < T; t++) {
				long need = Long.parseLong(in.readLine());
				long min = -INF;
				long max = INF;
				for (int i = 0; i < n; i++) {
					for (int j = i + 1; j < n; j++) {
						long lj = (a[i] * a[j]) / gcd(a[i], a[j]);
						for (int k = j + 1; k < n; k++) {
							long lk = (lj * a[k]) / gcd(lj, a[k]);
							for (int l = k + 1; l < n; l++) {
								long lcm = (lk * a[l]) / gcd(lk, a[l]);
								long rest = need % lcm;
								long cmin = need - rest;
								long cmax = cmin + ((rest == 0) ? (0) : (lcm));
								min = max(min, cmin);
								if (cmax >= need)
									max = min(max, cmax);
							}
						}
					}
				}
				
				System.out.println(min + " " + max);
			}
		}
	}
	
	static long gcd(long a, long b) {
		while (a > 0 && b > 0)
			if (a > b)
				a %= b;
			else
				b %= a;
		return a + b;
	}
}
