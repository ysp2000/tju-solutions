import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (int n = nextInt(), ans, sum, i; n > 0; n = nextInt()) {
			for (ans = sum = i = 0; i < n; i++)
				ans = Math.max(ans, sum = Math.max(0, sum + nextInt()));
			if (ans > 0) { out.print("The maximum winning streak is "); out.print(ans); out.println('.'); }
			else out.println("Losing streak.");
		}
		out.close();
	}
	
	int nextInt() throws IOException {
		int n, c, s = 1;
		for (c = in.read(); c < '0' || c > '9'; c = in.read())
			if (c == '-')
				s = -1;
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return s == 1 ? n : -n;
 	}
}
