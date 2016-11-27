import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		PrintWriter out = new PrintWriter(System.out);
		int[] a = new int [100000];
		
		lp: for (int N = readInt(); N > 0; N = readInt()) {
			for (int i = 0; i < N; i++)
				a[i] = readInt() - 1;
			for (int i = 0; i < N; i++)
				if (i != a[a[i]]) {
					out.println("not ambiguous");
					continue lp;
				}
			out.println("ambiguous");
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
