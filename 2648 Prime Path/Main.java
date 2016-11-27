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
	
	int[] head = new int [1061];
	int[] next = new int [300000 + 1];
	int[] data = new int [300000 + 1];
	int cnt = 1;
	
	void run() throws IOException {
		int MAXP = 9999;
		int SQRT = 100;
		boolean[] erat = new boolean [MAXP + 1];
		int[] primes = new int [1061];
		int psz = 0;
		int[] map = new int [10000];
		for (int i = 4; i <= MAXP; i += 2)
			erat[i] = true;
		for (int i = 3; i <= MAXP; i += 2)
			if (!erat[i]) {
				if (i >= 1000)
					map[primes[psz] = i] = psz++;
				if (i <= SQRT)
					for (int j = i * i; j <= MAXP; j += i)
						erat[j] = true;
			}
		for (int i = 0; i < psz; i++) {
			int p = primes[i];
			for (int d = 0; d < 4; d++) {
				for (int nd = d < 3 ? 0 : 1; nd <= 9; nd++) {
					int np = get(p, d, nd);
					if (!erat[np] && np != p)
						add(i, map[np]);
				}
			}
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int tick = 0;
		int[] queue = new int [10000];
		int[] dist = new int [10000];
		int[] used = new int [10000];
		for (int T = nextInt(); T --> 0;) {
			int a = nextInt();
			int b = map[nextInt()];
			int qH = 0;
			int qT = 0;
			tick++;
			queue[qT++] = map[a];
			int ans = -1;
			while (qH < qT) {
				int cv = queue[qH];
				int cd = dist[qH];
				qH++;
				if (cv == b) {
					ans = cd;
					break;
				}
				used[cv] = tick;
				for (int i = head[cv]; i != 0; i = next[i]) {
					int x = data[i];
					if (used[x] != tick) {
						used[x] = tick;
						queue[qT] = x;
						dist[qT] = cd + 1;
						qT++;
					}
				}
			}
			out.println(ans == -1 ? "Impossible" : ans);
		}
		out.close();
	}
	
	int[] p10 = { 1, 10, 100, 1000, 10000 };
	
	int get(int n, int d, int v) {
		return n - n % p10[d + 1] + p10[d] * v + n % p10[d];
	}

	void add(int u, int v) {
		next[cnt] = head[u];
		data[cnt] = v;
		head[u] = cnt++;
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
