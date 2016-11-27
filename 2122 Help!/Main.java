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
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int[] time = new int [26];
		
		for (int n = nextInt(); n > 0; n = nextInt()) {
			Arrays.fill(time, 0);
			int ans = 0;
			int last = 0;
			for (int i = 0; i < n; i++) {
				int p = nextToken().charAt(0) - 'A';
				int tm = nextInt();
				boolean ok = nextToken().charAt(0) == 'c';
				if (ok) {
					ans++;
					last += tm + time[p] * 20;
				} else
					time[p]++;
			}
			out.println(ans + " " + last);
		}
		
		out.close();
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
