// TJU Team Selection Contest 3
import java.io.*;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.fill;

public class Main {
	int INF = Integer.MAX_VALUE >> 1;
	
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
	
	int MAXV = 200;
	int MAXE = 10000;
	
	int vNum;
	Graph graph = new Graph(MAXV, MAXE << 1);
	Pair[] verts = new Pair [MAXV];
	boolean[] used = new boolean [MAXV];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int i = 0; i < MAXV; i++) {
			verts[i] = new Pair(0, 0);
		}
		
		for (vNum = nextInt(); vNum > 0; vNum = nextInt()) {
			graph.reset(vNum);
			int eNum = nextInt();
			
			for (int i = 0; i < eNum; i++) {
				int u = nextInt();
				int v = nextInt();
				int d = nextInt();
				graph.add(u, v, d);
				graph.add(v, u, d);
			}
			
			RMQDijkstra(0);
			Arrays.sort(verts, 0, vNum);
			out.println(verts[nextInt()].v);
		}
		
		out.close();
	}
	
	void RMQDijkstra(int v) {
		fill(used, 0, vNum, false);
		
		for (int i = 0; i < vNum; i++) {
			verts[i].v = i;
			verts[i].d = INF;
		}
		
		RMQ rmq = new RMQ(0, vNum - 1);
		rmq.set(v, 0);
		verts[v].d = 0;
		
		while (rmq.get(v) != INF) {
			used[v] = true;
			int cDist = verts[v].d;
			
			for (int i = graph.head[v]; i > 0; i = graph.next[i]) {
				int adj = graph.vert[i];
				
				if (!used[adj]) {
					int newDist = cDist + graph.dist[i];
					
					if (verts[adj].d > newDist) {
						verts[adj].d = newDist;
						rmq.set(adj, newDist);
					}
				}
			}
			
			rmq.set(v, INF);
			v = rmq.minIndex(1, 0, vNum - 1);
		}
	}

	class Pair implements Comparable<Pair> {
		int v;
		int d;
		
		Pair(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Pair o) {
			if (d == o.d) {
				return v - o.v;
			}
			
			return d - o.d;
		}
		
		@Override
		public String toString() {
			return v + ": " + d;
		}
	}
	
	class Graph {
		int[] head;
		int[] next;
		int[] vert;
		int[] dist;
		int pnt = 1;
		
		Graph(int vNum, int eNum) {
			head = new int [vNum];
			next = new int [eNum + 1];
			vert = new int [eNum + 1];
			dist = new int [eNum + 1];
		}
		
		void reset(int hNum) {
			pnt = 1;
			fill(head, 0, hNum, 0);
		}
		
		void add(int u, int w, int d) {
			next[pnt] = head[u];
			vert[pnt] = w;
			dist[pnt] = d;
			head[u] = pnt++;
		}
	}
	
	class RMQ {
		int[] value;
		int[] index;
		int[] left;
		int[] right;
		int offset;
		int[] pointer;
		
		RMQ(int l, int r) {
			int len = r - l + 1;
			int sz = 1;
			
			while (sz < len) {
				sz <<= 1;
			}
			
			sz <<= 1;
			
			value = new int [sz];
			index = new int [sz];
			left = new int [sz];
			right = new int [sz];
			offset = l;
			pointer = new int [len];
			Arrays.fill(value, INF);
			build(1, l, r);
			
			for (int i = l; i <= r; i++) {
				index[pointer[i]] = i;
			}
		}

		void build(int v, int l, int r) {
			left[v] = l;
			right[v] = r;
			
			if (l != r) {
				v <<= 1;
				int m = (l + r) >> 1;
				build(v, l, m);
				build(v + 1, m + 1, r);
			} else {
				pointer[l - offset] = v;
			}
		}
		
		int get(int ind) {
			return value[pointer[ind - offset]];
		}
		
		void set(int ind, int val) {
			int v = pointer[ind - offset];
			value[v] = val;
			
			for (v >>= 1; v > 0; v >>= 1) {
				int l = v << 1;
				int r = l + 1;
				
				if (value[l] < value[r]) {
					value[v] = value[l];
					index[v] = index[l];
				} else {
					value[v] = value[r];
					index[v] = index[r];
				}
			}
		}
		
		int minIndex(int v, int l, int r) {
			if (l == left[v] && r == right[v]) {
				return index[v];
			}
			
			int res = -1;
			int nxt = v >> 1;
			
			if (l <= right[nxt]) {
				res = minIndex(nxt, l, min(r, right[nxt]));
			}
			
			if (r >= left[++nxt]) {
				int rInd = minIndex(nxt, max(l, left[nxt]), r);
				res = (res == -1 || get(res) > get(rInd)) ? rInd : res;
			}
			
			return res;
		}
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
