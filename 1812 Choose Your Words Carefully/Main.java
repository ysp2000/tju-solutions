import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		new Main().run();
	}

	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> map = new HashMap<String, Integer>();
		int max = 0;
		while (in.ready()) {
			StringTokenizer tok = new StringTokenizer(in.readLine(), " ,.;\'`\"()/:-");
			while (tok.hasMoreTokens())
				max = Math.max(max, add(map, tok.nextToken().toLowerCase()));
		}
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, Integer> e : map.entrySet())
			if (e.getValue() == max)
				list.add(e.getKey());
		Collections.sort(list);
		PrintWriter out = new PrintWriter(System.out);
		out.print(max); out.println(" occurrences");
		for (String s : list)
			out.println(s);
		out.close();
	}
	
	static int add(Map<String, Integer> map, String s) {
		if (!map.containsKey(s)) {
			map.put(s, 1);
			return 1;
		}
		int ret = map.get(s) + 1;
		map.put(s, ret);
		return ret;
	}
}
