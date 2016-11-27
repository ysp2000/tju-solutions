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
		
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new Main().run();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "1", 1 << 24).start();
		
	}

	BufferedReader in;
	PrintWriter out;
	
	int MAXE = 1995;
	
	int vNum = 44;
	int eNum;
	int start;
	List<Edge>[] graph = new List [vNum];
	List<Integer> ans = new ArrayList<Integer>(MAXE);
	int[] deg = new int [vNum];
	int[] used1 = new int [MAXE];
	int[] used2 = new int [vNum];
	int tick1 = 1;
	int tick2 = 1;
	
	void run() throws IOException {
		for (int i = 0; i < graph.length; i++)
			graph[i] = new ArrayList<Edge>(44);
		
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (clearUp(); readEdge(); clearUp()) {
			while (readEdge());
			solve();
		}
		
		out.close();
	}
	
	void clearUp() {
		start = -1;
		eNum = 0;
		tick1++;
		fill(deg, 0);
		for (List<Edge> lst : graph)
			lst.clear();
		ans.clear();
	}
	
	boolean readEdge() throws IOException {
		int x = nextInt() - 1;
		int y = nextInt() - 1;
		if (x == -1 && y == -1)
			return false;
		if (start == -1)
			start = min(x, y);
		int z = nextInt();
		graph[x].add(new Edge(z, y));
		if (x != y)
			graph[y].add(new Edge(z, x));
		deg[x]++;
		deg[y]++;
		eNum++;
		return true;
	}

	void solve() {
		for (List<Edge> lst : graph)
			Collections.sort(lst);
		if (dfs1(start) && eNum == ans.size()) {
			for (int i = 0; i < eNum; i++) {
				if (i > 0)
					out.print(' ');
				out.print(ans.get(i));
			}
			out.println();
		} else {
			out.println("Round trip does not exist.");
		}
		out.println();
	}
	
	boolean dfs1(int v) {
		for (Edge e : graph[v]) {
			if (good(v, e)) {
				removeEdge(v, e);
				ans.add(e.id);
				return dfs1(e.to);
			}
		}
		return v == start;
	}

	void removeEdge(int v, Edge e) {
		used1[e.id] = tick1;
		deg[v]--;
		deg[e.to]--;
	}

	boolean good(int u, Edge e) {
		tick2++;
		return used1[e.id] != tick1 && (deg[u] == 1 || dfs2(u, e));
	}

	boolean dfs2(int v, Edge e) {
		if (v == e.to)
			return true;
		used2[v] = tick2;
		for (Edge ce : graph[v])
			if (e.id != ce.id && used1[ce.id] != tick1 && used2[ce.to] != tick2 && dfs2(ce.to, e))
				return true;
		return false;
	}

	class Edge implements Comparable<Edge> {
		int id;
		int to;
		
		Edge(int id, int to) {
			this.id = id;
			this.to = to;
		}
	
		@Override
		public String toString() {
			return "(id = " + id + ", to =  " + to + ")";
		}
		
		@Override
		public int compareTo(Edge edge) {
			return id - edge.id;
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
