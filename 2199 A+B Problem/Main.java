import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}
	
	BufferedReader in;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		int[] a = new int [1000];
		Set set = new Set();
		PrintWriter out = new PrintWriter(System.out);
		for (int n = nextInt(); n > 0; n = nextInt()) {
			set.clear();
			for (int i = 0; i < n; i++)
				set.add(a[i] = nextInt());
			int ans = -1;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < i; j++)
					if (set.contains(a[i] + a[j]) && ans < a[i] + a[j])
						ans = a[i] + a[j];
			out.println(ans);
		}
		out.close();
	}

	class Set {
		int P = 16;
		int N = 1 << P;
		int M =  N - 1;
		int K = 1000;
		
		int[] head = new int [N];
		int[] used = new int [N];
		int[] next = new int [K + 1];
		int[] keys = new int [K + 1];
		int cnt = 1;
		int tick = 0;
		
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
		
		boolean contains(int key) {
			for (int i = head(hash(key)); i != 0; i = next[i])
				if (keys[i] == key)
					return true;
			return false;
		}
		
		void add(int key) {
			if (contains(key))
				return;
			int h = hash(key);
			next[cnt] = head[h];
			keys[cnt] = key;
			head[h] = cnt++;
		}
		
		int hash(int key) {
			return (key ^ (key >> 16)) & M; 
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
