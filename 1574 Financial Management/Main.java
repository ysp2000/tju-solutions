import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		long sum = 0;
		for (int i = 0; i < 12; i++)
			sum += get();
		long ans = (long) (sum * 0.08333333333333333 + 0.5);
		long rst = ans % 100;
		System.out.println("$" + ans / 100 + "." + (rst < 10 ? "0" : "") + rst);
	}
	
	static long get() throws IOException {
		long n, c;
		for (c = System.in.read(); c < '0' || c > '9'; c = System.in.read());
		for (n = 0L; '0' <= c && c <= '9' || c == '.'; c = System.in.read())
			if (c != '.')
				n = n * 10L + c - '0';
		return n;
 	}
}
