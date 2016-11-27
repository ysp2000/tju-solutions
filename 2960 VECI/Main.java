import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	int n;
	char[] d;
	int[] p;
	boolean[] u;
	int best = 1000000000;
	int num = -1;
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		d = in.readLine().toCharArray();
		p = new int [n = d.length];
		u = new boolean [n];
		dfs(0);
		System.out.println(best == 1000000000 ? 0 : best);
	}

	void dfs(int v) {
		if (v == n) {
			int cur = 0;
			int pow = 1;
			for (int i = 0; i < n; i++, pow *= 10)
				cur += pow * (d[p[i]] - '0');
			if (num == -1)
				num = cur;
			else if (num < cur && cur < best)
				best = cur;
			return;
		}
		for (int i = n - 1; i >= 0; i--) {
			if (!u[i]) {
				u[i] = true;
				p[v] = i;
				dfs(v + 1);
				u[i] = false;
			}
		}
	}
}
