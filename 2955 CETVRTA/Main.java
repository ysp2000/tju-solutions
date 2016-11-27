import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		int x1 = ni(), y1 = ni(), x2 = ni(), y2 = ni(), x3 = ni(), y3 = ni();
		System.out.println((x1==x2?x3:(x1==x3?x2:x1))+" "+(y1==y2?y3:(y1==y3?y2:y1)));
	}
	static int ni() throws IOException {
		int n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0; '0' <= c && c <= '9'; c = System.in.read())
			n = n * 10 + c - '0';
		return n;
 	}
}
