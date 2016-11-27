import java.io.*;
import java.util.*;
import static java.util.Arrays.fill;

/* NWERC 2004 */
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
	
	int[] val = new int [256];
	int N;
	int[] a = new int [26];
	int[] e = new int [26];
	boolean[][] g = new boolean [26][26];
	
	void run() throws IOException {
		val['2'] = 0;
		val['3'] = 1;
		val['4'] = 2;
		val['5'] = 3;
		val['6'] = 4;
		val['7'] = 5;
		val['8'] = 6;
		val['9'] = 7;
		val['T'] = 8;
		val['J'] = 9;
		val['Q'] = 10;
		val['K'] = 11;
		val['A'] = 12;
		val['C'] = 0;
		val['D'] = 1;
		val['S'] = 2;
		val['H'] = 3;
		
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 0; test < TESTNUM; test++) {
			N = nextInt();

			for (int i = 0; i < N; i++) {
				a[i] = getCard(nextToken());
			}
			
			for (int i = 0; i < N; i++) {
				e[i] = getCard(nextToken());
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (e[i] > a[j]) {
						g[i][j] = true;
					} else {
						g[i][j] = false;
					}
				}
			}
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					out.print((g[i][j] ? 1 : 0) + " ");
//				}
//				
//				out.println();
//			}
			
			out.println(maxMatching());
		}
		
		out.close();
	}
	
	int getCard(String s) {
//		System.out.println(s + " = " + (val[s.charAt(0)] + 13 * val[s.charAt(1)]));
		return 4 * val[s.charAt(0)] + val[s.charAt(1)];
	}
	
	int[] xm = new int [26];
	int[] ym = new int [26];
	int[] ok = new int [26];
	int tick = 1;
	
	int maxMatching() {
		fill(xm, 0, N, -1);
		fill(ym, 0, N, -1);
		
		int res = 0;
		
		for (int x = 0; x < N; x++) {
			if (xm[x] == -1) {
				proc(x);
				tick++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (xm[i] != -1) {
				res++;
			}
		}
		
		return res;
	}

	boolean proc(int x) {
		if (ok[x] == tick) {
			return false;
		}
		
		ok[x] = tick;
		
		for (int y = 0; y < N; y++) {
			if (g[x][y] && (ym[y] == -1 || proc(ym[y]))) {
				xm[x] = y;
				ym[y] = x;
				return true;
			}
		}
		
		return false;
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
