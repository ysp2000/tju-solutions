import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
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
	StringTokenizer st = new StringTokenizer("");
	
	int V = 12;
	int T = 24 * 60;
	int MAXV = V * T;
	int MAXE = 100;

	int vNum;
	int[] num = new int [12];
	MultiList graph = new MultiList();
	
	int from;
	int to;
	int begin;
	int end;
	
	double[] dp = new double [MAXV];
	double[] p = new double [MAXV];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(); T --> 0; ) {
			vNum = 0;
			fill(num, -1);
			for (int K = nextInt(); K --> 0; )
				addEdge();
			getConstrants();
			fill(dp, -1.0);
			fill(p, -1.0);
			rec(from, begin);
		}
		
		out.close();
	}

	double rec(int v, int time) {
		if (time > end)
			return 0.0;
		if (v == to)
			return 1.0;
		int vrt = v(v, time);
		if (dp[vrt] != -1)
			return dp[vrt];
		if (graph.head[vrt] == 0)
			return rec(v, time + 1);
		double maxP = -1.0;
		int best = -1;
		for (int i = graph.head[vrt]; i != 0; i = graph.next[i]) {
			int x = graph.next[vrt];
			double p = graph.prob[vrt];
			double curP = p * rec(x / T, x % T) + (1 - p) * rec1(v, x / T, time + 1);
			
			if (maxP < curP) {
				maxP = curP;
			}
		}
	}

	double rec1(int v2, int i, int j) {
		return 0;
	}

	void getConstrants() throws IOException {
		StringTokenizer tok = new StringTokenizer(nextToken(), ": ");
		from = num(tok.nextToken().charAt(0) - 'A');
		begin = time(Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()));
		to = num(tok.nextToken().charAt(0) - 'A');
		end = time(Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()));
	}

	void addEdge() throws IOException {
		StringTokenizer tok = new StringTokenizer(nextToken(), ": ");
		int v1 = num(tok.nextToken().charAt(0) - 'A');
		int t1 = time(Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()));
		int v2 = num(tok.nextToken().charAt(0) - 'A');
		int t2 = time(Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()));
		double p = 1.0 - Double.parseDouble(tok.nextToken());
		graph.add(v(v1, t1), v(t2, v2), p);
	}
	
	int v(int v, int t) {
		return v * T + t;
	}
	
	int num(int c) {
		return num[c] == -1 ? (num[c] = vNum++) : num[c];
	}

	int time(int h, int m) {
		return h * 60 + m;
	}

	class MultiList {
		int[] head = new int [MAXV];
		int[] next = new int [MAXE + 1];
		int[] vert = new int [MAXE + 1];
		double[] prob = new double [MAXE + 1];
		int cnt = 1;

		void clear() {
			fill(head, 0);
			cnt = 1;
		}
		
		void add(int u, int v, double p) {
			next[cnt] = head[u];
			vert[cnt] = v;
			prob[cnt] = p;
			head[u] = cnt++;
		}
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
	
	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
	
	boolean EOF() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = in.readLine();
			if (s == null)
				return true;
			st = new StringTokenizer(s);
		}
		return false;
	}
}
