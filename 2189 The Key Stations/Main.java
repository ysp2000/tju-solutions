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
		
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new Main().run();
				} catch (IOException e) {}
			}
		}, "1", 1L << 24).start();
	}

	BufferedReader in;
	PrintWriter out;
	
	int MAXN = 1000;
	
	int N;
	int vNum;
	Graph g = new Graph();

	boolean[] used = new boolean [MAXN];
	int[] tin = new int [MAXN];
	int[] fup = new int [MAXN];
	int timer = 0;
	
	boolean[] cutPoint = new boolean [MAXN];
	
	int asz = 0;
	int[] answer = new int [MAXN];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (N = Integer.parseInt(in.readLine()); N > 0; N = Integer.parseInt(in.readLine())) {
			vNum = 0;
			g.prepare(N);
			
			for (;;) {
				StringTokenizer tok = new StringTokenizer(in.readLine());
				int u = Integer.parseInt(tok.nextToken()) - 1;
				if (u == -1)
					break;
				while (tok.hasMoreTokens()) {
					int v = Integer.parseInt(tok.nextToken()) - 1;
					if (u != v) {
						g.add(u, v);
						g.add(v, u);
					}
				}
			}
			
			timer = 0;
			fill(cutPoint, 0, N, false);
			fill(used, 0, N, false);
			
			for (int i = 0; i < N; i++)
				if (!used[i])
					dfs(0, -1);
			
			asz = 0;
			for (int i = 0; i < N; i++)
				if (cutPoint[i])
					answer[asz++] = i + 1;
			out.print(asz);
			for (int i = 0; i < asz; i++)
				out.print(" " + answer[i]);
			out.println();
		}
		
		out.close();
	}
	
	void dfs(int v, int p) {
		used[v] = true;
		tin[v] = fup[v] = timer++;
		
		int cnt = 0;
		
		for (int i = g.head[v]; i != 0; i = g.next[i]) {
			int x = g.vert[i];
			if (used[x])
				fup[v] = min(fup[v], tin[x]);
			else {
				cnt++;
				dfs(x, v);
				fup[v] = min(fup[v], fup[x]);
				if (fup[x] >= tin[v])
					cutPoint[v] = true;
			}
		}
		
		if (p == -1)
			cutPoint[v] = cnt > 1;
	}	

	class Graph {
		int[] head = new int [MAXN];
		int[] next = new int [1 + 2 * MAXN];
		int[] vert = new int [1 + 2 * MAXN];
		Set<Integer>[] adj = new Set [MAXN];
		int cnt = 1;
		
		Graph() {
			for (int i = 0; i < MAXN; i++)
				adj[i] = new HashSet<Integer>();
		}
		
		void prepare(int vNum) {
			fill(head, 0, vNum, 0);
			for (int i = 0; i < vNum; i++)
				adj[i].clear();
			cnt = 1;
		}
		
		void add(int u, int v) {
			if (adj[u].contains(v))
				return;
			adj[u].add(v);
			next[cnt] = head[u];
			vert[cnt] = v;
			head[u] = cnt++;
		}
	}
}
