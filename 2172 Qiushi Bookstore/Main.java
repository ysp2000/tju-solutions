import java.io.*;
import java.util.*;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			String file = "input.txt";
			if (new File(file).exists())
				System.setIn(new FileInputStream(file));
		} catch (SecurityException e) {}
		new Main().run();
	}

	BufferedReader in;
	int MASK = (1 << 18) - 1;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tok.nextToken());
		int M = Integer.parseInt(tok.nextToken());
		LabeledGraph lg = new LabeledGraph(MASK + 1, N);
		Edge[] edges = new Edge [M];
		for (int e = 0; e < M; e++) {
			tok = new StringTokenizer(in.readLine());
			edges[e] = new Edge(lg.getVertexNumber(tok.nextToken()), lg.getVertexNumber(tok.nextToken()), tok.nextToken().charAt(0) - '0');
		}
		sort(edges);
		DSF dsf = new DSF(N);
		int ans = 0;
		for (Edge e : edges)
			if (dsf.union(e.u, e.v))
				ans += e.w;
		ans += 10 * dsf.sscCount();
		System.out.println("The total cost is " + ans / 10 + " Yuan " + ans % 10 + " Jiao.");
	}
	
	class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;
		
		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return w - e.w;
		}
	}
	
	class LabeledGraph {
		int[] head;
		int[] next;
		int[] key;
		int[] val;
		int cnt = 1;
		
		LabeledGraph(int hsz, int sz) {
			head = new int [hsz];
			next = new int [sz + 1];
			key = new int [sz + 1];
			val = new int [sz + 1];
		}
		
		int getVertexNumber(String label) {
			int K = label.hashCode();
			int H = Math.abs(K) & MASK;
			int res = find(H, K);
			return res < 0 ? addVertex(H, K) : res;			
		}
		
		int find(int H, int K) {
			for (int i = head[H]; i != 0; i = next[i])
				if (K == key[i])
					return val[i];
			return -1;
		}
		
		int addVertex(int H, int K) {
			int vNum = cnt - 1;
			next[cnt] = head[H];
			key[cnt] = K;
			val[cnt] = vNum;
			head[H] = cnt++;
			return vNum;
		}
	}
	
	class DSF {
		int[] set;
		int[] rnk;
		
		DSF(int sz) {
			set = new int [sz];
			rnk = new int [sz];
			for (int i = 0; i < sz; i++)
				set[i] = i;
		}
		
		int setOf(int i) {
			return i == set[i] ? i : (set[i] = setOf(set[i]));
		}
		
		boolean union(int i, int j) {
			i = setOf(i);
			j = setOf(j);
			if (i == j)
				return false;
			if (rnk[i] > rnk[j]) {
				set[j] = i;
			} else {
				set[i] = j;
				if (rnk[i] == rnk[j])
					rnk[j]++;
			}
			return true;
		}
		
		int sscCount() {
			int res = 0;
			for (int i = 0; i < set.length; i++) {
				int s = setOf(i);
				if (rnk[s] >= 0) {
					rnk[s] = -1;
					res++;
				}
			}
			return res;
		}
	}
	
}
