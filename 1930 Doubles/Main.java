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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int[] set = new int [200];
		int[] lst = new int [15];
		int sz = 0;
		lp: for (int tick = 1;; tick++) {
			sz = 0;
			for (;;) {
				int v = nextInt();
				if (v == -1) break lp;
				if (v == 0) break;
				set[v] = tick;
				lst[sz++] = v;
			}
			int ans = 0;
			for (int i = 0; i < sz; i++)
				if (set[lst[i] * 2] == tick)
					ans++;
			out.println(ans);
		}
		
		out.close();
	}
	
	int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return s == 1 ? n : -n;
 	}
}
