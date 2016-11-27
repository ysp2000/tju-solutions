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
		while (in.ready()) {
			StringTokenizer tok = new StringTokenizer(in.readLine(), "+=");
			out.println((Integer.parseInt(new StringBuilder(tok.nextToken()).reverse() + "") + Integer.parseInt(new StringBuilder(tok.nextToken()).reverse() + "") == Integer.parseInt(new StringBuilder(tok.nextToken()).reverse() + "")) ? "True" : "False");
		}
		out.close();
	}
}
