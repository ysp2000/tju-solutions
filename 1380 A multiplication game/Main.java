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

	void run() throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		for (long N = nextLong(); N > 0; N = nextLong()) {
			int cnt = 0;
			while (N > 1) {
				if (((cnt++) & 1) == 0)
					N = (N % 9 > 0 ? 1 : 0) + (N / 9);
				else
					N = ((N & 1) > 0 ? 1 : 0) + (N >> 1);
			}
			out.println(((cnt & 1) == 1 ? "Stan" : "Ollie") + " wins.");
		}
		out.close();
	}
	
	long nextLong() throws IOException {
		long n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read())
			if (c == -1)
				break;
		for (n = 0L; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10L + c - '0';
		return n;
 	}
}
