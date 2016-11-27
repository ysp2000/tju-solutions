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

	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		Treap t = new Treap();
		for (int n = nextInt(); n > 0; n = nextInt()) {
			t.clear(n);
			for (int q = 0; q < n; q++) {
				if (nextChar() == 'B') t.add(nextInt());
				else out.println(t.get());
			}
		}
		out.close();
	}

	BufferedReader in;
	int MAXN = 100000;
	
	class Treap {
		final int[] NIL = { 0, 0};
		
		int root = 0;
		int[] keys = new int [MAXN + 1];
		int[] nums = new int [MAXN + 1];
		int[] priority = new int [MAXN + 1];
		int[] left = new int [MAXN + 1];
		int[] right = new int [MAXN + 1];
		int[] memory = new int [MAXN + 1];
		int mp = 0;
		Random rnd = new Random(1007);
		
		Treap() {
			for (int i = 1; i <= MAXN; i++) {
				priority[i] = rnd.nextInt();
				memory[i] = i;
			}
		}
		
		void clear(int n) {
			mp = n + 1;
			root = 0;
			for (int i = 1; i <= mp; i++)
				memory[i] = i;
		}
		
		void add(int key) {
			if (!inc(key))
				root = add(root, alloc(key));
		}
		
		int get() {
			int v = min();
			if (v == -1)
				return -1;
			int ret = keys[v];
			if (dec(v))
				delete(ret);
			return ret;
		}
		
		void delete(int key) {
			root = delete(root, key);
		}
		
		boolean inc(int key) {
			int v = find(key);
			if (v == -1)
				return false;
			nums[v]++;
			return true;
		}
		
		boolean dec(int v) {
			nums[v]--;
			return nums[v] == 0;
		}

		int find(int key) {
			int v = root;
			while (v != 0) {
				if (key == keys[v])
					return v;
				v = key < keys[v] ? left[v] : right[v];
			}
			return -1;
		}
		
		int min() {
			int v = root;
			while (left[v] != 0)
				v = left[v];
			return v == 0 ? -1 : v;
		}
		
		int add(int v, int x) {
			if (v == 0)
				return update(x);
			if (priority[x] < priority[v]) {
				int[] a = split(v, keys[x]);
				left[x] = a[0];
				right[x] = a[1];
				return update(x);
			}
			if (keys[x] < keys[v])
				left[v] = add(left[v], x);
			else
				right[v] = add(right[v], x);
			return update(v);
		}
		
		int delete(int v, int key) {
			if (v == 0)
				return 0;
			if (key == keys[v]) {
				memory[++mp] = v;
				return merge(left[v], right[v]);
			}
			if (key < keys[v]) left[v] = delete(left[v], key);
			if (key > keys[v]) right[v] = delete(right[v], key);
			return update(v);
		}

		int[] split(int v, int key) {
			if (v == 0)
				return NIL;
			if (key == keys[v])
				return new int[] { left[v], right[v] };
			if (key < keys[v]) {
				int[] a = split(left[v], key);
				left[v] = a[1];
				return new int[] { a[0], update(v) };
			} else {
				int[] a = split(right[v], key);
				right[v] = a[0];
				return new int[] { update(v), a[1] };
			}
		}

		int merge(int a, int b) {
			if (a == 0) return b;
			if (b == 0) return a;
			if (priority[a] < priority[b]) {
				right[a] = merge(right[a], b);
				return update(a);
			} else {
				left[b] = merge(a, left[b]);
				return update(b);
			}
		}
		
		int update(int x) {
			return x;
		}

		int alloc(int key) {
			int ind = memory[mp--];
			keys[ind] = key;
			left[ind] = right[ind] = 0;
			nums[ind] = 1;
			return ind;
		}
	}
	
	char nextChar() throws IOException {
		for (int c = in.read();; c = in.read())
			if ('A' <= c && c <= 'Z')
				return (char) c;
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
