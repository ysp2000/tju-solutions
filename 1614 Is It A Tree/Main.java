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
	
	Map<Integer, ArrayList> g = new HashMap<Integer, ArrayList>();
	Set<Integer> used = new HashSet<Integer>();
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		
		lp: for (int t = 1;;) {
			g.clear();
			
			boolean tree = true;

			for (;;) {
				int u = nextInt();
				int v = nextInt();
				if (u == -1 && v == -1)
					break lp;
				if (u == 0 && v == 0)
					break;
				if (!g.containsKey(u))
					g.put(u, new ArrayList<Integer>());
				g.get(u).add(v);
				tree = false;
			}
			
			sl: for (int v : g.keySet()) {
				used.clear();
				if (dfs(v, -1) && check()) {
					tree = true;
					break sl;
				}
			}
			
			out.println("Case " + t++ + (tree ? " is a tree." : " is not a tree."));
		}
		
		out.close();
	}

	boolean dfs(int v, int p) {
		used.add(v);
		List<Integer> lst = g.get(v);
		if (lst == null)
			return true;
		for (int i : lst)
			if (used.contains(i))
				return false;
			else if (!dfs(i, v))
				return false;
		return true;
	}
	
	boolean check() {
		for (int v : g.keySet())
			if (!used.contains(v))
				return false;
		return true;
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
