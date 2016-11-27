import java.io.*;

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

	char[] wff;
	int pos;
	int state;
	
	void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		for (String s = in.readLine(); !s.equals("0"); s = in.readLine()) {
			wff = s.toCharArray();
			boolean ans = true;
			for (pos = 0, state = 0; state < 32 && ans; pos = 0, state++)
				ans = calc();
			out.println(ans ? "tautology" : "not");
		}
		
		out.close();
	}
	
	boolean calc() {
		char op = wff[pos++];
		
		if (op == 'K')
			return calc() & calc();
		if (op == 'A')
			return calc() | calc();
		if (op == 'N')
			return !calc();
		if (op == 'C')
			return !calc() | calc();
		if (op == 'E')
			return calc() == calc();
			
		return (state & (1 << (op - 'p'))) > 0;
	}
}
