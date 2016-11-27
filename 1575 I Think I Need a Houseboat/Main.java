import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		double K = Math.PI * 1e-2;
		for (int T = Integer.parseInt(in.readLine().trim()), t = 1; T --> 0;) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			double x = Double.parseDouble(tok.nextToken());
			double y = Double.parseDouble(tok.nextToken());
			out.print("Property "); out.print(t++); out.print(": This property will begin eroding in year ");
			out.print((long) Math.ceil(K * (x * x + y * y) - 1e-6)); out.println('.');
		}
		out.println("END OF OUTPUT.");
		out.close();
	}
}
