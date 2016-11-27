import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (;;) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(tok.nextToken());
			if (a == 0)
				break;
			int b = Integer.parseInt(tok.nextToken());
			int s = Integer.parseInt(tok.nextToken());
			int m = Integer.parseInt(tok.nextToken());
			int n = Integer.parseInt(tok.nextToken());
			long x = a * m;
			long y = b * n;
			System.out.printf(Locale.US ,"%.2f %.2f%n", toDegrees(atan(y / (double) x)), sqrt(x * x + y * y) / s);
		}
	}
}
