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
		Set<String> set = new HashSet<String>();
		while (in.ready())
			set.add(in.readLine());
		String[] ans = new String [60000];
		int sz = 0;
		for (String s : set)
			for (int l = s.length(), i = 1; i < l; i++)
				if (set.contains(s.substring(0, i)) && set.contains(s.substring(i))) {
					ans[sz++] = s;
					break;
				}
		Arrays.sort(ans, 0, sz);
		for (int i = 0; i < sz; i++)
			out.println(ans[i]);
		out.close();
	}
}
