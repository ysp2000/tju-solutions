/* Monte carlo ))) */
import java.io.*;
import java.util.*;
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
	
	double min = 0.98 - 1e-8;
	double max = 1.02 + 1e-8;
	double[] a = new double [10000];
	double tw;
	int tick;
	int[] used = new int [10000];
	Random rnd = new Random();
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		for (int n = nextInt(); n > 0; n = nextInt()) {
			tw = 0.0;
			for (int i = 0; i < n; i++)
				tw += a[i] = nextDouble();
			loop: for (;;) {
				double w = monteCarlo(n);
				
				if (abs(w) > 1e-7) {
					double p = (tw - w) / w;
					if (min < p && p < max) {
						boolean sb = false;
						for (int i = 0; i < n; i++) {
							if (used[i] == tick) {
								if (sb)
									out.print(" ");
								sb = true;
								out.print((i + 1));
							}
						}
						
						out.println();
						break loop;
					}
				}
			}
		}
		
		out.close();
	}
	
	double monteCarlo(int n) {
		tick++;
		double res = 0.0;
		
		while (res < tw - res) {
			for (int i = rnd.nextInt(n);; i = rnd.nextInt(n)) {
				if (used[i] != tick) {
					used[i] = tick;
					res += a[i];
					break;
				}
			}
		}
		
		return res;
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
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
