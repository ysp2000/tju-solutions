import java.io.*;
import java.util.*;

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
		
		int test = 1;
		
		for (double H = nextDouble(); H > 0; H = nextDouble()) {
			out.println("Case " + test++ + ":");
			
			double V = nextDouble();
			double h = nextDouble();
			double v = nextDouble();
			
			for (int pNum = nextInt(), p = 0; p < pNum; p++) {
				double x = nextDouble();
				double y = nextDouble();
				int depth = 0;
				double l = 0;
				double r = H;
				double b = 0;
				double t = V;
				
				while (true) {
					depth++;
					double ch = l + (r - l) * h;
					double cv = b + (t - b) * v;
					
					if (x < ch && y > cv || x > ch && y < cv) {
						break;
					} else if (x < ch) {
						r = ch;
						t = cv;
					} else {
						l = ch;
						b = cv;
					}
				}
				
				out.println((depth & 1) == 1 ? "black" : "white");
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
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}
