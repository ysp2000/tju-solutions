import java.io.*;
import java.util.regex.*;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			if (new File("input.txt").exists())
				System.setIn(new FileInputStream("input.txt"));
		} catch (SecurityException e) {}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		Pattern p = Pattern.compile("([\\w]+)://([-A-Za-z0-9\\.]+)(:([\\d]+))*(/([\\S]+))*");
		for (int T = Integer.parseInt(in.readLine()), t = 1; T --> 0;) {
			Matcher m = p.matcher(in.readLine());
			if (m.matches()) {
				out.print("URL #"); out.println(t++);
				out.print("Protocol = "); out.println(m.group(1));
				out.print("Host     = "); out.println(m.group(2));
				out.print("Port     = "); out.println(m.group(4) == null ? "<default>" : m.group(4));
				out.print("Path     = "); out.println(m.group(6) == null ? "<default>" : m.group(6));
				out.println();
			}
		}
		out.close();
	}
}
