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
	
	int N;
	double[] C;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		N = nextInt();
		C = new double [N + 1];
		for (int i = 0; i <= N; i++)
			C[i] = nextDouble();
		double s = signum(C[N]);
		for (int i = 0; i <= N; i++)
			C[i] *= s;
			
		double l = -1000000.0;
		double r = 1000000.0;
		
		for (int it = 0; it < 500; it++) {
			double x = 0.5 * (l + r);
			double y = f(x);
			if (y > 1e-5)
				r = x;
			else
				l = x;
		}
		
		double x = 1000 * 0.5 * (l + r);
		out.println((long) x);
		out.close();
	}
	
	double f(double x) {
		double res = 0.0;
		double cx = 1.0;
		for (int i = 0; i <= N; i++) {
			res += C[i] * cx;
			cx *= x;
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
