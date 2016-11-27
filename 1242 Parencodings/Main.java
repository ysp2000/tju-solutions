import java.io.*;
import java.util.*;

/* Asia - Tehran 2001 */
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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int TESTNUM = nextInt();
		
		for (int test = 0; test < TESTNUM; test++) {
			int n = nextInt();
			int prev = 0;
			int ssz = 0;
			int[] seq = new int [2 * n];
			
			for (int i = 0; i < n; i++) {
				int cur = nextInt();
				ssz += cur - prev;
				prev = cur;
				seq[ssz++] = 1;
			}
			
			int sp = 1;
			int[] stack = new int [n + 1];
			int asz = 0;
			int[] ans = new int [n];
			
			for (int i = 0; i < 2 * n; i++) {
				if (seq[i] == 0) {
					stack[sp++] = 0;
				} else {
					stack[sp - 1]++;
					int cur = stack[--sp];
					ans[asz++] = cur;
					stack[sp - 1] += cur;
				}
			}
			
			out.print(ans[0]);
			
			for (int i = 1; i < asz; i++) {
				out.print(" " + ans[i]);
			}
			
			out.println();
		}
		
		out.close();
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
