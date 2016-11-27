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
		
		for (;;) {
			int a = nextInt();
			int b = nextInt();
			if (a == 0 && b == 0)
				break;
			int ans = 0;
			int carry = 0;
			while (a > 0 || b > 0) {
				carry = (a % 10 + b % 10 + carry) / 10;
				if (carry > 0)
					ans++;
				a /= 10;
				b /= 10;
			}
			out.print(ans == 0 ? "No" : ans);
			out.print(" carry operation");
			if (ans > 1)
				out.print('s');
			out.println('.');
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
