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
	
	int vNum;
	String[] place = new String [333 * 2];
	Map<String, Integer> map = new HashMap<String, Integer>();
	MultiList g = new MultiList();
	int[] order = new int [333 * 2];
	int[] used = new int [333 * 2];
	int tick = 0;
	int osz;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int t = 1, T = nextInt(); T --> 0;) {
			int E = nextInt();
			vNum = 0;
			map.clear();
			g.clear(E * 2);
			for (int i = 1; i < E; i++)
				g.add(vert(nextToken()), vert(nextToken()));
			osz = 0;
			tick++;
			for (int i = 0; i < vNum; i++)
				if (used[i] != tick)
					dfs(i);
			out.print("Scenario #"); out.print(t++); out.println(':');
			while (osz --> 0)
				out.println(place[order[osz]]);
			out.println();
		}
		
		out.close();
	}
	
	void dfs(int v) {
		used[v] = tick;
		for (int i = g.head[v], x; i != 0; i = g.next[i])
			if (used[x = g.data[i]] != tick)
				dfs(x);
		order[osz++] = v;
	}

	int vert(String s) {
		if (!map.containsKey(s))
			map.put(place[vNum] = s, vNum++);
		return map.get(s);
	}
	
	class MultiList {
		int[] head = new int [333 * 2];
		int[] next = new int [333 + 1];
		int[] data = new int [333 + 1];
		int cnt = 1;
		
		void clear(int n) {
			cnt = 1;
			Arrays.fill(head, 0, n, 0);
		}
		
		void add(int h, int v) {
			next[cnt] = head[h];
			data[cnt] = v;
			head[h] = cnt++;
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
