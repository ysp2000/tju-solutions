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
	PrintWriter out;
	
	int N;
	int[] G;
	int[] C;
	int[] W;

	int[] p10 = { 1, 10, 100, 1000, 10000 };
	int[] used = new int [4];
	int tick = 0;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		N = nextInt();
		G = new int [N];
		C = new int [N];
		W = new int [N];
		for (int i = 0; i < N; i++) {
			G[i] = nextInt();
			C[i] = nextInt();
			W[i] = nextInt();
		}
		for (int S = 1000; S < 10000; S++) {
			boolean ok = true;
			for (int i = 0; i < N; i++) {
				tick++;
				int c = 0;
				int w = 0;
				for (int d = 0; d < 4; d++)
					if (((S / p10[d]) % 10) == ((G[i] / p10[d]) % 10)) {
						used[d] = tick;
						c++;
					}
				for (int u = 0; u < 4; u++) if (used[u] != tick)
					for (int v = 0; v < 4; v++) if (used[v] != tick)
						if (((S / p10[u]) % 10) == ((G[i] / p10[v]) % 10))
							w++;
				if (c != C[i] || w != W[i]) {
					ok = false;
					break;
				}
			}
			if (ok) {
				out.println(S);
				out.close();
				return;
			}
		}
		out.println("NONE");
		out.close();
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
