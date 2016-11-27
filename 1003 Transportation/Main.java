import java.io.*;

import static java.util.Arrays.sort;
import static java.util.Arrays.fill;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int N;
	int M;
	int K;
	Order[] o = new Order [22];
	int[] p = new int [8];
	boolean[] used = new boolean [1 << 22];
	int max;
	int cur;

	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		
		for (;;) {
			N = readUnsignedInt();
			M = readUnsignedInt();
			K = readUnsignedInt();
			
			if (N == 0)
				break;
			
			for (int i = 0; i < K; i++)
				o[i] = new Order(readUnsignedInt(), readUnsignedInt(), readUnsignedInt());
			sort(o, 0, K);
			for (int i = K - 2; i >= 0; i--)
				o[i].remain = o[i + 1].remain + o[i + 1].cost;
			
			max = 0;
			cur = 0;
			fill(p, 0, M, 0);
			fill(used, 0, 1 << K, false);
			dfs(0);
			
			out.println(max);
		}
		
		out.close();
	}
	
	void dfs(int mask) {
		used[mask] = true;
		max = Math.max(max, cur);
		for (int k = mask == 0 ? 0 : Integer.numberOfTrailingZeros(mask); k < K; k++) {
			int nmask = mask | (1 << k);
			if (!used[nmask] && can(k)) {
				cur += o[k].cost;
				if (cur + o[k].remain > max) {
					inc(k);
					dfs(nmask);
					dec(k);
				}
				cur -= o[k].cost;
			}
		}
	}

	boolean can(int k) {
		for (int i = o[k].begin, bk = o[k].end, ck = o[k].pasangers; i < bk; i++)
			if (p[i] + ck > N)
				return false;
		return true;
	}

	void inc(int k) {
		for (int i = o[k].begin, bk = o[k].end, ck = o[k].pasangers; i < bk; i++)
			p[i] += ck;
	}

	void dec(int k) {
		for (int i = o[k].begin, bk = o[k].end, ck = o[k].pasangers; i < bk; i++)
			p[i] -= ck;
	}

	class Order implements Comparable<Order> {
		int begin;
		int end;
		int pasangers;
		int cost;
		int remain = 0;
		
		Order(int begin, int end, int pasangers) {
			this.begin = begin;
			this.end = end;
			this.pasangers = pasangers;
			cost = (end - begin) * pasangers;
		}

		@Override
		public int compareTo(Order o) {
			if (cost == o.cost)
				if (pasangers == o.pasangers)
					return (end - begin) - (o.end - o.begin);
				else
					return pasangers - o.pasangers;
			return o.cost - cost;
		}
	}
	
	static int readUnsignedInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
