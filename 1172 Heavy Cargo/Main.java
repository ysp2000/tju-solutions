import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
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
	
	int vNum;
	Map<String, Integer> map = new HashMap<String, Integer>();
	Edge[] edges = new Edge [19900];
	
	void run() throws IOException {
		for (int i = 0; i < 19900; i++)
			edges[i] = new Edge();
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		DSF dsf = new DSF();
		for (int t = 1;;) {
			int N = nextInt();
			int R = nextInt();
			if (N == 0)
				break;
			map.clear();
			vNum = 0;
			for (int i = 0; i < R; i++)
				edges[i].set(getVertex(nextToken()), getVertex(nextToken()), nextInt());
			sort(edges, 0, R);
			int a = getVertex(nextToken());
			int b = getVertex(nextToken());
			dsf.initialize(vNum);
			int ans = 0;
			for (int i = 0; i < R && dsf.setOf(a) != dsf.setOf(b); i++)
				if (dsf.union(edges[i].u, edges[i].v))
					ans = edges[i].w;
			out.print("Scenario #");
			out.println(t++);
			out.print(ans);
			out.println(" tons");
			out.println();
		}
		out.close();
	}
	
	int getVertex(String s) {
		if (!map.containsKey(s))
			map.put(s, vNum++);
		return map.get(s);
	}
	
	class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;
		
		void set(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge e) {
			return e.w - w;
		}
	}	
	
	class DSF {
		int[] set = new int [200];
		int[] rnk = new int [200];
		
		void initialize(int sz) {
			for (int i = 0; i < sz; i++)
				set[i] = i;
			fill(rnk, 0, sz, 0);
		}
		
		int setOf(int x) {
			return x == set[x] ? x : (set[x] = setOf(set[x]));
		}
		
		boolean union(int i, int j) {
			i = setOf(i);
			j = setOf(j);
			if (i == j)
				return false;
			if (rnk[i] > rnk[j])
				set[j] = i;
			else {
				set[i] = j;
				rnk[j] += rnk[i] == rnk[j] ? 1 : 0;
			}
			return true;
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
}
