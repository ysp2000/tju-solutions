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
	
	int DOWN = 0;
	int EVEN = 1;
	int UP   = 2;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int[] w = new int [12];
		int[][] left = new int [3][];
		int[][] right = new int [3][];
		int[] res = new int [3];
		
		lp: for (int T = nextInt(); T --> 0;) {
			for (int i = 0; i < 3; i++) {
				char[] l = nextToken().toCharArray();
				char[] r = nextToken().toCharArray();
				int n = l.length;
				left[i] = new int [n];
				right[i] = new int [n];
				char cmp = nextToken().charAt(0);
				res[i] = cmp == 'd' ? DOWN : (cmp == 'e' ? EVEN : UP);
				for (int j = 0; j < n; j++) {
					left[i][j] = l[j] - 'A';
					right[i][j] = r[j] - 'A';
				}
			}
			Arrays.fill(w, 2);
			for (int m = 0; m < 12; m++) {
				llp: for (w[m] = 1; w[m] <= 3; w[m] += 2) {
					for (int i = 0; i < 3; i++) {
						int n = left[i].length;
						int wl = 0;
						int wr = 0;
						for (int j = 0; j < n; j++) {
							wl += w[left[i][j]];
							wr += w[right[i][j]];
						}
						if (!cmp(wl, wr, res[i]))
							continue llp;
					}
					out.print((char)(m + 'A'));
					out.print(" is the counterfeit coin and it is ");
					out.println(w[m] == 1 ? "light." : "heavy.");
					continue lp;
				}
				w[m] = 2;
			}
		}
		
		out.close();
	}
	
	boolean cmp(int a, int b, int c) {
		if (c == DOWN)
			return a < b;
		if (c == EVEN)
			return a == b;
		return a > b;
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
