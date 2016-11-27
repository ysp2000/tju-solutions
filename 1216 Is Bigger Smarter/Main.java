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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int eNum = 0;
		Elephant[] e = new Elephant [1000];
		int id = 1;
		while (in.ready())
			e[eNum++] = new Elephant(id++, nextInt(), nextInt());
		Arrays.sort(e, 0, eNum);
		int[] dp = new int [eNum];
		int[] p = new int [eNum];
		int best = -1;
		for (int i = 0; i < eNum; i++) {
			dp[i] = 1;
			p[i] = -1;
			for (int j = 0; j < i; j++)
				if (e[i].r(e[j]))
					if (dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
						p[i] = j;
					}
			if (best == -1 || dp[i] > dp[best])
				best = i;
		}
		int n = dp[best];
		for (int i = n - 1, j = best; i >= 0; i--, j = p[j])
			dp[i] = e[j].id;
		out.println(n);
		for (int i = 0; i < n; i++)
			out.println(dp[i]);
		out.close();
	}
	
	class Elephant implements Comparable<Elephant> {
		int id;
		int weight;
		int iq;
		int x;
		
		Elephant(int id, int weight, int iq) {
			this.id = id;
			this.weight = weight;
			this.iq = iq;
			this.x = weight * (10001 - iq);
		}
		
		boolean r(Elephant e) {
			return weight > e.weight && iq < e.iq;
		}
		
		@Override
		public int compareTo(Elephant e) {
			return x - e.x;
		}
	}

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
