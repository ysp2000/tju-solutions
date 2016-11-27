import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		for (int T = readInt(), t = 1; t <= T; t++) {
			int n = readInt();
			int m = readInt();
			out.println("Scenario #" + t + ":");
			out.println(n * m  + ((n & 1) == 1 && (m & 1) == 1 ? ".41" : ".00"));
			out.println();
		}
		out.close();
	}
	
	static int readInt() throws IOException {
		int n = 0, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (; '0' <= c && c <= '9'; c = System.in.read())
			n = 10 * n + c - '0';
		return n;
	}
}
