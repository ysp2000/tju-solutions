import java.io.*;
import java.util.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	int tick = 0;
	int edge = 0;
	int[] tmp = new int [5];
	int[] used = new int [26];
	int[][] graph = new int [26][26];
	int[] order = new int [26];
	int osz;
	int snum;
	int[] ssz = new int [26];
	int[][] ssc = new int [26][26];
	String[] ans = new String [26];
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int n = nextInt(); n > 0; n = nextInt()) {
			if (tick++ > 0)
				out.println();
			edge = tick;
			for (int t = 0; t < n; t++) {
				for (int i = 0; i < 5; i++)
					tmp[i] = nextToken().charAt(0) - 'A';
				int ans = nextToken().charAt(0) - 'A';
				for (int i = 0; i < 5; i++)
					graph[ans][tmp[i]] = tick;
			}
			osz = 0;
			for (int i = 0; i < 26; i++)
				if (graph[i][i] == edge && used[i] != tick)
					dfs1(i);
			tick++;
			snum = 0;
			fill(ssz, 0);
			for (int i = osz - 1; i >= 0; i--) {
				int x = order[i];
				if (used[x] != tick) {
					dfs2(x);
					snum++;
				}
			}
			for (int i = 0; i < snum; i++) {
				sort(ssc[i], 0, ssz[i]);
				StringBuffer sb = new StringBuffer();
				sb.append((char) (ssc[i][0] + 'A'));
				for (int j = 1; j < ssz[i]; j++)
					sb.append(" " + (char) (ssc[i][j] + 'A'));
				ans[i] = sb.toString();
			}
			sort(ans, 0, snum);
			for (int i = 0; i < snum; i++)
				out.println(ans[i]);
		}
		out.close();
	}
	
	void dfs1(int v) {
		used[v] = tick;
		for (int i = 0; i < 26; i++)
			if (used[i] != tick && graph[v][i] == edge)
				dfs1(i);
		order[osz++] = v;
	}
	
	void dfs2(int v) {
		used[v] = tick;
		ssc[snum][ssz[snum]++] = v;
		for (int i = 0; i < 26; i++)
			if (used[i] != tick && graph[i][v] == edge)
				dfs2(i);
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
