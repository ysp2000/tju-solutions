import java.io.*;
import java.util.*;

import static java.lang.Math.*;

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
	
	int MAXZ = 20;
	int MAXN = 10;
	
	int Z;
	int N;
	char[] header;
	int[] len = new int [21];
	
	int[] key = new int [10 * MAXZ];
	int[] leaf = new int [10 * MAXZ];
	int[][] next = new int [10 * MAXZ][MAXN];
	int[][] mark = new int [10 * MAXZ][MAXN];
	int tick = 0;
	int cnt = 1;
	
	int maxLen;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int T = nextInt(); T --> 0; ) {
			Z = nextInt();
			N = nextInt();
			header = nextToken().toCharArray();
			int L = 0;
			while (L * (N - 1) + 1 < Z)
				L++;
			int uLen = (L - 1) * L * (N - 1) / 2 + L * N;
			if (abs(uLen - header.length) < header.length / 10) {
				for (maxLen = L; !dfs1(0, 0); maxLen++);
			} else {
				for (maxLen = 2; !dfs2(0, 0); maxLen++);
			}
			int from = 0;
			for (int i = 0; i < Z; i++) {
				out.print(i);
				out.print("->");
				for (int j = 0; j < len[i]; j++)
					out.print(header[from + j]);
				from += len[i];
				out.println();
			}
		}
		
		out.close();
	}

	boolean dfs1(int v, int pos) {
		if (v == Z) return pos == header.length;
		if (header.length - pos < (Z - v)) return false;
		if (header.length - pos < need) return false;
		if (header.length - pos > (Z - v) * maxLen) return false;
		for (len[v] = min(maxLen, header.length - pos); len[v] > 0; len[v]--) {
			if (can(v) && dfs1(v + 1, pos + len[v]))
				return true;
		}
		return false;
	}
	
	boolean dfs2(int v, int pos) {
		if (v == Z) return pos == header.length;
		if (header.length - pos < (Z - v)) return false;
		if (header.length - pos < need) return false;
		if (header.length - pos > (Z - v) * maxLen) return false;
		for (len[v] = 4; len[v] < maxLen && pos + len[v] <= header.length; len[v]++) {
			if (can(v) && dfs2(v + 1, pos + len[v]))
				return true;
		}
		for (len[v] = 1; len[v] < min(4, maxLen) && pos + len[v] <= header.length; len[v]++) {
			if (can(v) && dfs2(v + 1, pos + len[v]))
				return true;
		}
		return false;
	}
	
	int need = 0;

	boolean can(int lastV) {
		tick++;
		cnt = 1;
		int from = 0;
		for (int i = 0; i <= lastV; i++) {
			if (!add(from, len[i], i) || size(0) > Z)
				return false;
			from += len[i];
		}
		need = need(0, 0);
		return true;
	}

	int need(int v, int h) {
		if (leaf[v] == tick)
			return 0;
		int ret = 0;
		for (int i = 0; i < N; i++)
			if (mark[v][i] == tick)
				ret += need(next[v][i], h + 1);
			else
				ret += h + 1;
		return ret;
	}

	boolean add;
	
	boolean add(int ind, int len, int vert) {
		int v = 0;
		add = false;
		for (int i = 0; i < len; i++) {
			int c = header[ind + i] - '0';
			v = go(v, c);
			if (leaf[v] == tick)
				return false;
		}
		if (!add) return false;
		key[v] = vert;
		leaf[v] = tick;
		return true;
	}
	
	int go(int v, int c) {
		if (mark[v][c] != tick) {
			mark[v][c] = tick;
			next[v][c] = alloc();
		}
		return next[v][c];
	}

	int alloc() {
		add = true;
		return cnt++;
	}

	int size(int v) {
		if (leaf[v] == tick) return 1;
		int ret = 0;
		for (int i = 0; i < N; i++)
			if (mark[v][i] == tick)
				ret += size(next[v][i]);
			else
				ret++;
		return ret;
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
