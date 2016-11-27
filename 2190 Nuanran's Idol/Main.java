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
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		DSF dsf = new DSF();
		for (int N = nextInt(); N > 0; N = nextInt()) {
			dsf.clear(N);
			int M = nextInt();
			int K = nextInt();
			while (M --> 0)
				dsf.union(nextInt() - 1, nextInt() - 1);
			int kelly = dsf.setOf(0);
			while (K --> 0)
				out.println(kelly == dsf.setOf(nextInt() - 1) ? "Yes" : "No");
		}
		out.close();
	}

	class DSF {
		int[] set = new int [100000];
		int[] rnk = new int [100000];

		void clear(int n) {
			Arrays.fill(rnk, 0, n, 0);
			for (int i = 0; i < n; i++)
				set[i] = i;
		}
		
		int setOf(int x) {
			return x == set[x] ? x : (set[x] = setOf(set[x]));
		}
		
		void union(int i, int j) {
			if ((i = setOf(i)) == (j = setOf(j)))
				return;
			if (rnk[i] > rnk[j])
				set[j] = i;
			else {
				set[i] = j;
				if (rnk[i] == rnk[j])
					rnk[j]++;
			}
		}
	}
	
	BufferedReader in;
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == -1)
				return -1;
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
