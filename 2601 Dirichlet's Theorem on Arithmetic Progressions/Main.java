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
		int MAXP = 1000000;
		int SQRT = 1000;
		boolean[] erat = new boolean [MAXP + 1];
		erat[0] = erat[1] = true;
		for (int i = 4; i <= MAXP; i += 2)
			erat[i] = true;
		for (int i = 3; i <= MAXP; i += 2)
			if (!erat[i] && i <= SQRT)
				for (int j = i * i; j <= MAXP; j += i)
					erat[j] = true;
		in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (;;) {
			int a = nextInt();
			int d = nextInt();
			int n = nextInt();
			if (a + d + n == 0)
				break;
			for (; n > 0; a += d)
				if (!erat[a])
					n--;
			out.println(a - d);
		}
		out.close();
	}
	
	int nextInt() throws IOException {
		int n, c;
		for (c = in.read(); c < '0' || c > '9'; c = in.read());
		for (n = 0; '0' <= c && c <= '9'; c = in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
