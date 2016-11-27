import java.io.*;
import java.util.*;
import static java.lang.Math.*;
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

	Agency[] a = new Agency [100];
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int T = Integer.parseInt(in.readLine()), t = 1; t <= T; t++) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			int wl = Integer.parseInt(tok.nextToken());
			int wt = Integer.parseInt(tok.nextToken());
			int n = Integer.parseInt(tok.nextToken());
			for (int i = 0; i < n; i++)
				a[i] = new Agency(in.readLine(), wl, wt);
			sort(a, 0, n);
			out.println("Case " + t);
			for (int i = 0; i < n; i++)
				out.println(a[i]);
		}
		out.close();
	}
	
	class Agency implements Comparable<Agency> {
		String name;
		int a, b;
		long c;
		
		Agency(String s, int wl, int ws) {
			StringTokenizer tok = new StringTokenizer(s, " ,:");
			name = tok.nextToken();
			a = Integer.parseInt(tok.nextToken());
			b = Integer.parseInt(tok.nextToken());
			c = 0;
			
			while (wl > ws) {
				int h = wl >> 1;
				if (h >= ws) {
					c += min((wl - h) * a, b);
					wl = h;
				} else {
					c += (wl - ws) * a;
					wl = ws;
				}
			}
		}
		
		@Override
		public int compareTo(Agency o) {
			if (c == o.c)
				return name.compareTo(o.name);
			return c < o.c ? -1 : 1;
		}
		
		@Override
		public String toString() {
			return name + " " + c;
		}
	}
}
