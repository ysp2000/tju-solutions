import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;

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
	
	int[][] a = new int [50][50];
	int[] o = new int [50];
	int[] f = new int [50 * 50];
	int[] p = new int [50 * 50];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(), t = 0; t < T; t++) {
			int H = nextInt();
			int L = nextInt();
			int C = 0;
			
			fill(o, 0, H, 0);
			
			for (int i = 0; i < H; i++)
				for (int j = 0; j < L; j++) {
					a[i][j] = nextInt() - 1;
					if (a[i][j] != -2) {
						C = max(C, a[i][j] + 1);
						f[a[i][j]] = i;
						p[a[i][j]] = j;
					}
				}
			
			int ans = 0;
			
			for (int c = 0; c < C; c++) {
				ans += (f[c] << 2);
				int tp = p[c];
				int cp = o[f[c]];
				ans += min(abs(tp - cp), L - abs(tp - cp));
				o[f[c]] = tp;
			}
			
			out.println(ans * 5);
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
}
