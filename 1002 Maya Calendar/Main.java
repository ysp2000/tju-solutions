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
		String[] haab = { "pop", "no", "zip", "zotz", "tzec", "xul", "yoxkin", "mol", "chen", "yax", "zac", "ceh", "mac", "kankin", "muan", "pax", "koyab", "cumhu", "uayet" };
		String[] tzolkin = { "imix", "ik", "akbal", "kan", "chicchan", "cimi", "manik", "lamat", "muluk", "ok", "chuen", "eb", "ben", "ix", "mem", "cib", "caban", "eznab", "canac", "ahau" };
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < haab.length; i++)
			map.put(haab[i], i);
		int T = Integer.parseInt(in.readLine());
		out.println(T);
		for (; T --> 0;) {
			StringTokenizer tok = new StringTokenizer(in.readLine(), ". ");
			int day = Integer.parseInt(tok.nextToken()) + 20 * map.get(tok.nextToken()) + 365 * Integer.parseInt(tok.nextToken());
			out.println((1 + day % 13) + " " + tzolkin[day % 20] + " " + day / 260);
		}
		out.close();
	}
}
