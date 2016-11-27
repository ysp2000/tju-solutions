import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (String s = in.readLine(); s.charAt(0) != '0'; s = in.readLine()) {
			StringTokenizer tok = new StringTokenizer(s);
			int b = Integer.parseInt(tok.nextToken());
			BigInteger p = new BigInteger(tok.nextToken(), b);
			BigInteger m = new BigInteger(tok.nextToken(), b);
			System.out.println(p.mod(m).toString(b));
		}
	}
}
