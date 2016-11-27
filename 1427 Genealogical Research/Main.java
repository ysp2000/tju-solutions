import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists()) {
				System.setIn(new FileInputStream("input.txt"));
			}
		} catch (SecurityException e) {
		}
		
		new Main().run();
	}

	PrintWriter out;
	Map<String, Person> map = new HashMap<String, Person>();
	StringTokenizer tok;
	boolean blank = false;
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (String cmd = in.readLine(); cmd.charAt(0) != 'Q'; cmd = in.readLine()) {
			if (cmd.charAt(0) == 'B') {
				tok = new StringTokenizer(cmd.substring(5), ":");
				add(nextToken(), nextToken(), get(nextToken()), get(nextToken()));
			} else if (cmd.charAt(0) == 'A') {
				tok = new StringTokenizer(cmd.substring(9), ":");
				Person p = map.get(nextToken());
				blank();
				out.println("ANCESTORS of " + p.name);
				printAnc(p, 2);
			} else if (cmd.charAt(2) == 'A') {
				tok = new StringTokenizer(cmd.substring(5), ":");
				map.get(nextToken()).death = nextToken();
			} else {
				tok = new StringTokenizer(cmd.substring(11), ":");
				Person p = map.get(nextToken());
				blank();
				out.println("DESCENDANTS of " + p.name);
				printDec(p, 2);
			}
		}
		out.close();
	}
	
	void blank() {
		if (blank)
			out.println();
		blank = true;
	}
	
	void printAnc(Person p, int offset) {
		for (Person pr : p.parents) {
			offset(offset);
			out.println(pr);
			printAnc(pr, offset + 2);
		}
	}
	
	void printDec(Person p, int offset) {
		Collections.sort(p.children);
		for (Person ch : p.children) {
			offset(offset);
			out.println(ch);
			printDec(ch, offset + 2);
		}
	}
	
	void offset(int offset) {
		for (int i = 0; i < offset; i++)
			out.print(' ');
	}
	
	void insert(String name) {
		if (!map.containsKey(name)) {
			Person np = new Person();
			np.name = name;
			map.put(name, np);
		}
	}
	
	Person get(String name) {
		insert(name);
		return map.get(name);
	}
	
	void add(String name, String birth, Person p1, Person p2) {
		insert(name);
		Person p = map.get(name);
		p.birth = birth;
		p.parents.add(p1);
		p.parents.add(p2);
		p1.children.add(p);
		p2.children.add(p);
		Collections.sort(p.parents);
	}
	
	String nextToken() {
		return tok.nextToken().trim();
	}
	
	class Person implements Comparable<Person> {
		String name;
		String birth;
		String death;
		List<Person> parents = new ArrayList<Person>();
		List<Person> children = new ArrayList<Person>();
		
		@Override
		public int compareTo(Person o) {
			return name.compareTo(o.name);
		}
		
		@Override
		public String toString() {
			return name + (birth != null ? (" " + birth + " -") : "") + (death != null ? (" " + death) : "");
		}
	}
}
