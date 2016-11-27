import java.io.*;
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
	
	int MOD;
	int[] inv = new int [30000];
	int N;
	int[][] a = new int [70][70 + 1];
	int[] x = new int [70];
	int[] px = new int [70 + 1];
	int[] py = new int [70 + 1];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 0; test < TESTNUM; test++) {
			MOD = nextInt();
			buildMatrix(nextToken().toCharArray()); 
			genInv();
			gause();
			
			out.print(x[0]);
			
			for (int i = 1; i < N; i++) {
				out.print(" " + x[i]);
			}
			
			out.println();
		}
		
		out.close();
	}
	
	void gause() {
		for (int i = 0; i <= N; i++) {
			px[i] = py[i] = i;
		}

		for (int i = 0; i < N - 1; i++) {
			swap: for (int cx = i; cx < N; cx++) {
				for (int cy = i; cy < N; cy++) {
					if (a[py[cy]][px[cx]] != 0) {
						int t = px[i];
						px[i] = px[cx];
						px[cx] = t;
						
						t = py[i];
						py[i] = py[cy];
						py[cy] = t;
						
						break swap;
					}
				}
			}
		
			for (int cy = i; cy < N; cy++) {
				int m = inv[a[py[cy]][px[i]]];
				
				for (int cx = i; cx <= N; cx++) {
					a[py[cy]][px[cx]] = (a[py[cy]][px[cx]] * m) % MOD;
				}
			}
			
			for (int cy = i + 1; cy < N; cy++) {
				for (int cx = i; cx <= N; cx++) {
					a[py[cy]][px[cx]] = (a[py[cy]][px[cx]] - a[py[i]][px[cx]] + MOD) % MOD;
				}
			}
		}

		for (int i  = N - 1; i >= 0; i--) {
			int rv = a[py[i]][N];
			int m = inv[a[py[i]][px[i]]];
			
			for (int cx = 0; cx < N; cx++) {
				if (cx != i) {
					rv = (rv - (a[py[i]][px[cx]] * x[px[cx]]) % MOD + MOD) % MOD;
				}
				
			}

			x[px[i]] = (m * rv) % MOD;
		}
	}

	void buildMatrix(char[] s) {
		N = s.length;
		
		for (int i = 0; i < N; i++) {
			a[i][N] = (s[i] == '*') ? 0 : (s[i] - 'a' + 1);
			
			int k = 1;
			
			for (int j = 0; j < N; j++) {
				a[i][j] = k;
				k = (k * (i + 1)) % MOD;
			}
		}
	}

	void genInv() {
		inv[1] = 1;
		
		for (int i = 2; i < MOD; i++) {
			inv[i] = fastPow(i, MOD - 2);
		}
	}
	
	int fastPow(int base, int pow) {
		int res = 1;
		
		while (pow > 0) {
			if ((pow & 1) == 1) {
				res = (res * base) % MOD;
			}
			
			base = (base * base) % MOD;
			pow >>= 1;
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
