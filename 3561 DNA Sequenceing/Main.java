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
	StringTokenizer st = new StringTokenizer("");
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int N = nextInt();
		int M = nextInt();
		char[][] a = new char [N][];
		char[][] b = new char [M][];
		int[][] ans = new int [N][M];
		boolean ok;
		for (int i = 0; i < N; i++) a[i] = nextToken().toCharArray();
		for (int i = 0; i < M; i++) b[i] = nextToken().toCharArray();
		for (int i = 0; i < N; i++) {
			char[] ai = a[i];
			for (int j = 0; j < M; j++) {
				char[] bj = b[j];
				for (int k = 0; k < N; k++) {
					if (k == i) continue;
					char[] ak = a[k];
					ok = true;
					for (int l = 0; l < a[k].length; l++) {
						if (ak[l] != ai[l] && ak[l] != bj[l]) {
							ok = false;
							break;
						}
					}
					if (ok) ans[i][j]++;
				}
				for (int k = 0; k < M; k++) {
					if (k == j) continue;
					char[] bk = b[k];
					ok = true;
					for (int l = 0; l < bk.length; l++) {
						if (bk[l] != ai[l] && bk[l] != bj[l]) {
							ok = false;
							break;
						}
					}
					if (ok) ans[i][j]++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j > 0) out.print(' ');
				out.print(ans[i][j]);
			}
			out.println();
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
