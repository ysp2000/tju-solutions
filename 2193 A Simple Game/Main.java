import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		for (;;) {
			int N = readInt(in);
			if (N == 0)
				break;
			out.println(N % (readInt(in) + 1) == 0 ? "Think About It." : "Just Do It.");
		}
		
		out.close();
	}
	
	static int readInt(BufferedReader in) throws IOException {
		int ch, N = 0;
		for (ch = in.read(); ch < '0' || ch > '9'; ch = in.read());
		for (; '0' <= ch && ch <= '9'; ch = in.read())
			N = 10 * N + ch - '0';
		return N;
	}
}
