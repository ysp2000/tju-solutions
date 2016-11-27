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

	char[] perm;
	int[] used = new int [26];
	int[] cnt = new int [26 + 1];
	int tick = 0;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int T = nextInt(); T --> 0;) {
			perm = nextToken().toCharArray();
			Arrays.fill(cnt, 0);
			tick++;
			for (int i = 0; i < perm.length; i++) cnt[dfs(perm[i])]++;
			boolean ok = true;
			for (int i = 1; i < 14; i++)
				if (cnt[i << 1] % 2 == 1) {
					ok = false;
					break;
				}
			out.println(ok ? "Yes" : "No");
		}
		out.close();
	}
	
	int dfs(char c) {
		int v = c - 'A';
		if (used[v] == tick) return 0;
		used[v] = tick;
		return 1 + dfs(perm[v]);
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
