/* naive O(n^2) */
import java.io.*;
import java.util.*;

public class Main {
	String DELIM = ": ";
	
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
	StringTokenizer st = new StringTokenizer("", DELIM);
	
	int[] pos = new int [10000];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int n = nextInt(); n > 0; n = nextInt()) {
			for (int i = 0; i < n; i++)
				pos[nextInt()] = i;
			boolean ans = true;
			
			loop: for (int i = 1; i < n - 1; i++) {
				for (int d = 1;; d++) {
					if (i - d < 0 || i + d >= n)
						break;
					int p1 = pos[i - d];
					int p2 = pos[i];
					int p3 = pos[i + d];
					if (p1 < p2 && p2 < p3 || p3 < p2 && p2 < p1) {
						ans = false;
						break loop;
					}
				}
			}
			
			out.println(ans ? "yes" : "no");
		}
		
		out.close();
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine(), DELIM);
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
