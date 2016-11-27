import java.io.*;

import static java.util.Arrays.sort;

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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int F = nextInt();
		int M = nextInt();
		double A = F / (double) M;
		int N = nextInt();
		Item[] p = new Item [N];
		for (int i = 0; i < N; i++)
			p[i] = new Item(i + 1, nextInt(), nextInt());
		sort(p);
		int[] ans = new int [N];
		int asz = 0;
		for (Item i : p) {
			int nF = F + i.F;
			int nM = M + i.M;
			double nA = nF / (double) nM;
			if (A < nA) {
				F = nF;
				M = nM;
				A = nA;
				ans[asz++] = i.id;
			}
		}
		if (asz > 0) {
			sort(ans, 0, asz);
			for (int i = 0; i < asz; i++)
				out.println(ans[i]);
		} else {
			out.println("NONE");
		}
		out.close();
	}
	
	class Item implements Comparable<Item> {
		int id;
		int F;
		int M;
		double A;
		
		Item(int id, int F, int M) {
			this.id = id;
			this.F = F;
			this.M = M;
			this.A = F / (double) M;
		}

		@Override
		public int compareTo(Item i) {
			return A < i.A ? 1 : -1;
		}
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
