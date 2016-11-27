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
	
	int MAXS = 5000000;
	
	long[] pow3 = new long [24 + 1];
	
	int[] ir = { 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6 };
	int[] ic = { 2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4 };
	
	int[][] nr = {
		//    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
			{ 6, 0, 0, 1, 2, 2, 1, 2, 2, 2, 2, 2, 3, 4, 4, 3, 4, 4, 4, 4, 4, 5, 5, 6 },
			{ 0, 6, 1, 0, 2, 2, 2, 2, 1, 2, 2, 3, 2, 4, 4, 4, 4, 3, 4, 4, 5, 4, 6, 5 },
			{ 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6 },
			{ 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6 },
			{ 0, 1, 1, 2, 2, 2, 2, 2, 3, 2, 2, 3, 4, 4, 4, 4, 4, 5, 4, 4, 5, 6, 6, 0 },
			{ 1, 0, 2, 1, 2, 2, 3, 2, 2, 2, 2, 4, 3, 4, 4, 5, 4, 4, 4, 4, 6, 5, 0, 6 },
			{ 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6 },
			{ 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6 }
	};
	
	int[][] nc = {
		//	  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
			{ 2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4 },
			{ 2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4 },
			{ 2, 4, 2, 4, 1, 2, 3, 4, 5, 6, 0, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4 },
			{ 2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 1, 2, 3, 4, 5, 6, 0, 2, 4, 2, 4 },
			{ 2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4 },
			{ 2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4 },
			{ 2, 4, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 6, 0, 1, 2, 3, 4, 5, 2, 4, 2, 4 },
			{ 2, 4, 2, 4, 6, 0, 1, 2, 3, 4, 5, 2, 4, 0, 1, 2, 3, 4, 5, 6, 2, 4, 2, 4 },
	};
	
	int[] tr = { 2, 2, 2, 3, 3, 4, 4, 4 };
	int[] tc = { 2, 3, 4, 2, 4, 2, 3, 4 };
	
	int[][] a = new int [7][7];
	int[][] b = new int [7][7];
	
	int[] t = new int [7];
	
	int best;
	int num = -1;
	
//	Set<Long> used = new HashSet<Long>();
	MySet used = new MySet();
	long[] queue = new long [MAXS];
	int[] prev = new int [MAXS];
	char[] move = new char [MAXS];
	
	void run() throws IOException {
		pow3[0] = 1;
		for (int i = 1; i <= 24; i++)
			pow3[i] = 3L * pow3[i - 1];
		
		PrintWriter out = new PrintWriter(System.out);
		while (input()) {
			if (test(a)) {
				out.println("No moves needed");
				out.print(a[tr[0]][tc[0]]);
				continue;
			}
			
			int qH = 0;
			int qT = 0;
			
			used.clear();
			queue[qT++] = code(a);
			int last = -1;
			
			lp: while (qH < qT) {
				int cur = qH++;
				long code = queue[cur];
				decode(code, a);
				if (test(a) || qT > 2000000) {
					last = cur;
					break;
				}
				for (int m = 0; m < 8; m++) {
					move(m, a, b);
					long ncode = code(b);
					if (!used.contains(ncode)) {
						used.add(ncode);
						prev[qT] = cur;
						move[qT] = (char) ('A' + m);
						queue[qT++] = ncode;
					}
				}
			}

			StringBuffer sb = new StringBuffer();
			for (int v = last; v != 0; v = prev[v])
				sb.append(move[v]);
			out.println(sb.reverse());
			decode(queue[last], a);
			out.println(a[tr[0]][tc[0]]);
		}
		out.close();
	}
	
	void print(int[][] a) {
		System.out.println();
		for (int[] b : a)
			System.out.println(Arrays.toString(b));
	}

	void move(int m, int[][] a, int[][] b) {
		for (int i = 0; i < ir.length; i++)
			b[nr[m][i]][nc[m][i]] = a[ir[i]][ic[i]];
	}

	void decode(long code, int[][] a) {
		for (int i = 0; i < ir.length; i++, code /= 3)
			a[ir[i]][ic[i]] = (int) (code % 3) + 1;
	}

	long code(int[][] a) {
		long ret = 0;
		for (int i = 0; i < ir.length; i++)
			ret += pow3[i] * (a[ir[i]][ic[i]] - 1);
		return ret;
	}

	boolean test(int[][] a) {
		for (int v = a[tr[0]][tc[0]], k = 1; k < 8; k++)
			if (a[tr[k]][tc[k]] != v)
				return false;
		return true;
	}

	boolean input() throws IOException {
		for (int k = 0; k < 24; k++)
			if ((a[ir[k]][ic[k]] = nextInt()) == 0)
				return false;
		return true;
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
	
	class MySet {
		int MAXN = 3000017;
		int tick = 1;
		
		int[] head = new int [MAXN];
		int[] used = new int [MAXN];
		int[] next = new int [MAXS + 1];
		long[] key = new long [MAXS + 1];
		int cnt = 1;
		
		void clear() {
			tick++;
		}
		
		void add(long K) {
			int h = hash(K);
			next[cnt] = head(h);
			key[cnt] = K;
			head[h] = cnt++;
		}
		
		boolean contains(long K) {
			for (int i = head(hash(K)); i != 0; i = next[i])
				if (key[i] == K)
					return true;
			return false;
		}
		
		int hash(long K) {
			return Math.abs((int) (K ^ (K >> 20))) % MAXN;
		}
		
		int head(int h) {
			if (used[h] == tick) {
				used[h] = tick;
				head[h] = 0;
			}
			return head[h];
		}
	}
}
