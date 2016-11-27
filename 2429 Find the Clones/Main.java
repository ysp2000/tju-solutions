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

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int MAXN = 20000;
	int MAXM = 20;
	int HDEG = 18;
	
	int N;
	int M;
	MyHashMap map = new MyHashMap();
	int[] ans = new int [MAXN];
	
	long[] code = new long [128];
	long[] pows = new long [MAXM + 1];
	
	void run() throws IOException {
		code['A'] = 1L;
		code['C'] = 2L;
		code['G'] = 3L;
		code['T'] = 4L;
		
		pows[0] = 1L;
		for (int i = 1; i <= MAXM; i++)
			pows[i] = pows[i - 1] * 5L;
		
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (;;) {
			N = nextInt();
			M = nextInt();
			if (N == 0 && M == 0)
				break;
			map.clear();
			for (int i = 0; i < N; i++)
				map.put(code(nextToken()));
			map.getAns();
			for (int i = 0; i < N; i++)
				out.println(ans[i]);
		}
		
		out.close();
	}
	
	long code(String s) {
		long ret = 0L;
		for (int i = 0; i < M; i++)
			ret += pows[i] * code[s.charAt(i)];
		return ret;
	}

	class MyHashMap {
		int SIZE = 1 << HDEG;
		int MASK = SIZE - 1;
		
		int[] head = new int [SIZE];
		int[] valid = new int [SIZE];
		int[] next = new int [MAXN];
		long[] keys = new long [MAXN];
		int[] values = new int [MAXN];
		
		int[] headset = new int [MAXN];
		int tick = 1;
		int pointer = 1;
		int hsize = 0;

		void clear() {
			tick++;
			pointer = 1;
			hsize = 0;
		}
		
		int head(int h) {
			if (valid[h] != tick) {
				valid[h] = tick;
				headset[hsize++] = h;
				return head[h] = 0;
			}
			return head[h];
		}

		void put(long key) {
			int hash = hash(key);
			int find = find(hash, key);
			if (find == -1) {
				add(hash, key);
			} else {
				values[find]++;
			}
		}

		int find(int hash, long key) {
			for (int i = head(hash); i != 0; i = next[i])
				if (keys[i] == key)
					return i;
			return -1;
		}
		
		void add(int hash, long key) {
			next[pointer] = head(hash);
			keys[pointer] = key;
			values[pointer] = 1;
			head[hash] = pointer++;
		}

		int hash(long key) {
			return (int) ((key >> 24) ^ key) & MASK;
		}
		
		void getAns() {
			Arrays.fill(ans, 0, N, 0);
			for (int hind = 0; hind < hsize; hind++) {
				int hash = headset[hind];
				for (int i = head[hash]; i != 0; i = next[i])
					ans[values[i] - 1]++;
			}
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
