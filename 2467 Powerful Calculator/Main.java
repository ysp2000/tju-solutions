import java.io.*;
import java.math.BigInteger;

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
		boolean blank = false;
		while (true) {
			BigInteger a = new BigInteger(in.readLine());
			BigInteger b = new BigInteger(in.readLine());
			if (a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO))
				break;
			if (blank)
				out.println();
			blank = true;
			out.println(a.add(b));
			out.println(a.subtract(b));
			out.println(a.multiply(b));
		}
		out.close();
	}
}
