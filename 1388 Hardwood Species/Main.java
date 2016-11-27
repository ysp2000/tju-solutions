import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		new Main().run();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");

	int total = 0;
	Map<String, Integer> map = new HashMap<String, Integer>();
	List<String> lst = new ArrayList<String>(10000);
	
	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		while (in.ready()) {
			String cur = in.readLine();
			if (map.containsKey(cur))
				map.put(cur, map.get(cur) + 1);
			else
				map.put(cur, 1);
			total++;
		}
		
		for (String s : map.keySet())
			lst.add(s);
		Collections.sort(lst);
		
		double mul = 100.0 / total;
		for (String s : lst)
			out.printf(Locale.US, "%s %.4f%n", s, map.get(s) * mul);
		
		out.close();
	}
}
