import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		int n = nextInt();
		int nn = 1 << n;
		PrintWriter out = new PrintWriter(System.out);
		for (int i = 0; i < n; i++) out.print('O'); out.println();
		for (int i = 1; i < nn; i++) {
			for (int g = i ^ (i >> 1), k = n; k > 0; k--, g >>= 1)
				out.print((g & 1) == 0 ? 'O' : 'X');
			out.println();
		}
		for (int i = 0; i < n; i++) out.print('O'); out.println();	
		out.close();
	}
	
	static int nextInt() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
