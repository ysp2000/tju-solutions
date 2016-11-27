import java.io.*;
import java.util.*;

import static java.util.Arrays.fill;

public class Main {

	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int INF = Integer.MAX_VALUE >> 1;
	int MAXE = 2 * 1000000;
	int vNum;
	Map<String, Integer> map = new HashMap<String, Integer>();
	MultiLis g = new MultiLis();
	
	int[] a = new int [150];
	int[] q = new int [10000];
	int[] used = new int [10000];
	int[] dist = new int [10000];
	int tick = 0;
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer tok;
		
		for (int t = 1;; t++) {
			initialize();
			tok = new StringTokenizer(in.readLine());
			int P = Integer.parseInt(tok.nextToken());
			int N = Integer.parseInt(tok.nextToken());
			if (P == 0 && N == 0)
				break;
			for (int p = 0; p < P; p++) {
				tok = new StringTokenizer((new StringTokenizer(in.readLine(), ":")).nextToken(), " ,");
				int k = 0;
				while (tok.hasMoreTokens())
					a[k++] = addVertex(tok.nextToken() + (char) (150) + tok.nextToken());
				for (int i = 0; i < k; i++)
					for (int j = i + 1; j < k; j++) {
						g.add(a[i], a[j]);
						g.add(a[j], a[i]);
					}
			}
//			for (Entry e : map.entrySet())
//				System.out.println(e.getKey() + " " + e.getValue());
			fill(dist, 0, vNum, INF);
			tick++;
			bfs(getVertex("Erdos" + (char) (150) + "P."));
			out.print("Database #");
			out.println(t);
			for (int n = 0; n < N; n++) {
				String s = in.readLine();
				tok = new StringTokenizer(s, ", ");
				out.print(s);
				out.print(": ");
				out.println(s(tok.nextToken() + (char) (150) + tok.nextToken()));
			}
			out.println();
		}
		
		out.close();
	}
	
	String s(String name) {
		int v = getVertex(name);
		return (v == -1 || dist[v] == INF) ? "infinity" : ("" + dist[v]);
	}
	
	void bfs(int v) {
		if (v == -1)
			return;
		int qT = 0;
		int qH = 0;
		dist[v] = 0;
		q[qT++] = v;
		
		while (qH < qT) {
			v = q[qH++];
			int cd = dist[v];
			
			for (int i = g.head(v); i != 0; i = g.next[i]) {
				int x = g.vert[i];
				if (used[x] != tick) {
					used[x] = tick;
					dist[x] = cd + 1;
					q[qT++] = x;
				}
			}
		}
	}

	void initialize() {
		vNum = 0;
		map.clear();
		g.clear();
	}

	int addVertex(String s) {
		if (map.containsKey(s))
			return map.get(s);
		map.put(s, vNum++);
		return vNum - 1;
	}
	
	int getVertex(String s) {
		if (map.containsKey(s))
			return map.get(s);
		return -1;
	}
	
	class MultiLis {
		int[] head = new int [10000];
		int[] used = new int [10000];
		int[] next = new int [MAXE + 1];
		int[] vert = new int [MAXE + 1];
		int cnt = 1;
		int tick = -1;
		
		void clear() {
			cnt = 1;
			tick++;
		}
		
		int head(int h) {
			if (used[h] != tick) {
				used[h] = tick;
				head[h] = 0;
			}
			return head[h];
		}
		
		void add(int u, int v) {
			next[cnt] = head(u);
			vert[cnt] = v;
			head[u] = cnt++;
		}
	}
}
