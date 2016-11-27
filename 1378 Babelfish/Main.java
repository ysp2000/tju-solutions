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
		Map<String, String> map = new HashMap<String, String>();
		for (String s; !(s = in.readLine()).isEmpty();) {
			StringTokenizer tok = new StringTokenizer(s);
			String a = tok.nextToken();
			map.put(tok.nextToken(), a);
		}
		while (in.ready()) {
			String s = map.get(in.readLine());
			out.println(s == null ? "eh" : s);
		}
		out.close();
	}
}
