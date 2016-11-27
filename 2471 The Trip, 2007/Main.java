import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import static java.lang.Math.*;

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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		boolean blank = false;
		
		for (int n = nextInt(); n > 0; n = nextInt()) {
			if (blank) {
				out.println();
			}
			
			blank = true;
			
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int ans = 0;
			
			for (int i = 0; i < n; i++) {
				int x = nextInt();
				
				if (!map.containsKey(x)) {
					map.put(x, 1);
				} else {
					map.put(x, map.get(x) + 1);
				}
				
				ans = max(ans, map.get(x));
			}
			
			int[][] a = new int [ans][n / ans + 1];
			int[] sz = new int [ans];
			int ca = 0;
			
			for (Entry<Integer, Integer> e : map.entrySet()) {
				int num = e.getKey();
				int cnt = e.getValue();
				
				for (int i = 0; i < cnt; i++) {
					a[ca][sz[ca]++] = num;
					ca++;
					
					if (ca >= ans) {
						ca = 0;
					}
				}
			}
			
			out.println(ans);
			
			for (int i = 0; i < ans; i++) {
				for (int j = 0; j < sz[i]; j++) {
					out.print((j > 0 ? " " : "") + a[i][j]);
				}
				
				out.println();
			}
		}
		
		out.close();
	}
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
}
