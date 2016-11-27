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
		int[] ans = new int [5000];
		for (int n = nextInt(); n != -1; n = nextInt()) {
			int m = nextInt();
			int l = 0;
			int k = 0;
			for (l = 1; k + l - 1 <= m; k += l - 1, l++);
			int p = m - k;
			int asz = 0;
			if (p == 0) {
				l--;
				for (int i = 1; i <= n - l; i++)
					ans[asz++] = i;
				for (int i = n; i > n - l; i--)
					ans[asz++] = i;
			} else { 
				for (int i = 1; i <= n - l; i++)
					ans[asz++] = i;
				int x = ans[asz++] = n - l + 1 + p;
				for (int i = n; i > n - l; i--)
					if (i != x)
						ans[asz++] = i;
			}
			out.print(ans[0]);
			for (int i = 1; i < asz; i++) {
				out.print(' '); out.print(ans[i]);
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
